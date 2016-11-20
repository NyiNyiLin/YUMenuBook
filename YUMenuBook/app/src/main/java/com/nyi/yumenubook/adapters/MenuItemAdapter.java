package com.nyi.yumenubook.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.data.VOs.MenuItem;
import com.nyi.yumenubook.data.VOs.ShopVO;
import com.nyi.yumenubook.views.holders.MenuItemViewHolder;
import com.nyi.yumenubook.views.holders.ShopViewHolder;

import java.util.List;

/**
 * Created by IN-3442 on 21-Oct-16.
 */

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemViewHolder> {
    private LayoutInflater inflater;
    private List<MenuItem> mMenuItemList;
    private MenuItemViewHolder.ControllerMenuItem controllerMenuItem;

    public MenuItemAdapter(List<MenuItem> menuItemList, MenuItemViewHolder.ControllerMenuItem controllerMenuItem) {
        this.mMenuItemList = menuItemList;
        this.controllerMenuItem = controllerMenuItem;
        inflater = LayoutInflater.from(YUMenuBookApp.getContext());
    }


    @Override
    public MenuItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view_menu, parent, false);
        MenuItemViewHolder menuItemViewHolder = new MenuItemViewHolder(view, controllerMenuItem);
        return menuItemViewHolder;
    }

    @Override
    public void onBindViewHolder(MenuItemViewHolder holder, int position) {
        holder.bindMenu(mMenuItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMenuItemList.size();
    }

    public void addNewMenu(MenuItem menuItem){
        mMenuItemList.add(menuItem);
        notifyDataSetChanged();
    }
}
