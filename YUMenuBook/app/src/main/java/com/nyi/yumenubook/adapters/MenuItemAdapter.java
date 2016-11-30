package com.nyi.yumenubook.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.data.VOs.MenuItemVO;
import com.nyi.yumenubook.views.holders.MenuItemViewHolder;

import java.util.List;

/**
 * Created by IN-3442 on 21-Oct-16.
 */

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemViewHolder> {
    private LayoutInflater inflater;
    private List<MenuItemVO> mMenuItemVOList;
    private MenuItemViewHolder.ControllerMenuItem controllerMenuItem;

    public MenuItemAdapter(List<MenuItemVO> menuItemVOList, MenuItemViewHolder.ControllerMenuItem controllerMenuItem) {
        this.mMenuItemVOList = menuItemVOList;
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
        holder.bindMenu(mMenuItemVOList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMenuItemVOList.size();
    }

    public void addNewMenu(MenuItemVO menuItemVO){
        mMenuItemVOList.add(menuItemVO);
        notifyItemInserted(mMenuItemVOList.size());
    }

    public void changeMenu(MenuItemVO newMenuItemVO){
        int position = 0;
        for(int a=0; a<getItemCount(); a++ ){
            MenuItemVO menuItemVO = mMenuItemVOList.get(a);
            if(menuItemVO.getMenuItemID().equals(newMenuItemVO.getMenuItemID())){
                position = a;
            }
        }
        mMenuItemVOList.remove(position);
        mMenuItemVOList.add(position, newMenuItemVO);
        notifyItemChanged(position);
    }

    public void removeMenu(String removedMenuID){
        int position = 0;
        for(int a=0; a<getItemCount(); a++ ){
            MenuItemVO menuItemVO = mMenuItemVOList.get(a);
            if(menuItemVO.getMenuItemID().equals(removedMenuID)){
                position = a;
            }
        }
        mMenuItemVOList.remove(position);
        notifyItemRemoved(position);
    }
}
