package com.nyi.yumenubook.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.data.VOs.MenuItem;
import com.nyi.yumenubook.views.holders.CartMenuItemViewHolder;

import java.util.List;

/**
 * Created by IN-3442 on 21-Oct-16.
 */

public class MenuCartItemAdapter extends RecyclerView.Adapter<CartMenuItemViewHolder> {
    private LayoutInflater inflater;
    private List<MenuItem> mMenuItemList;
    private CartMenuItemViewHolder.ControllerCartMenuItem controllerCartMenuItem;

    public MenuCartItemAdapter(List<MenuItem> menuItemList, CartMenuItemViewHolder.ControllerCartMenuItem controllerCartMenuItem) {
        this.mMenuItemList = menuItemList;
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
        holder.bindMenu(mMenuItemList.get(position), position);
    }


    @Override
    public int getItemCount() {
        return mMenuItemList.size();
    }

    public void addNewMenu(MenuItem menuItem){
        mMenuItemList.add(menuItem);
        notifyDataSetChanged();


    }

    public void removeMenu(int position){
        mMenuItemList.remove(position);
        notifyItemRemoved(position);
    }
}
