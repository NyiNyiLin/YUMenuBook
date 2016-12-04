package com.nyi.yumenubook.fragments;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.adapters.ShopAdapter;
import com.nyi.yumenubook.data.VOs.ShopVO;
import com.nyi.yumenubook.utils.Constants;
import com.nyi.yumenubook.utils.FirebaseUtil;
import com.nyi.yumenubook.views.holders.ShopViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.rv_shop)
    RecyclerView rvShop;

    private List<ShopVO> mShopList;
    private ShopAdapter shopAdapter;

    private ShopViewHolder.ControllerShopItem mControllerShopItem;

    public static Fragment newInstance(){
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mControllerShopItem = (ShopViewHolder.ControllerShopItem) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mShopList == null){
            mShopList = new ArrayList<>();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        shopAdapter = new ShopAdapter(mShopList, mControllerShopItem);
        rvShop.setAdapter(shopAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(YUMenuBookApp.getContext(), LinearLayoutManager.VERTICAL, false);
        rvShop.setLayoutManager(layoutManager);

        getShopDataFromFirebase();

        return view;
    }

    private void assignDummyData(){
        mShopList.add(new ShopVO("Shwe Pha Lar"));
        mShopList.add(new ShopVO("Shan Ma Lay"));
        mShopList.add(new ShopVO("U Chit"));

    }

    private void getShopDataFromFirebase(){
        DatabaseReference ref = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.SHOP);

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(Constants.TAG, "Home Fragment onChildAdded:" + dataSnapshot.getKey());

                ShopVO shopVO = dataSnapshot.getValue(ShopVO.class);

                shopAdapter.addNewShop(shopVO);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(Constants.TAG, "Home Fragment onChildChanged:" + dataSnapshot.getKey());

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(Constants.TAG, "Home Fragment onChildRemoved:" + dataSnapshot.getKey());

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(Constants.TAG, "Home Fragment onChildMoved:" + dataSnapshot.getKey());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        ref.addChildEventListener(childEventListener);

    }
}
