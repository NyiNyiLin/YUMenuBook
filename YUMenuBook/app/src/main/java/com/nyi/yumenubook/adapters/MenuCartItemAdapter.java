package com.nyi.yumenubook.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.data.VOs.MenuItemVO;
import com.nyi.yumenubook.views.holders.CartMenuItemViewHolder;

import java.util.List;

/**
 * Created by IN-3442 on 21-Oct-16.
 */

public class MenuCartItemAdapter extends RecyclerView.Adapter<CartMenuItemViewHolder> {
    private LayoutInflater inflater;
    private List<MenuItemVO> mMenuItemVOList;
    private CartMenuItemViewHolder.ControllerCartMenuItem controllerCartMenuItem;

    public MenuCartItemAdapter(List<MenuItemVO> menuItemVOList, CartMenuItemViewHolder.ControllerCartMenuItem controllerCartMenuItem) {
        this.mMenuItemVOList = menuItemVOList;
        this.controllerCartMenuItem = controllerCartMenuItem;
        inflater = LayoutInflater.from(YUMenuBookApp.getContext());
    }


    @Override
    public CartMenuItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view_cart_menu, parent, false);
        CartMenuItemViewHolder menuItemViewHolder = new CartMenuItemViewHolder(view, controllerCartMenuItem);
        return menuItemViewHolder;
    }

    @Override
    public void onBindViewHolder(CartMenuItemViewHolder holder, int position) {
        holder.bindMenu(mMenuItemVOList.get(position), position);
    }


    @Override
    public int getItemCount() {
        return mMenuItemVOList.size();
    }

    public void addNewMenu(MenuItemVO menuItemVO){
        mMenuItemVOList.add(menuItemVO);
        notifyDataSetChanged();

    }

    public void removeMenu(int position){
        mMenuItemVOList.remove(position);
        notifyItemRemoved(position);
    }
}
