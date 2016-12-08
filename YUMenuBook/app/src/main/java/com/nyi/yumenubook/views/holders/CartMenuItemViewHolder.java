package com.nyi.yumenubook.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.data.VOs.MenuItemVO;
import com.nyi.yumenubook.data.VOs.OrderItemVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 19-Nov-16.
 */

public class CartMenuItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @BindView(R.id.tv_item_cart_menu_name)
    TextView tvItemMenuName;

    @BindView(R.id.tv_item_cart_menu_price)
    TextView tvItemMenuPrice;

    @BindView(R.id.tv_item_cart_menu_quantity)
    TextView tvItemMenuQuantity;

    @BindView(R.id.tv_item_cart_menu_delete)
    ImageView tvItemMenuAdd;

    private ControllerCartMenuItem mControllerCartMenuItem;
    private OrderItemVO orderItemVO;
    private int posistion;

    public CartMenuItemViewHolder(View itemView, ControllerCartMenuItem controllerCartMenuItem) {
        super(itemView);
        this.mControllerCartMenuItem = controllerCartMenuItem;

        ButterKnife.bind(this, itemView);
        tvItemMenuName.setTypeface(YUMenuBookApp.getTextTypeface());
        tvItemMenuPrice.setTypeface(YUMenuBookApp.getTextTypeface());
        tvItemMenuQuantity.setTypeface(YUMenuBookApp.getTextTypeface());
        tvItemMenuAdd.setOnClickListener(this);

    }

    public void bindMenu(OrderItemVO orderItemVO, int position){
        this.orderItemVO = orderItemVO;
        this.posistion = position;
        tvItemMenuName.setText(orderItemVO.getName() + "  x " + orderItemVO.getQuantity());
        tvItemMenuPrice.setText(orderItemVO.getPrice() + "");
        //tvItemMenuQuantity.setText("x " + orderItemVO.getQuantity());
    }

    @Override
    public void onClick(View view) {
        mControllerCartMenuItem.onTapMenuItem(orderItemVO, posistion);
    }

    public interface ControllerCartMenuItem {
        void onTapMenuItem(OrderItemVO orderItemVO, int position);
    }
}
