package com.nyi.yumenubook.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.data.VOs.MenuItemVO;
import com.nyi.yumenubook.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 19-Nov-16.
 */

public class MenuItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @BindView(R.id.tv_item_menu_name)
    TextView tvItemMenuName;

    @BindView(R.id.tv_item_menu_price)
    TextView tvItemMenuPrice;

    @BindView(R.id.tv_item_menu_add)
    ImageView ivItemMenuAdd;

    private ControllerMenuItem mControllerMenuItem;
    private MenuItemVO menuItemVO;

    public MenuItemViewHolder(View itemView, ControllerMenuItem controllerMenuItem) {
        super(itemView);
        this.mControllerMenuItem = controllerMenuItem;

        ButterKnife.bind(this, itemView);
        //tvItemMenuName.setTypeface(YUMenuBookApp.getTextTypeface());
        tvItemMenuPrice.setTypeface(YUMenuBookApp.getTextTypeface());

        ivItemMenuAdd.setOnClickListener(this);

    }

    public void bindMenu(MenuItemVO menuItemVO){
        this.menuItemVO = menuItemVO;
        tvItemMenuName.setText(menuItemVO.getName());
        tvItemMenuPrice.setText(menuItemVO.getPrice() + "");

        if(menuItemVO.getAvailable() == Constants.AVAILABLE) ivItemMenuAdd.setVisibility(View.VISIBLE);
        else if(menuItemVO.getAvailable() == Constants.NOT_AVAILABLE) ivItemMenuAdd.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        mControllerMenuItem.onTapMenuItem(menuItemVO);
    }

    public interface ControllerMenuItem{
        void onTapMenuItem(MenuItemVO menuItemVO);
    }
}
