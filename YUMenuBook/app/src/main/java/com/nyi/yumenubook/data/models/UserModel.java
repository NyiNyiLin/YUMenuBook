package com.nyi.yumenubook.data.models;

import com.google.firebase.auth.FirebaseUser;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.data.VOs.UserVO;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by IN-3442 on 30-Nov-16.
 */

public class UserModel {

    private static UserModel objInstance;
    private Realm realm;
    private UserVO userVO;
    private FirebaseUser firebaseUser;

    private boolean isSignIn;

    private UserModel(){
        realm = Realm.getDefaultInstance();
        userVO = new UserVO();
        getUserFromRealmDatabase();
    }

    public static UserModel objInstance(){
        if(objInstance == null) objInstance = new UserModel();
        return objInstance;
    }

    public void saveFirebaseUser(FirebaseUser firebaseUser) {
        this.firebaseUser = firebaseUser;

        if(firebaseUser != null) isSignIn = true;
        else isSignIn = false;
    }

    private UserVO saveToRealm(){
        UserVO userVO = new UserVO();

        realm.beginTransaction();
        UserVO realmUser = realm.copyToRealm(userVO);
        realm.commitTransaction();

        return realmUser;
    }

    private void deleteAllUserFromRealm(){
        final RealmResults<UserVO> results = realm.where(UserVO.class).findAll();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.deleteAllFromRealm();
            }
        });
    }

    public UserVO getUserFromRealmDatabase(){
        // Build the query looking at all users:
        RealmQuery<UserVO> query = realm.where(UserVO.class);

        // Execute the query:
        RealmResults<UserVO> result1 = query.findAll();
        if(result1.size() > 0) {
            userVO = result1.get(0);
            return userVO;
        }
        return saveToRealm();
    }

    public void setUserPhone(final String phone){
        final RealmResults<UserVO> results = realm.where(UserVO.class).findAll();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.get(0).setPhone(phone);
            }
        });

    }


    public void setUserAge(final int age){
        final RealmResults<UserVO> results = realm.where(UserVO.class).findAll();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.get(0).setAge(age);
            }
        });
    }


    public void setUserMajor(final String major){
        final RealmResults<UserVO> results = realm.where(UserVO.class).findAll();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.get(0).setMajor(major);
            }
        });
    }

    public String getName() {
        return firebaseUser.getDisplayName();
    }

    public String getEmail() {
        return firebaseUser.getEmail();
    }

    public String getPhotoURL() {
        return firebaseUser.getPhotoUrl().toString();
    }

    public String getPhone() {
        return userVO.getPhone();
    }

    public String getMajor() {
        return userVO.getMajor();
    }

    public int getAge() {
        return userVO.getAge();
    }

    public boolean isSignIn() {
        return isSignIn;
    }
}
