package com.nyi.yumenubook.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.data.models.UserModel;
import com.nyi.yumenubook.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 12-Dec-16.
 */

public class InfoFragment extends Fragment {

    @BindView(R.id.btn_info_open_source)
    Button btnOpenSource;

    @BindView(R.id.btn_info_feedback)
    Button btnFeedback;

    public InfoFragment(){

    }

    public static InfoFragment newInstance(){
        InfoFragment infoFragment = new InfoFragment();
        return infoFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, view);

        btnOpenSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialog = OpenSourceDialogFragment.newInstance();
                dialog.show(getChildFragmentManager(), "Open Open Source Dialoge Fragment");
            }
        });

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{  "nyinyilin.dev@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Yu Menu Book");
                //i.putExtra(Intent.EXTRA_TEXT   , "Testing");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

}
