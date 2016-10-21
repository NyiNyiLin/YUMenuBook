package com.nyi.yumenubook;

import android.app.Application;
import android.content.Context;

/**
 * Created by IN-3442 on 21-Oct-16.
 */

public class YUMenuBookApp extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
