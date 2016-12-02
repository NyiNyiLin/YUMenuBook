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


import com.nyi.yumenubook.R;
import com.nyi.yumenubook.data.models.UserModel;
import com.nyi.yumenubook.events.DataEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 27-Nov-16.
 */

public class EditDialogFragment extends DialogFragment {

    @BindView(R.id.tv_dialoge_edit_title)
    TextView etDialogeTitle;

    @BindView(R.id.et_dialoge_edit_value)
    EditText etDialogeValue;

    private static final String ARG_title = "title";
    private static final String ARG_value = "value";

    private String title;
    private String value;

    public static EditDialogFragment newInstance(String title, String value){
        EditDialogFragment editDialogFragment = new EditDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_title, title);
        args.putString(ARG_value, value);

        editDialogFragment.setArguments(args);

        return editDialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_title);
            value = getArguments().getString(ARG_value);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.fragment_edit_dialog, null);
        ButterKnife.bind(this, view);

        etDialogeTitle.setText(title);
        etDialogeValue.setText(value);

        builder.setView(view);

        // Add action buttons
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        //User Info is brodcast to Profile Fragment
                        EventBus.getDefault().post(new DataEvent.ChangeUserInfo(etDialogeTitle.getText().toString(), etDialogeValue.getText().toString()));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
