package com.nyi.yumenubook.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.data.VOs.ReviewVO;
import com.nyi.yumenubook.data.VOs.ShopVO;
import com.nyi.yumenubook.views.holders.ReviewViewHolder;

import java.util.List;

/**
 * Created by IN-3442 on 03-Dec-16.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {
    private List<ReviewVO> reviewVOList;
    private LayoutInflater inflater;

    public ReviewAdapter(List<ReviewVO> reviewVOList) {
        this.reviewVOList = reviewVOList;

        inflater = LayoutInflater.from(YUMenuBookApp.getContext());
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view_review, parent, false);
        ReviewViewHolder reviewViewHolder = new ReviewViewHolder(view);
        return reviewViewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        holder.bindView(reviewVOList.get(position));
    }

    @Override
    public int getItemCount() {
        return reviewVOList.size();
    }

    public void addNewReview(ReviewVO reviewVO){
        reviewVOList.add(0, reviewVO);
        notifyItemInserted(0);
    }
}
