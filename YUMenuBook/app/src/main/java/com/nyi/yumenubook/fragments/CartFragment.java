package com.nyi.yumenubook.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.adapters.MenuCartItemAdapter;
import com.nyi.yumenubook.adapters.MenuItemAdapter;
import com.nyi.yumenubook.data.VOs.MenuItem;
import com.nyi.yumenubook.data.models.MenuModel;
import com.nyi.yumenubook.events.DataEvent;
import com.nyi.yumenubook.views.holders.CartMenuItemViewHolder;
import com.nyi.yumenubook.views.holders.MenuItemViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartFragment extends Fragment implements CartMenuItemViewHolder.ControllerCartMenuItem{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    @BindView(R.id.rv_cart_menuItem)
    RecyclerView rvCartMenuItem;

    private MenuCartItemAdapter menuItemAdapter;
    private List<MenuItem> mMenuItemList;


    public CartFragment() {
        // Required empty public constructor
    }


    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        if(mMenuItemList == null){
            mMenuItemList = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, view);

        mMenuItemList = MenuModel.getobjInstance().getCartMenuItemList();
        menuItemAdapter = new MenuCartItemAdapter(mMenuItemList, this);
        rvCartMenuItem.setAdapter(menuItemAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(YUMenuBookApp.getContext(), LinearLayoutManager.VERTICAL, false);
        rvCartMenuItem.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onTapMenuItem(MenuItem menuItem, int position) {
        Toast.makeText(YUMenuBookApp.getContext(), menuItem.getName() + " is removed from your Cart", Toast.LENGTH_SHORT).show();
        mMenuItemList.remove(position);
        menuItemAdapter.removeMenu(position);

    }

    /*
    EventBus Sunscriber
    This method is called when you enter plus sign in MenuItemFragment
    Check MenuItemFragment
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(DataEvent.AddCartEvent event) {
        Log.d("YU", "EventBus Receive " + event.getMenuItem().getName());

        MenuItem menuItem = event.getMenuItem();
        mMenuItemList.add(menuItem);
        menuItemAdapter.addNewMenu(menuItem);
    }
}
