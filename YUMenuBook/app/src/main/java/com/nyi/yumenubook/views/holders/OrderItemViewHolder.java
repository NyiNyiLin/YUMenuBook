package com.nyi.yumenubook.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.data.VOs.OrderItemVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 08-Dec-16.
 */

public class OrderItemViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tv_item_view_order_item_name)
    TextView tvName;

    @BindView(R.id.tv_item_view_order_item_quantity)
    TextView tvQuantity;

    @BindView(R.id.tv_item_view_order_item_price)
    TextView tvPrice;

    public OrderItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        tvName.setTypeface(YUMenuBookApp.getTextTypeface());
        tvPrice.setTypeface(YUMenuBookApp.getTextTypeface());
        tvQuantity.setTypeface(YUMenuBookApp.getTextTypeface());
    }

    public void bindData(OrderItemVO orderItemVO){
        tvName.setText(orderItemVO.getName());
        tvQuantity.setText("x " + orderItemVO.getQuantity());
        tvPrice.setText(orderItemVO.getPrice() + "");
    }

}
