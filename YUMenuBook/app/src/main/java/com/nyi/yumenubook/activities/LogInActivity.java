package com.nyi.yumenubook.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.data.models.UserModel;
import com.nyi.yumenubook.fragments.LogInFragment;
import com.nyi.yumenubook.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInActivity extends AppCompatActivity implements LogInFragment.LoginController{
    @BindView(R.id.fl_log_in_acti)
    FrameLayout frameLayout;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser firebaseUser;

    public static Intent newIntent(){
        Intent intent = new Intent(YUMenuBookApp.getContext(), LogInActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this, this);

        mAuth = FirebaseAuth.getInstance();
        //Auth
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    // User is signed in
                    Log.d(Constants.TAG, " Log In Activity onAuthStateChanged:signed_in:" + firebaseUser.getUid());
                    UserModel.objInstance().saveFirebaseUser(firebaseUser);
                    LogInActivity.this.finish();
                }
            }
        };

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_log_in_acti, LogInFragment.newInstance()).commit();

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

    public void backLogInClick(View view){
        this.finish();
    }

    @Override
    public void onSuccessfulLogIn() {

    }
}
