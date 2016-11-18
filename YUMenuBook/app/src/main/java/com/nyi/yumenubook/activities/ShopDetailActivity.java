package com.nyi.yumenubook.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.adapters.MenuFragmentPagerAdapter;
import com.nyi.yumenubook.fragments.HomeFragment;
import com.nyi.yumenubook.fragments.MenuFragment;
import com.nyi.yumenubook.fragments.MenuItemFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopDetailActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fl_detail)
    FrameLayout fl_detail;

    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_detail, MenuFragment.newInstance()).commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_cart:
                Toast.makeText(YUMenuBookApp.getContext(), "Cart", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_detail, MenuItemFragment.newInstance("j","l")).commit();
                break;
            case R.id.action_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_detail, MenuFragment.newInstance()).commit();
                Toast.makeText(YUMenuBookApp.getContext(), "Menu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_review:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_detail, MenuItemFragment.newInstance("j","l")).commit();
                Toast.makeText(YUMenuBookApp.getContext(), "Review", Toast.LENGTH_SHORT).show();
                break;
        }

        return false;
    }
}
