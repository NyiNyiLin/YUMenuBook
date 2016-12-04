package com.nyi.yumenubook.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.adapters.OrderAdapter;
import com.nyi.yumenubook.data.VOs.OrderVO;
import com.nyi.yumenubook.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 04-Dec-16.
 */

public class OrdersFragment extends Fragment {

    @BindView(R.id.rv_orders)
    RecyclerView rvOrders;

    private List<OrderVO> mOrderVOList;
    private OrderAdapter mOrderAdapter;

    public OrdersFragment(){

    }

    public static Fragment newInstance(){
        OrdersFragment fragment = new OrdersFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mOrderVOList == null) mOrderVOList = new ArrayList<>();
        setDummyData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders,container, false);
        ButterKnife.bind(this, view);

        mOrderAdapter = new OrderAdapter(mOrderVOList);
        rvOrders.setAdapter(mOrderAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(YUMenuBookApp.getContext(), LinearLayoutManager.VERTICAL, false);
        rvOrders.setLayoutManager(layoutManager);

        return view;
    }

    private void setDummyData(){
        mOrderVOList.add(new OrderVO("Shan Ma Lay", "123", "12/12/16", "12:30 pm"));
        mOrderVOList.add(new OrderVO("Shan Ma Lay", "123", "12/12/16", "12:30 pm"));
        mOrderVOList.add(new OrderVO("Shan Ma Lay", "123", "12/12/16", "12:30 pm"));
        mOrderVOList.add(new OrderVO("Shan Ma Lay", "123", "12/12/16", "12:30 pm"));
        mOrderVOList.add(new OrderVO("Shan Ma Lay", "123", "12/12/16", "12:30 pm"));
        mOrderVOList.add(new OrderVO("Shan Ma Lay", "123", "12/12/16", "12:30 pm"));
        mOrderVOList.add(new OrderVO("Shan Ma Lay", "123", "12/12/16", "12:30 pm"));
    }
}
