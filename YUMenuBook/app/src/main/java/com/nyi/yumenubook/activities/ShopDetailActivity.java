package com.nyi.yumenubook.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.adapters.MenuFragmentPagerAdapter;
import com.nyi.yumenubook.data.VOs.ShopVO;
import com.nyi.yumenubook.data.models.ShopModel;
import com.nyi.yumenubook.fragments.MenuItemFragment;
import com.nyi.yumenubook.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopDetailActivity extends AppCompatActivity{
    @BindView(R.id.tv_shop_detail_title)
    TextView tvShopDetailTitle;

    @BindView(R.id.pager_item)
    ViewPager pagerItem;

    @BindView(R.id.tab_type)
    TabLayout tabType;

    //private final String ARG_
    private ShopVO shopVO;
    private List<String> shopTypeList;
    private String shopid;
    private MenuFragmentPagerAdapter menuFragmentPagerAdapter;

    public static Intent newIntent() {
        Intent newIntent= new Intent(YUMenuBookApp.getContext(), ShopDetailActivity.class);
        return newIntent;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this, this);

        shopVO = ShopModel.getobjInstance().getShopVO();
        shopTypeList = shopVO.getType();
        shopid = shopVO.getShopID();

        tvShopDetailTitle.setText(shopVO.getName());

        tvShopDetailTitle.setTypeface(YUMenuBookApp.getTitleTypeface());

        menuFragmentPagerAdapter = new MenuFragmentPagerAdapter(getSupportFragmentManager());

        for(String s: shopTypeList){
            if(!s.isEmpty())menuFragmentPagerAdapter.addTab(MenuItemFragment.newInstance(shopid, s), s);
        }
        pagerItem.setAdapter(menuFragmentPagerAdapter);
        tabType.setupWithViewPager(pagerItem);

        Log.d(Constants.TAG, menuFragmentPagerAdapter.getCount() + "");

        //to create all of the fragment
        pagerItem.setOffscreenPageLimit(menuFragmentPagerAdapter.getCount());

        //pagerItem.setClipToPadding(false);
        //pagerItem.setPageMargin(12);
    }

    public void back(View v){
        this.finish();
    }

    public void cartClick(View view){
        Intent intent = CartActivity.newIntent();
        startActivity(intent);
    }
}
