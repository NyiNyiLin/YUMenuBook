package com.nyi.yumenubook.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by IN-3442 on 04-Dec-16.
 */

public class OrdersFragment extends Fragment {

    public OrdersFragment(){

    }

    public static Fragment newInstance(){
        OrdersFragment fragment = new OrdersFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate()

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
