package com.nyi.yumenubook.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.data.VOs.MenuItem;

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

    @BindView(R.id.tv_item_cart_menu_delete)
    ImageView tvItemMenuAdd;

    private ControllerCartMenuItem mControllerCartMenuItem;
    private MenuItem menuItem;
    private int posistion;

    public CartMenuItemViewHolder(View itemView, ControllerCartMenuItem controllerCartMenuItem) {
        super(itemView);
        this.mControllerCartMenuItem = controllerCartMenuItem;

        ButterKnife.bind(this, itemView);
        tvItemMenuAdd.setOnClickListener(this);

    }

    public void bindMenu(MenuItem menuItem, int position){
        this.menuItem = menuItem;
        this.posistion = position;
        tvItemMenuName.setText(menuItem.getName());
        tvItemMenuPrice.setText(menuItem.getPrice() + "");
    }

    @Override
    public void onClick(View view) {
        mControllerCartMenuItem.onTapMenuItem(menuItem, posistion);
    }

    public interface ControllerCartMenuItem {
        void onTapMenuItem(MenuItem menuItem, int position);
    }
}
