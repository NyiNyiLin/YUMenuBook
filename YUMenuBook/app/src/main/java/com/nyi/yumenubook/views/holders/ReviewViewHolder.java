package com.nyi.yumenubook.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.data.VOs.ReviewVO;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by IN-3442 on 03-Dec-16.
 */

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_item_review_image)
    CircleImageView ivReviewImage;

    @BindView(R.id.tv_item_review_name)
    TextView tvReviewName;

    @BindView(R.id.tv_item_review_review)
    TextView tvReviewReview;

    @BindView(R.id.tv_item_review_date)
    TextView tvReviewDate;

    public ReviewViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        tvReviewName.setTypeface(YUMenuBookApp.getTextTypeface());
        tvReviewReview.setTypeface(YUMenuBookApp.getTextTypeface());
        tvReviewDate.setTypeface(YUMenuBookApp.getTextTypeface());
    }

    public void bindView(ReviewVO reviewVO){
        tvReviewName.setText(reviewVO.getReviewName());
        tvReviewDate.setText(reviewVO.getReviewDate());
        tvReviewReview.setText(reviewVO.getReviewReview());

        Glide.with(YUMenuBookApp.getContext())
                .load(reviewVO.getReviewPhotoURL())
                .asBitmap().centerCrop()
                .placeholder(R.drawable.ic_camera_black_24dp)
                .error(R.drawable.ic_camera_black_24dp)
                .into(ivReviewImage);
    }
}
