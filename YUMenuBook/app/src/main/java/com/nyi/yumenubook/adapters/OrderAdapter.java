package com.nyi.yumenubook.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.data.VOs.OrderVO;
import com.nyi.yumenubook.data.VOs.ReviewVO;
import com.nyi.yumenubook.views.holders.OrderViewHolder;
import com.nyi.yumenubook.views.holders.ReviewViewHolder;

import java.util.List;

/**
 * Created by IN-3442 on 03-Dec-16.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    private List<OrderVO> orderVOList;
    private LayoutInflater inflater;

    public OrderAdapter(List<OrderVO> orderVOList) {
        this.orderVOList = orderVOList;

        inflater = LayoutInflater.from(YUMenuBookApp.getContext());
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view_order, parent, false);
        OrderViewHolder orderViewHolder = new OrderViewHolder(view);
        return orderViewHolder;
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        holder.bindData(orderVOList.get(position));
    }


    @Override
    public int getItemCount() {
        return orderVOList.size();
    }

    public void addNewOrder(OrderVO orderVO){
        orderVOList.add(0, orderVO);
        notifyItemInserted(0);
    }
}
