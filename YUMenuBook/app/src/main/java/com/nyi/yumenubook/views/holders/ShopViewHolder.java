package com.nyi.yumenubook.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.data.VOs.ShopVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 21-Oct-16.
 */

public class ShopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.tv_shop_name)
    TextView tvShopName;

    @BindView(R.id.tv_shop_place)
    TextView tvShopPlace;

    @BindView(R.id.tv_shop_rating)
    TextView tvSHopRating;

    @BindView(R.id.iv_shop_image)
    ImageView ivShopImage;

    private ControllerShopItem mControllerShopItem;
    private ShopVO mShopVO;

    public ShopViewHolder(View itemView, ControllerShopItem controllerShopItem) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        tvShopName.setTypeface(YUMenuBookApp.getTextTypeface());
        tvShopPlace.setTypeface(YUMenuBookApp.getTextTypeface());
        tvSHopRating.setTypeface(YUMenuBookApp.getTextTypeface());

        itemView.setOnClickListener(this);

        this.mControllerShopItem = controllerShopItem;
    }

    public void bindShop(ShopVO shopVO){
        this.mShopVO = shopVO;
        tvShopName.setText(shopVO.getName());
        tvShopPlace.setText(shopVO.getPlace());

        Glide.with(YUMenuBookApp.getContext())
                .load(shopVO.getImageLink())
                .asBitmap().centerCrop()
                .placeholder(R.drawable.ic_bg_30)
                .error(R.drawable.ic_bg_30)
                .into(ivShopImage);
    }

    @Override
    public void onClick(View view) {
        mControllerShopItem.onTapShopItem(mShopVO);
    }

    public interface ControllerShopItem{
        void onTapShopItem(ShopVO shopVO);
    }
}
