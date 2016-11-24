package com.nyi.yumenubook.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.adapters.MenuItemAdapter;
import com.nyi.yumenubook.data.VOs.MenuItem;
import com.nyi.yumenubook.data.VOs.ShopVO;
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

import static com.google.android.gms.internal.zzs.TAG;

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


    private List<MenuItem> mMenuItemList;
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

        if(mMenuItemList == null){
            mMenuItemList = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_item, container, false);
        ButterKnife.bind(this, view);

        menuItemAdapter = new MenuItemAdapter(mMenuItemList, this);
        rvMenuItem.setAdapter(menuItemAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(YUMenuBookApp.getContext(), LinearLayoutManager.VERTICAL, false);
        rvMenuItem.setLayoutManager(layoutManager);

        getMenuItemFromFirebase();

        return view;
    }

    @Override
    public void onTapMenuItem(MenuItem menuItem) {
        Log.d("YU", menuItem.getName());
        broadcasMenuItemToCartWithEventBus(menuItem);

        Toast.makeText(YUMenuBookApp.getContext(), menuItem.getName() + " is added to your Cart", Toast.LENGTH_SHORT).show();
        MenuModel.getobjInstance().addMenuItemToCartMenuList(menuItem);
    }

    private void getMenuItemFromFirebase(){
        DatabaseReference ref = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.DETAIL).child(mShopID).child(Constants.MENUITEM).child(mShopType);

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(Constants.TAG, "onMenuItemChildAdded:" + dataSnapshot.getKey());

                MenuItem menuItem = dataSnapshot.getValue(MenuItem.class);
                menuItem.setMenuItemID(dataSnapshot.getKey());
                menuItemAdapter.addNewMenu(menuItem);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(Constants.TAG, "onMenuItemChildChildren:" + dataSnapshot.getKey());

                MenuItem menuItem = dataSnapshot.getValue(MenuItem.class);
                menuItem.setMenuItemID(dataSnapshot.getKey());
                menuItemAdapter.changeMenu(menuItem);
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
