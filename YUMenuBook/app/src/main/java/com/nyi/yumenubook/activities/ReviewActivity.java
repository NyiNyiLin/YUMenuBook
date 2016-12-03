package com.nyi.yumenubook.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.adapters.ReviewAdapter;
import com.nyi.yumenubook.adapters.ShopAdapter;
import com.nyi.yumenubook.data.VOs.ReviewVO;
import com.nyi.yumenubook.data.models.UserModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewActivity extends AppCompatActivity {

    @BindView(R.id.tv_review_title)
    TextView tvReviewTitle;

    @BindView(R.id.rv_review_item)
    RecyclerView rvReviewItem;

    private ReviewAdapter reviewAdapter;
    private List<ReviewVO> mReviewVOList = new ArrayList<>();


    public static Intent newIntent(){
        Intent intent = new Intent(YUMenuBookApp.getContext(), ReviewActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        ButterKnife.bind(this, this);

        tvReviewTitle.setTypeface(YUMenuBookApp.getTextTypeface());

        assignDummyReview();

        reviewAdapter = new ReviewAdapter(mReviewVOList);
        rvReviewItem.setAdapter(reviewAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(YUMenuBookApp.getContext(), LinearLayoutManager.VERTICAL, false);
        rvReviewItem.setLayoutManager(layoutManager);


    }

    private void assignDummyReview(){
        mReviewVOList.add(new ReviewVO("Nyi", "12/12/2016", "Service is good enough. Go and try some food from this shop.  It is worth tasting", UserModel.objInstance().getPhotoURL()));
        mReviewVOList.add(new ReviewVO("Nyi", "12/12/2016", "Fried rice is really tasty.  Should try", UserModel.objInstance().getPhotoURL()));
        mReviewVOList.add(new ReviewVO("Nyi", "12/12/2016", "First Review 3", UserModel.objInstance().getPhotoURL()));
        mReviewVOList.add(new ReviewVO("Nyi", "12/12/2016", "First Review 4", UserModel.objInstance().getPhotoURL()));
        mReviewVOList.add(new ReviewVO("Nyi", "12/12/2016", "First Review 5", UserModel.objInstance().getPhotoURL()));
    }

    public void backReviewClick(View view){
        this.finish();
    }

    public void writeReviewClick(View view){

    }


}
