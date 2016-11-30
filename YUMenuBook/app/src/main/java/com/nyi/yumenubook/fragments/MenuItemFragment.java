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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.adapters.MenuItemAdapter;
import com.nyi.yumenubook.data.VOs.MenuItemVO;
import com.nyi.yumenubook.data.models.MenuModel;
import com.nyi.yumenubook.events.DataEvent;
import com.nyi.yumenubook.utils.Constants;
import com.nyi.yumenubook.utils.FirebaseUtil;
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
    private static final String ARG_SHOP_ID = "shopID";
    private static final String ARG_TYPE = "type";

    private String mShopID;
    private String mShopType;

    @BindView(R.id.rv_menuItem)
    RecyclerView rvMenuItem;


    private List<MenuItemVO> mMenuItemVOList;
    private MenuItemAdapter menuItemAdapter;

    public MenuItemFragment() {
        // Required empty public constructor
    }

    public static MenuItemFragment newInstance(String shopID, String type) {
        MenuItemFragment fragment = new MenuItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOP_ID, shopID);
        args.putString(ARG_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mShopID = getArguments().getString(ARG_SHOP_ID);
            mShopType = getArguments().getString(ARG_TYPE);
        }

        if(mMenuItemVOList == null){
            mMenuItemVOList = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_item, container, false);
        ButterKnife.bind(this, view);

        menuItemAdapter = new MenuItemAdapter(mMenuItemVOList, this);
        rvMenuItem.setAdapter(menuItemAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(YUMenuBookApp.getContext(), LinearLayoutManager.VERTICAL, false);
        rvMenuItem.setLayoutManager(layoutManager);

        getMenuItemFromFirebase();

        return view;
    }

    @Override
    public void onTapMenuItem(MenuItemVO menuItemVO) {
        Log.d("YU", menuItemVO.getName());
        broadcasMenuItemToCartWithEventBus(menuItemVO);

        Toast.makeText(YUMenuBookApp.getContext(), menuItemVO.getName() + " is added to your Cart", Toast.LENGTH_SHORT).show();
        MenuModel.getobjInstance().addMenuItemToCartMenuList(menuItemVO);
    }

    private void getMenuItemFromFirebase(){
        DatabaseReference ref = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.DETAIL).child(mShopID).child(Constants.MENUITEM).child(mShopType);

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(Constants.TAG, "onMenuItemChildAdded:" + dataSnapshot.getKey());

                MenuItemVO menuItemVO = dataSnapshot.getValue(MenuItemVO.class);
                menuItemVO.setMenuItemID(dataSnapshot.getKey());
                menuItemAdapter.addNewMenu(menuItemVO);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(Constants.TAG, "onMenuItemChildChildren:" + dataSnapshot.getKey());

                MenuItemVO menuItemVO = dataSnapshot.getValue(MenuItemVO.class);
                menuItemVO.setMenuItemID(dataSnapshot.getKey());
                menuItemAdapter.changeMenu(menuItemVO);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(Constants.TAG, "onMenuItemChildMoved:" + dataSnapshot.getKey());

                String removeMenuID = dataSnapshot.getKey();
                menuItemAdapter.removeMenu(removeMenuID);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(Constants.TAG, "onChildMoved:" + dataSnapshot.getKey());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        ref.addChildEventListener(childEventListener);
    }

    private void dummyData() {
        for(int i=0; i<10; i++){
            mMenuItemVOList.add(new MenuItemVO("Fried Rice " + i, 1300));
        }
    }


    /*
        Broadcast with eventbus
        Check CartFragment
        It receives this broadcast
     */
    private void broadcasMenuItemToCartWithEventBus(MenuItemVO menuItemVO) {
        EventBus.getDefault().post(new DataEvent.AddCartEvent("Cart Added", menuItemVO));
    }
}
