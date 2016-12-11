package com.nyi.yumenubook.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by IN-3442 on 11-Dec-16.
 */

public class ProgressDialogUtil {

    public static ProgressDialog createProgressDialoge(Context context, String progressMsg){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(progressMsg);
        progressDialog.setTitle("");

        return progressDialog;
    }

    private void test(){
        new Thread(new Runnable() {
            @Override
            public void run() {


            }
        }).start();
    }
}
