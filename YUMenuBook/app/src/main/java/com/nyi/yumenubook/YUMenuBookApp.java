package com.nyi.yumenubook;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

import com.facebook.FacebookSdk;
import com.nyi.yumenubook.utils.RealmUtil;

import io.realm.Realm;

/**
 * Created by IN-3442 on 21-Oct-16.
 */

public class YUMenuBookApp extends Application{
    private static Context context;
    private static Typeface titleTypeface;
    private static Typeface textTypeface;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        FacebookSdk.sdkInitialize(getApplicationContext());
        RealmUtil.objInstance();

        AssetManager am = context.getApplicationContext().getAssets();

        titleTypeface = Typeface.createFromAsset(getAssets(),  "fonts/CoreSansGRounded-Medium.ttf");
        textTypeface = Typeface.createFromAsset(getAssets(),  "fonts/CoreSansG-Regular.ttf");
        //FirebaseUtil.getObjInstance().uploadTestMenu();
        //FirebaseUtil.getObjInstance().uploadTestShop();
    }

    public static Context getContext() {
        return context;
    }

    public static Typeface getTitleTypeface() {
        return titleTypeface;
    }

    public static Typeface getTextTypeface() {
        return textTypeface;
    }
}
