package com.nyi.yumenubook.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;

public class SignInActivity extends AppCompatActivity {

    public static Intent newIntent(){
        Intent intent = new Intent(YUMenuBookApp.getContext(), SignInActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

}
