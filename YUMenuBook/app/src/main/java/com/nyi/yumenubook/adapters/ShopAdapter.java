package com.nyi.yumenubook.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.data.VOs.ShopVO;
import com.nyi.yumenubook.views.holders.ShopViewHolder;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by IN-3442 on 21-Oct-16.
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopViewHolder> {
    private LayoutInflater inflater;
    private List<ShopVO> mShopVOList;
    private ShopViewHolder.ControllerShopItem mControllerShopItem;

    public ShopAdapter(List<ShopVO> mShopVOList, ShopViewHolder.ControllerShopItem mControllerShopItem) {
        this.mShopVOList = mShopVOList;
        this.mControllerShopItem = mControllerShopItem;
        inflater = LayoutInflater.from(YUMenuBookApp.getContext());
    }

    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_view_shop, parent, false);
        ShopViewHolder shopViewHolder = new ShopViewHolder(itemView, mControllerShopItem);
        return shopViewHolder;
    }

    @Override
    public void onBindViewHolder(ShopViewHolder holder, int position) {
        holder.bindShop(mShopVOList.get(position));
    }

    @Override
    public int getItemCount() {
        return mShopVOList.size();
    }

    public void addNewShop(ShopVO shopVO){
        mShopVOList.add(shopVO);
        notifyDataSetChanged();
    }
}
