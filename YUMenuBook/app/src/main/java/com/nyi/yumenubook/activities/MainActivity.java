package com.nyi.yumenubook.activities;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.phenotype.Flag;
import com.nyi.yumenubook.R;
import com.nyi.yumenubook.data.VOs.ShopVO;
import com.nyi.yumenubook.data.models.ShopModel;
import com.nyi.yumenubook.fragments.HomeFragment;
import com.nyi.yumenubook.views.holders.ShopViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ShopViewHolder.ControllerShopItem, NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.main_frame)
    FrameLayout frameLayout;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.cl_main)
    CoordinatorLayout clMain;

    private ObjectAnimator leftAnimation;
    private boolean leftMenuOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_left_menu_24dp);

        }

        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, HomeFragment.newInstance()).commit();

        leftAnimation = ObjectAnimator.ofFloat(
                clMain,
                "x",
                350);
        leftAnimation.setDuration(500);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                if(leftMenuOpen == false){
                    leftAnimation.start();
                    leftMenuOpen = true;
                }else if(leftMenuOpen == true){
                    leftAnimation.reverse();
                    leftMenuOpen = false;
                }
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTapShopItem(ShopVO shopVO) {
        ShopModel.getobjInstance().addUserSelectedShop(shopVO);
        Intent intent = ShopDetailActivity.newIntent();
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if(leftMenuOpen == true){
            leftAnimation.reverse();
            leftMenuOpen = false;
        }
        int id = item.getItemId();
        switch (id){
            case R.id.menu_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, HomeFragment.newInstance()).commit();
                return true;
        }
        return false;
    }
}
