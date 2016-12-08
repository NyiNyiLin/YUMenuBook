package com.nyi.yumenubook.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nyi.yumenubook.R;
import com.nyi.yumenubook.data.models.UserModel;
import com.nyi.yumenubook.events.DataEvent;
import com.nyi.yumenubook.utils.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment extends Fragment {

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

    private final String PHONE = "Phone";
    private final String Major = "Major";
    private final String AGE = "Age";


    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getContext());
        mAuth = FirebaseAuth.getInstance();

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

        return view;
    }

    // This method will be called when a MessageEvent is posted (in the UI thread for Toast)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DataEvent.ChangeUserInfo event) {
        if(event.getTitle().equals(PHONE)){
            tvProfilePhone.setText(event.getValue());
            UserModel.objInstance().setUserPhone(event.getValue());
        }else if(event.getTitle().equals(Major)){
            tvProfileMajor.setText(event.getValue());
            UserModel.objInstance().setUserMajor(event.getValue());
        }else if(event.getTitle().equals(AGE)){
            tvProfileAge.setText(event.getValue());
            UserModel.objInstance().setUserAge(Integer.parseInt(event.getValue()));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @OnClick(R.id.iv_profile_phone_edit)
    public void phoneEdit(View view){
        DialogFragment dialog = EditDialogFragment.newInstance(PHONE, UserModel.objInstance().getPhone());
        dialog.show(getChildFragmentManager(), "Update");
    }

    @OnClick(R.id.iv_profile_major_edit)
    public void MajorEdit(View view){
        DialogFragment dialog = EditDialogFragment.newInstance(Major, UserModel.objInstance().getMajor());
        dialog.show(getChildFragmentManager(), "Update");
    }

    @OnClick(R.id.iv_profile_age_edit)
    public void AgeEdit(View view){
        DialogFragment dialog = EditDialogFragment.newInstance(AGE, UserModel.objInstance().getAge() + "");
        dialog.show(getChildFragmentManager(), "Update");
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

        tvProfilePhone.setText(UserModel.objInstance().getPhone());
        tvProfileMajor.setText(UserModel.objInstance().getMajor());
        tvProfileAge.setText(UserModel.objInstance().getAge() + "");
    }
}
