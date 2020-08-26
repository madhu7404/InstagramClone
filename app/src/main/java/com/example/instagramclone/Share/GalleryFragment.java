package com.example.instagramclone.Share;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.instagramclone.R;
import com.example.instagramclone.Utils.FilePaths;
import com.example.instagramclone.Utils.FileSearch;
import com.example.instagramclone.Utils.GridImageAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

  public class GalleryFragment extends Fragment {
    private static final String TAG = "GalleryFragment";
    //constants
    private static final int NUM_GRID_COLUMNS = 3;


    //widgets
    private GridView gridView;
    private ImageView galleryImage;
    private ProgressBar mProgressbar;
    private Spinner directorySpinner;

    //vars
    private ArrayList<String>directories;
    private  String mAppend = "file:/";
    private String mSelectedImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_gallery,container,false);
         galleryImage = (ImageView)view.findViewById(R.id.galleryImageView);
         gridView = (GridView)view.findViewById(R.id.gridView);
         directorySpinner = (Spinner)view.findViewById(R.id.spinnerDirectory);
         mProgressbar = (ProgressBar)view.findViewById(R.id.ProgressBar);
         mProgressbar.setVisibility(View.GONE);
         directories = new ArrayList<>();
        Log.d(TAG, "onCreateView: started.");
        ImageView ShareClose = (ImageView)view.findViewById(R.id.ivCloseShare);
        ShareClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: closing the gallery fragment.");
                getActivity().finish();
            }
        });
        TextView nextScreen = (TextView) view.findViewById(R.id.tvNext);
        nextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: navigating to the final share screen.");
                Intent intent = new Intent(getActivity(),NextActivity.class);
                intent.putExtra(getString(R.string.selected_image),mSelectedImage);
                startActivity(intent);
            }
        });
        init();
         return view;
    }
    private void init(){
        FilePaths filePaths = new FilePaths();

        // Check for other folders inside "/storage/emulated/0/pictures"
        if (FileSearch.getDirectoryPaths(filePaths.PICTURES) != null){
            directories = FileSearch.getDirectoryPaths(filePaths.PICTURES);

        }
        ArrayList<String> directoryNames = new ArrayList<>();
        for (int i = 0; i<directories.size(); i++){
            int index = directories.get(i).lastIndexOf("/");
            String string = directories.get(i).substring(index);
            directoryNames.add(string);
        }
        directories.add(filePaths.CAMERA);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,directoryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        directorySpinner.setAdapter(adapter);
        directorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemSelected: selected:"+ directories.get(position));
                //setup our image grid for the directory chosen
                setupGridView(directories.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void setupGridView(String selectedDirectory){
        Log.d(TAG, "setupGridView: directory chosen:" + selectedDirectory);
        final ArrayList<String> imgURLs = FileSearch.getFilePaths(selectedDirectory);
        //set the grid column width
        int gridWidth = getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth/NUM_GRID_COLUMNS;
        gridView.setColumnWidth(imageWidth);

        //use the grid adapter to adapt the images to gridView
        GridImageAdapter adapter = new GridImageAdapter(getActivity(),R.layout.layout_grid_imageview,mAppend,imgURLs);
        gridView.setAdapter(adapter);
        //set the first image to be displayed when the activity fragment view is inflated
        setImage(imgURLs.get(0),galleryImage,mAppend);
        mSelectedImage = imgURLs.get(0);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: selected an image:"+imgURLs.get(position));
                setImage(imgURLs.get(position),galleryImage,mAppend);
                mSelectedImage = imgURLs.get(position);
            }
        });

    }
    private void setImage(String imgURL,ImageView image,String append){
        Log.d(TAG, "setImage: setting image");
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(append + imgURL, image, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                mProgressbar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                mProgressbar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                mProgressbar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                mProgressbar.setVisibility(View.INVISIBLE);

            }
        });
    }
}
