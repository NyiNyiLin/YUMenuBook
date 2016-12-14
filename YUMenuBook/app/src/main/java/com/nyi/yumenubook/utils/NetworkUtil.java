package com.nyi.yumenubook.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by IN-3442 on 13-Dec-16.
 */

public class NetworkUtil {
    public static boolean checkInternet(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        {
            NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

            if (netInfo == null) {
                return false;
            } else {
                return true;
            }
        }
    }
}
