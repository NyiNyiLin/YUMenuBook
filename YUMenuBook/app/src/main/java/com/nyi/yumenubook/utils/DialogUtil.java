package com.nyi.yumenubook.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by IN-3442 on 11-Dec-16.
 */

public class DialogUtil {

    public static ProgressDialog createProgressDialoge(Context context, String progressMsg){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(progressMsg);
        progressDialog.setTitle("");

        return progressDialog;
    }

    public static AlertDialog createAlertDialoge(Context context, String message, String title, String btnPositiveText){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setTitle(title)
                .setPositiveButton(btnPositiveText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog dialog = builder.create();

        return dialog;
    }

    private void test(){
        new Thread(new Runnable() {
            @Override
            public void run() {


            }
        }).start();
    }
}
