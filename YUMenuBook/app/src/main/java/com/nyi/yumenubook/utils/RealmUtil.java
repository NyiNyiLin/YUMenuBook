package com.nyi.yumenubook.utils;

import com.nyi.yumenubook.YUMenuBookApp;

import io.realm.Realm;

/**
 * Created by IN-3442 on 04-Dec-16.
 */

public class RealmUtil {
    private static RealmUtil objInstance;
    private Realm realm;

    private RealmUtil(){
        Realm.init(YUMenuBookApp.getContext());
        realm = Realm.getDefaultInstance();
    }

    public static RealmUtil objInstance(){
        if(objInstance == null) objInstance = new RealmUtil();
        return objInstance;
    }

    public Realm getRealm() {
        return realm;
    }
}
