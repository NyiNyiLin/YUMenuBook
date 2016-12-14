package com.nyi.yumenubook.activities;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.Transaction;
import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.data.VOs.ShopVO;
import com.nyi.yumenubook.data.models.ShopModel;
import com.nyi.yumenubook.data.models.UserModel;
import com.nyi.yumenubook.fragments.HomeFragment;
import com.nyi.yumenubook.fragments.InfoFragment;
import com.nyi.yumenubook.fragments.LogInFragment;
import com.nyi.yumenubook.fragments.OrdersFragment;
import com.nyi.yumenubook.fragments.ProfileFragment;
import com.nyi.yumenubook.utils.Constants;
import com.nyi.yumenubook.views.holders.ShopViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ShopViewHolder.ControllerShopItem, LogInFragment.LoginController{

    /*@BindView(R.id.toolbar)
    Toolbar toolbar;*/

    @BindView(R.id.main_frame)
    FrameLayout frameLayout;

    @BindView(R.id.left_menu)
    LinearLayout leftMenu;

    @BindView(R.id.cv_main)
    CardView cardViewMain;

    @BindView(R.id.iv_open_left_menu)
    ImageView ivOpenLeftMenu;

    @BindView(R.id.view_main)
    View viewMain;

    @BindView(R.id.rl_leftMenu_home)
    RelativeLayout rlLefMenuHome;

    @BindView(R.id.rl_leftMenu_order)
    RelativeLayout rlLefMenuOrder;

    @BindView(R.id.rl_leftMenu_profile)
    RelativeLayout rlLefMenuProfile;

    @BindView(R.id.rl_leftMenu_info)
    RelativeLayout rlLefMenuInfo;

    @BindView(R.id.tv_main_activity_title)
    TextView tvMainTitle;

    private ObjectAnimator leftAnimation;
    private Animation animSlideRight;
    private Animation animSlideLeft;
    private boolean leftMenuOpen = false;
    private boolean isProfileClick = false;
    private final String LEFT_BG_SELECTED_COLOR = "#ffb364";
    private final String LEFT_BG_COLOR = "#fc952a";

    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    //Swipe
    private float x1,x2;
    static final int MIN_DISTANCE = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tvMainTitle.setTypeface(YUMenuBookApp.getTitleTypeface());

        //Firebase Authentication
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, HomeFragment.newInstance()).commit();

        //Left Menu Animation
        leftAnimation = ObjectAnimator.ofFloat(cardViewMain, "x", 300);
        leftAnimation.setDuration(500);

        leftMenu.setVisibility(View.VISIBLE);
        viewMain.setVisibility(View.GONE);

        ivOpenLeftMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if it is open, to close and if it is close, to open;
                if(leftMenuOpen == false) openLeftMenu();
                else if(leftMenuOpen == true) closeLeftMenu();
            }
        });

        viewMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeLeftMenu();
            }
        });

        rlLefMenuHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leftMenuHomeClick();
            }
        });
        rlLefMenuOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leftMenuOrdersClick();
            }
        });
        rlLefMenuProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leftMenuProfileClick();
            }
        });
        rlLefMenuInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leftMenuInfoClick();
            }
        });


        //Auth
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                firebaseUser = firebaseAuth.getCurrentUser();
                logInFragmentControl(firebaseUser);
            }
        };

        rlLefMenuHome.setClickable(false);
        rlLefMenuInfo.setClickable(false);
        rlLefMenuOrder.setClickable(false);
        rlLefMenuProfile.setClickable(false);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTapShopItem(ShopVO shopVO) {
        closeLeftMenu();
        ShopModel.getobjInstance().addUserSelectedShop(shopVO);
        Intent intent = ShopDetailActivity.newIntent();
        startActivity(intent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    // Left to Right swipe action
                    if (x2 > x1)
                    {
                        openLeftMenu();
                    }

                    // Right to left swipe action
                    else
                    {
                        closeLeftMenu();
                    }

                }
                else
                {
                    // consider as something else - a screen tap for example
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void openLeftMenu(){
        if(leftMenuOpen == false){
            leftAnimation.start();

            animSlideRight = AnimationUtils.loadAnimation(YUMenuBookApp.getContext(), R.anim.slide_right);
            //leftMenu.startAnimation(animSlideRight);
            viewMain.setVisibility(View.VISIBLE);
            leftMenuOpen = true;

            rlLefMenuHome.setClickable(true);
            rlLefMenuInfo.setClickable(true);
            rlLefMenuOrder.setClickable(true);
            rlLefMenuProfile.setClickable(true);
        }
    }

    private void closeLeftMenu(){
        if(leftMenuOpen == true){
            leftAnimation.reverse();

            animSlideLeft = AnimationUtils.loadAnimation(YUMenuBookApp.getContext(), R.anim.slide_left);
            ///leftMenu.startAnimation(animSlideLeft);
            viewMain.setVisibility(View.GONE);
            leftMenuOpen = false;


            /*Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    rlLefMenuHome.setVisibility(View.INVISIBLE);
                    rlLefMenuInfo.setVisibility(View.INVISIBLE);
                    rlLefMenuOrder.setVisibility(View.INVISIBLE);
                    rlLefMenuProfile.setVisibility(View.INVISIBLE);
                }
            }, 3000);*/

            rlLefMenuHome.setClickable(false);
            rlLefMenuInfo.setClickable(false);
            rlLefMenuOrder.setClickable(false);
            rlLefMenuProfile.setClickable(false);
        }
    }

    private void leftMenuHomeClick(){
        rlLefMenuHome.setBackgroundColor(Color.parseColor(LEFT_BG_SELECTED_COLOR));
        rlLefMenuInfo.setBackgroundColor(Color.parseColor(LEFT_BG_COLOR));
        rlLefMenuOrder.setBackgroundColor(Color.parseColor(LEFT_BG_COLOR));
        rlLefMenuProfile.setBackgroundColor(Color.parseColor(LEFT_BG_COLOR));

        isProfileClick = false;
        closeLeftMenu();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, HomeFragment.newInstance()).commit();
    }

    private void leftMenuProfileClick(){
        rlLefMenuHome.setBackgroundColor(Color.parseColor(LEFT_BG_COLOR));
        rlLefMenuInfo.setBackgroundColor(Color.parseColor(LEFT_BG_COLOR));
        rlLefMenuOrder.setBackgroundColor(Color.parseColor(LEFT_BG_COLOR));
        rlLefMenuProfile.setBackgroundColor(Color.parseColor(LEFT_BG_SELECTED_COLOR));

        isProfileClick = true;
        closeLeftMenu();
        logInFragmentControl(firebaseUser);
    }

    private void leftMenuOrdersClick(){
        rlLefMenuHome.setBackgroundColor(Color.parseColor(LEFT_BG_COLOR));
        rlLefMenuInfo.setBackgroundColor(Color.parseColor(LEFT_BG_COLOR));
        rlLefMenuOrder.setBackgroundColor(Color.parseColor(LEFT_BG_SELECTED_COLOR));
        rlLefMenuProfile.setBackgroundColor(Color.parseColor(LEFT_BG_COLOR));

        isProfileClick = false;
        closeLeftMenu();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, OrdersFragment.newInstance()).commit();
    }

    private void leftMenuInfoClick(){
        rlLefMenuHome.setBackgroundColor(Color.parseColor(LEFT_BG_COLOR));
        rlLefMenuInfo.setBackgroundColor(Color.parseColor(LEFT_BG_SELECTED_COLOR));
        rlLefMenuOrder.setBackgroundColor(Color.parseColor(LEFT_BG_COLOR));
        rlLefMenuProfile.setBackgroundColor(Color.parseColor(LEFT_BG_COLOR));

        isProfileClick = false;
        closeLeftMenu();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, InfoFragment.newInstance()).commit();

    }

    private void logInFragmentControl(FirebaseUser firebaseUser){
        if (firebaseUser != null) {
            // User is signed in
            Log.d(Constants.TAG, " Main Activity onAuthStateChanged:signed_in:" + firebaseUser.getUid());
            UserModel.objInstance().saveFirebaseUser(firebaseUser);
            if(isProfileClick) getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, ProfileFragment.newInstance()).commit();

        } else {
            // User is signed out
            Log.d(Constants.TAG, "Main Activity onAuthStateChanged:signed_out");
            UserModel.objInstance().saveFirebaseUser(firebaseUser);
            if(isProfileClick) getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, LogInFragment.newInstance()).commit();

        }
    }

    @Override
    public void onSuccessfulLogIn() {
        Log.d(Constants.TAG, "MainActivity onSuccessful Login");
        if(isProfileClick) getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, ProfileFragment.newInstance()).commit();
    }
}
