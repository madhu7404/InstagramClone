package com.example.instagramclone.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.instagramclone.Login.LoginActivity;
import com.example.instagramclone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignOutFragment extends Fragment {
    private static final String TAG = "SignOutFragment";
    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressBar mProgressBar;
    private TextView tvSignout,tvSigningOut;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signout,container,false);
        tvSignout = (TextView) view.findViewById(R.id.tvConfirmSignout);
        mProgressBar = (ProgressBar) view.findViewById(R.id.ProgressBar);
        tvSigningOut = (TextView) view.findViewById(R.id.tvSigningOut);
        Button btnConfirmSignout = (Button) view.findViewById(R.id.btnConfirmSignout);
        mProgressBar.setVisibility(view.GONE);
        tvSigningOut.setVisibility(view.GONE);
        setupFirebaseAuth();
        btnConfirmSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: attempting to sign out.");
                mProgressBar.setVisibility(view.VISIBLE);
                tvSigningOut.setVisibility(view.VISIBLE);

                mAuth.signOut();
                getActivity().finish();
            }
        });
        return view;
    }
    /*
    ---------------------------------firebase------------------------------------
     */

    /**
     *setup the firebase auth object
     */
    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth. ");
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user!=null){
                    //user is signed in
                    Log.d(TAG, "onAuthStateChanged: signed_in:" + user.getUid());
                }else {
                    //user is signed out
                    Log.d(TAG, "onAuthStateChanged: signed_out:");
                    Log.d(TAG, "onAuthStateChanged: navigating back to login screen.");
                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                //...
            }
        };
    }
    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }
    @Override
    public void onStop(){
        super.onStop();
        if (mAuthListener!=null){
            mAuth.removeAuthStateListener(mAuthListener
            );
        }
    }


}
