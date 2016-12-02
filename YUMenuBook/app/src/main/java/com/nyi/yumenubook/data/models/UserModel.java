package com.nyi.yumenubook.data.models;

import com.nyi.yumenubook.data.VOs.UserVO;

/**
 * Created by IN-3442 on 30-Nov-16.
 */

public class UserModel {
    private String name;
    private String email;
    private String photoURL;
    private String phone;
    private String major;
    private int age;

    private boolean isSignIn;
    private String userID;

    private static UserModel objInstance;

    private UserModel(){
        name = "";
        email = "";
        photoURL = "";
        phone = "";
        major = "";
        age = 0;
        isSignIn = false;
        userID = "";
    }

    public static UserModel objInstance(){
        if(objInstance == null) objInstance = new UserModel();
        return objInstance;
    }

    public void setFirebaseUser(String name, String email, String photoURL, boolean isSignIn) {
        this.name = name;
        this.email = email;
        this.photoURL = photoURL;
        this.isSignIn = isSignIn;
    }

    public void setFirebaseUser(boolean isSignIn){
        if(isSignIn == false){
            this.isSignIn = false;

            name = "";
            email = "";
            photoURL = "";
        }
    }

    public void setUserPhone(String phone){
        this.phone = phone;
    }


    public void setUserAge(int age){
       this.age =age;
    }


    public void setUserMajor(String major){
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public String getPhone() {
        return phone;
    }

    public String getMajor() {
        return major;
    }

    public int getAge() {
        return age;
    }

    public boolean isSignIn() {
        return isSignIn;
    }

    public String getUserID() {
        return userID;
    }
}
