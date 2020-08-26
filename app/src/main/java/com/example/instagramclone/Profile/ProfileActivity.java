package com.example.instagramclone.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.example.instagramclone.R;
import com.example.instagramclone.Utils.BottomNavigationViewHelper;
import com.example.instagramclone.Utils.GridImageAdapter;
import com.example.instagramclone.Utils.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_num = 4;
    private static final int NUM_GRID_COLUMNS = 3;
    private Context mContext = ProfileActivity.this;
    private ProgressBar mProgressBar;
    private ImageView profilePhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: started.");
        init();
        /*mProgressBar = (ProgressBar) findViewById(R.id.profileProgressBar);
        mProgressBar.setVisibility(View.GONE);*/
//        setupBottomNavigationView();
//        setupToolbar();
//        setupActivityWidgets();
//        setProfileImage();
//        tempGridSetup();

    }
    private void init(){
        Log.d(TAG, "init: inflating" + getString(R.string.profile_fragment));
        ProfileFragment fragment = new ProfileFragment();
        FragmentTransaction transaction = ProfileActivity.this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,fragment);
        transaction.addToBackStack(getString(R.string.profile_fragment));
        transaction.commit();
    }

//    private void tempGridSetup(){
//        ArrayList<String> imgURLs = new ArrayList<>();
//
//        imgURLs.add("https://i.redd.it/9bf67ygj710z.jpg");
//        imgURLs.add("https://image.shutterstock.com/image-photo/butterfly-grass-on-meadow-night-260nw-1111729556.jpg");
//        imgURLs.add("https://i.pinimg.com/originals/0a/13/d9/0a13d9a894f3e4d53a974e4c25f713ff.jpg");
//        imgURLs.add("https://image.made-in-china.com/2f0j00pSNEWiaGYmbY/3D-Indian-God-Hindu-God-Picture-Poster-Printing.jpg");
//        imgURLs.add("https://cutewallpaper.org/21/indian-god-pics/Indian-God-Ganesha-poster-wall-decor-Lord-Ganesh-poster-Hindu-God-high-19x27.5-49x70.jpg");
//        imgURLs.add("https://i.ytimg.com/vi/GwUSXHHF22Y/maxresdefault.jpg");
//
//        setupImageGrid(imgURLs);
//
//    }
//    private void setupImageGrid(ArrayList<String> imgURLs){
//        GridView gridView = (GridView)findViewById(R.id.gridView);
//        int gridWidth = getResources().getDisplayMetrics().widthPixels;
//        int imageWidth = gridWidth/NUM_GRID_COLUMNS;
//        gridView.setColumnWidth(imageWidth);
//        GridImageAdapter adapter = new GridImageAdapter(mContext,R.layout.layout_grid_imageview,"",imgURLs);
//        gridView.setAdapter(adapter);
//    }
//    private void setProfileImage(){
//        Log.d(TAG, "setProfileImage: setting profile photo.");
//        String imgURL = "images.musicrad.io/resizer/?image=aHR0cDovL3N0YXRpYy5saWJzeW4uY29tL3AvYXNzZXRzLzYvNS82LzMvNjU2MzM1M2I1ZWYyNjI5ZC9hbmRyb2lkY2VudHJhbC1wb2RjYXN0LTE0MDAuanBn&width=600&signature=ecW5uDuF61UrrqaZ2_cLQ4f5214=";
//        UniversalImageLoader.setImage(imgURL,profilePhoto,null,"https://");
//    }
//    private void setupActivityWidgets(){
//        mProgressBar = (ProgressBar) findViewById(R.id.profileProgressBar);
//        mProgressBar.setVisibility(View.GONE);
//        profilePhoto=(ImageView)findViewById(R.id.profile_photo);
//
//    }
     /*
     Responsible for setting up the profile toolbar
      */


//    private void setupToolbar(){
//        Toolbar toolbar = (Toolbar)findViewById(R.id.profileToolBar);
//        setSupportActionBar(toolbar);
//        ImageView profileMenu = (ImageView)findViewById(R.id.profileMenu);
//        profileMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "onClick: navigating to account settings.");
//                Intent intent = new Intent(mContext,AccountSettingsActivity.class);
//                startActivity(intent);
//            }
//        });
//
//    }
//    /*
//     *Bottom navigation View Setup
//     */
//    private void setupBottomNavigationView() {
//        Log.d(TAG, "setupBottomNavigationView: setting-up BottomNavigationView");
//        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
//        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
//        BottomNavigationViewHelper.enableNavigation(mContext,bottomNavigationViewEx);
//        Menu menu = bottomNavigationViewEx.getMenu();
//        MenuItem menuItem = menu.getItem(ACTIVITY_num);
//        menuItem.setChecked(true);
//    }
//

}
