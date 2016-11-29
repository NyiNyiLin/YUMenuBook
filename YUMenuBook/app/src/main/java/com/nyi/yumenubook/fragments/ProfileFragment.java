package com.nyi.yumenubook.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nyi.yumenubook.R;
import com.nyi.yumenubook.activities.SignInActivity;
import com.nyi.yumenubook.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @BindView(R.id.tv_profile_name)
    TextView tvProfileName;

    @BindView(R.id.tv_profile_email)
    TextView tvProfileEmail;

    @BindView(R.id.tv_profile_phone)
    TextView tvProfilePhone;

    @BindView(R.id.tv_profile_major)
    TextView tvProfileMajor;

    @BindView(R.id.tv_profile_age)
    TextView tvProfileAge;

    @BindView(R.id.iv_profile_major_edit)
    ImageView ivProfileMajorEdit;

    @BindView(R.id.iv_profile_phone_edit)
    ImageView ivProfilePhoneEdit;

    @BindView(R.id.iv_profile_age_edit)
    ImageView ivProfileAgeEdit;

    @BindView(R.id.btn_log_out)
    Button btnLogOut;

    @BindView(R.id.iv_user_profile)
    ImageView ivUserProfile;

    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getContext());
        mAuth = FirebaseAuth.getInstance();

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        displayUser();

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
            }
        });

        // ...
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    // User is signed in
                    Log.d(Constants.TAG, " Profile Fragment onAuthStateChanged:signed_in:" + firebaseUser.getUid());
                    displayUser();
                } else {
                    // User is signed out
                    Log.d(Constants.TAG, "Profile Fragment onAuthStateChanged:signed_out");
                    Intent intent = new Intent(getContext(), SignInActivity.class);
                    startActivity(intent);
                }
            }
        };

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void displayUser(){
        if(firebaseUser != null){
            tvProfileName.setText(firebaseUser.getDisplayName());
            tvProfileEmail.setText(firebaseUser.getEmail());
            Glide.with(getContext())
                    .load(firebaseUser.getPhotoUrl())
                    .asBitmap().centerCrop()
                    .placeholder(R.drawable.ic_camera_black_24dp)
                    .error(R.drawable.ic_camera_black_24dp)
                    .into(ivUserProfile);
        }
    }

}
