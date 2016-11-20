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
import com.nyi.yumenubook.adapters.MenuItemAdapter;
import com.nyi.yumenubook.data.VOs.MenuItem;
import com.nyi.yumenubook.data.models.MenuModel;
import com.nyi.yumenubook.events.DataEvent;
import com.nyi.yumenubook.views.holders.MenuItemViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuItemFragment extends Fragment implements MenuItemViewHolder.ControllerMenuItem{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @BindView(R.id.rv_menuItem)
    RecyclerView rvMenuItem;

    private List<MenuItem> mMenuItemList;

    public MenuItemFragment() {
        // Required empty public constructor
    }

    public static MenuItemFragment newInstance(String param1, String param2) {
        MenuItemFragment fragment = new MenuItemFragment();
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
        dummyData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_item, container, false);
        ButterKnife.bind(this, view);

        MenuItemAdapter menuItemAdapter = new MenuItemAdapter(mMenuItemList, this);
        rvMenuItem.setAdapter(menuItemAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(YUMenuBookApp.getContext(), LinearLayoutManager.VERTICAL, false);
        rvMenuItem.setLayoutManager(layoutManager);


        return view;
    }

    @Override
    public void onTapMenuItem(MenuItem menuItem) {
        Log.d("YU", menuItem.getName());
        broadcasMenuItemToCartWithEventBus(menuItem);

        Toast.makeText(YUMenuBookApp.getContext(), menuItem.getName() + " is added to your Cart", Toast.LENGTH_SHORT).show();
        MenuModel.getobjInstance().addMenuItemToCartMenuList(menuItem);
    }

    private void dummyData() {
        for(int i=0; i<10; i++){
            mMenuItemList.add(new MenuItem("Fried Rice " + i, 1300));
        }
    }


    /*
        Broadcast with eventbus
        Check CartFragment
        It receives this broadcast
     */
    private void broadcasMenuItemToCartWithEventBus(MenuItem menuItem) {
        EventBus.getDefault().post(new DataEvent.AddCartEvent("Cart Added", menuItem));
    }
}
