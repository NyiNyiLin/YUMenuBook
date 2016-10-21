package com.nyi.yumenubook.fragments;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.adapters.ShopAdapter;
import com.nyi.yumenubook.data.VOs.ShopVO;
import com.nyi.yumenubook.views.holders.ShopViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.rv_science_canteen)
    RecyclerView rvScienceCanteen;

    @BindView(R.id.rv_taungnoo_canteen)
    RecyclerView rvTaungnooCanteen;

    private List<ShopVO> mScienceShopList;
    private List<ShopVO> mTaungnooShopList;

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
        if(mScienceShopList == null){
            mScienceShopList = new ArrayList<>();
        }
        if(mTaungnooShopList == null){
            mTaungnooShopList = new ArrayList<>();
        }

        assignDummyData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        ShopAdapter scienceAdapter = new ShopAdapter(mScienceShopList, mControllerShopItem);
        rvScienceCanteen.setAdapter(scienceAdapter);

        ShopAdapter taungNooAdapter = new ShopAdapter(mTaungnooShopList, mControllerShopItem);
        rvTaungnooCanteen.setAdapter(taungNooAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(YUMenuBookApp.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvScienceCanteen.setLayoutManager(layoutManager);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(YUMenuBookApp.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvTaungnooCanteen.setLayoutManager(layoutManager2);

        return view;
    }

    private void assignDummyData(){
        mScienceShopList.add(new ShopVO("Shwe Pha Lar"));
        mScienceShopList.add(new ShopVO("Shan Ma Lay"));
        mScienceShopList.add(new ShopVO("U Chit"));

        mTaungnooShopList.add(new ShopVO("Shop 1"));
        mTaungnooShopList.add(new ShopVO("Shop 2"));
        mTaungnooShopList.add(new ShopVO("Shop 3"));
        mTaungnooShopList.add(new ShopVO("Shop 4"));
    }


}
