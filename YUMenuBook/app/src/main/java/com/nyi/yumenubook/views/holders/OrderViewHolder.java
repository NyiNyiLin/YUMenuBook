package com.nyi.yumenubook.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.data.VOs.OrderVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 04-Dec-16.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tv_item_order_shop_name)
    TextView tvShopName;

    @BindView(R.id.tv_item_order_date)
    TextView tvDate;

    @BindView(R.id.tv_item_order_id)
    TextView tvOrderId;

    @BindView(R.id.tv_item_order_time)
    TextView tvOrderTime;

    @BindView(R.id.btn_item_order)
    Button btnOrder;

    public OrderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        tvShopName.setTypeface(YUMenuBookApp.getTextTypeface());
        tvOrderId.setTypeface(YUMenuBookApp.getTextTypeface());
        tvDate.setTypeface(YUMenuBookApp.getTextTypeface());
        tvOrderTime.setTypeface(YUMenuBookApp.getTextTypeface());
    }

    public void bindData(OrderVO orderVO){
        tvShopName.setText(orderVO.getShopName());
        tvOrderId.setText(orderVO.getOrderID());
        tvDate.setText(orderVO.getDate());
        tvOrderTime.setText(orderVO.getTime());
    }
}
