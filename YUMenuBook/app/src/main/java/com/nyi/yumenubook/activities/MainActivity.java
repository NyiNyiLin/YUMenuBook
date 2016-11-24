package com.nyi.yumenubook.activities;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.data.VOs.ShopVO;
import com.nyi.yumenubook.data.models.ShopModel;
import com.nyi.yumenubook.fragments.HomeFragment;
import com.nyi.yumenubook.views.holders.ShopViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ShopViewHolder.ControllerShopItem{

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

    private ObjectAnimator leftAnimation;
    private ObjectAnimator leftMenuAnimation;
    private Animation animSlideRight;
    private Animation animSlideLeft;
    private boolean leftMenuOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, HomeFragment.newInstance()).commit();

        leftAnimation = ObjectAnimator.ofFloat(
                cardViewMain,
                "x",
                300);
        leftAnimation.setDuration(500);

        leftMenu.setVisibility(View.INVISIBLE);

        ivOpenLeftMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(leftMenuOpen == false){
                    animSlideRight = AnimationUtils.loadAnimation(YUMenuBookApp.getContext(), R.anim.slide_right);
                    leftAnimation.start();
                    leftMenuOpen = true;
                    leftMenu.startAnimation(animSlideRight);
                }else if(leftMenuOpen == true){
                    animSlideLeft = AnimationUtils.loadAnimation(YUMenuBookApp.getContext(), R.anim.slide_left);
                    leftAnimation.reverse();
                    leftMenuOpen = false;
                    leftMenu.startAnimation(animSlideLeft);
                }
            }
        });
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
        ShopModel.getobjInstance().addUserSelectedShop(shopVO);
        Intent intent = ShopDetailActivity.newIntent();
        startActivity(intent);
    }
}
