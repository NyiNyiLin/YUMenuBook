package com.nyi.yumenubook.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.adapters.MenuFragmentPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MenuFragment extends Fragment {
    @BindView(R.id.tab_type)
    TabLayout tabType;

    @BindView(R.id.pager_item)
    ViewPager pagerItem;

    MenuFragmentPagerAdapter menuFragmentPagerAdapter;

    public MenuFragment() {
        // Required empty public constructor
    }

    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);

        //getFragmentManager().beginTransaction().replace(R.id.fl, MenuItemFragment.newInstance("j","l")).commit();


        menuFragmentPagerAdapter = new MenuFragmentPagerAdapter(getChildFragmentManager());

        menuFragmentPagerAdapter.addTab(MenuItemFragment.newInstance("a","a"), "aa");
        menuFragmentPagerAdapter.addTab(MenuItemFragment.newInstance("b","b"), "bb");
        pagerItem.setAdapter(menuFragmentPagerAdapter);

        Log.d("aa", menuFragmentPagerAdapter.getCount() + "");

        //to create all of the fragment
        pagerItem.setOffscreenPageLimit(menuFragmentPagerAdapter.getCount());

        pagerItem.setClipToPadding(false);
        pagerItem.setPageMargin(12);

        //to connect with tab layout and view pager
        tabType.setupWithViewPager(pagerItem);
        return view;
    }

}
