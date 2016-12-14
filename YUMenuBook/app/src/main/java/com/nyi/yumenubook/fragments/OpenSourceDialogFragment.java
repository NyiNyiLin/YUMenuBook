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
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.events.DataEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 27-Nov-16.
 */

public class OpenSourceDialogFragment extends DialogFragment {

    @BindView(R.id.wv_open_source)
    WebView wvOpenSourcel;

    public static OpenSourceDialogFragment newInstance(){
        OpenSourceDialogFragment editDialogFragment = new OpenSourceDialogFragment();

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
        View view = inflater.inflate(R.layout.fragment_open_source_dialoge, null);
        ButterKnife.bind(this, view);

        wvOpenSourcel.loadUrl("file:///android_asset/open_source_licenses.html");
        builder.setView(view);
        // Add action buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        }
                });

        return builder.create();
    }
}
