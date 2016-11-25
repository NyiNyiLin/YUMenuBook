package com.nyi.yumenubook.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.nyi.yumenubook.fragments.MenuItemFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-3442 on 30-Jul-16.
 */
public class MenuFragmentPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> mFragments = new ArrayList<>();
    List<String> mFragmentsTitle = new ArrayList<>();

    public MenuFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

/*    @Override
    public float getPageWidth (int position) {
        return 1f;
    }*/

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentsTitle.get(position);
    }

    public void addTab(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentsTitle.add(title);
    }
}
