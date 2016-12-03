package com.nyi.yumenubook.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.data.models.UserModel;
import com.nyi.yumenubook.events.DataEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by IN-3442 on 27-Nov-16.
 */

public class ReviewPostDialogFragment extends DialogFragment {

    @BindView(R.id.tv_fragment_post_review_name)
    TextView tvPostReviewName;

    @BindView(R.id.tv_fragment_post_review_date)
    TextView tvPostReviewDate;

    @BindView(R.id.et_fragment_post_review)
    EditText etPostReview;

    @BindView(R.id.iv_fragment_post_review_image)
    CircleImageView ivPostReviewImage;


    public static ReviewPostDialogFragment newInstance(){
        ReviewPostDialogFragment editDialogFragment = new ReviewPostDialogFragment();

        return editDialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.fragment_post_review, null);
        ButterKnife.bind(this, view);

        tvPostReviewName.setText(UserModel.objInstance().getName());
        tvPostReviewDate.setText("12/12/2016");

        Glide.with(YUMenuBookApp.getContext())
                .load(UserModel.objInstance().getPhotoURL())
                .asBitmap().centerCrop()
                .placeholder(R.drawable.ic_camera_black_24dp)
                .error(R.drawable.ic_camera_black_24dp)
                .into(ivPostReviewImage);


        builder.setView(view);

        // Add action buttons
        builder.setPositiveButton("POST", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        //User Info is brodcast to Profile Fragment

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ReviewPostDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
