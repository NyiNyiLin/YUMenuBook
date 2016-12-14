package com.nyi.yumenubook.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.adapters.ReviewAdapter;
import com.nyi.yumenubook.adapters.ShopAdapter;
import com.nyi.yumenubook.data.VOs.ReviewVO;
import com.nyi.yumenubook.data.VOs.ShopVO;
import com.nyi.yumenubook.data.models.ShopModel;
import com.nyi.yumenubook.data.models.UserModel;
import com.nyi.yumenubook.fragments.EditDialogFragment;
import com.nyi.yumenubook.fragments.ReviewPostDialogFragment;
import com.nyi.yumenubook.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewActivity extends AppCompatActivity {

    @BindView(R.id.tv_review_title)
    TextView tvReviewTitle;

    @BindView(R.id.rv_review_item)
    RecyclerView rvReviewItem;

    @BindView(R.id.tv_review_rate_count)
    TextView tvReviewReviewCount;

    private ReviewAdapter reviewAdapter;
    private List<ReviewVO> mReviewVOList = new ArrayList<>();
    private int reviewCount = 0;


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

        reviewAdapter = new ReviewAdapter(mReviewVOList);
        rvReviewItem.setAdapter(reviewAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(YUMenuBookApp.getContext(), LinearLayoutManager.VERTICAL, false);
        rvReviewItem.setLayoutManager(layoutManager);

        getReviewFromFirebase();

    }

    private void getReviewFromFirebase(){
        ShopVO shopVO = ShopModel.getobjInstance().getShopVO();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Constants.DETAIL).child(shopVO.getShopID()).child(Constants.REVIEW);

        ChildEventListener listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(Constants.TAG, "Review Activity onChildAdded:" + dataSnapshot.getKey());

                ReviewVO reviewVO = dataSnapshot.getValue(ReviewVO.class);

                reviewAdapter.addNewReview(reviewVO);

                reviewCount++;
                tvReviewReviewCount.setText(reviewCount + "");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addChildEventListener(listener);


    }

    private void assignDummyReview(){
        mReviewVOList.add(new ReviewVO("Nyi", "12/12/2016", "", "Service is good enough. Go and try some food from this shop.  It is worth tasting", UserModel.objInstance().getPhotoURL()));
        mReviewVOList.add(new ReviewVO("Nyi", "12/12/2016", "", "Fried rice is really tasty.  Should try", UserModel.objInstance().getPhotoURL()));
        mReviewVOList.add(new ReviewVO("Nyi", "12/12/2016", "", "First Review 3", UserModel.objInstance().getPhotoURL()));
        mReviewVOList.add(new ReviewVO("Nyi", "12/12/2016", "", "First Review 4", UserModel.objInstance().getPhotoURL()));
        mReviewVOList.add(new ReviewVO("Nyi", "12/12/2016", "", "First Review 5", UserModel.objInstance().getPhotoURL()));
    }

    public void backReviewClick(View view){
        this.finish();
    }

    public void writeReviewClick(View view){
        if(!UserModel.objInstance().isSignIn()){
            Intent intent = LogInActivity.newIntent();
            startActivity(intent);
        }else {
            ReviewPostDialogFragment dialog = new ReviewPostDialogFragment();
            dialog.show(getSupportFragmentManager(), "Post");
        }
    }

}
