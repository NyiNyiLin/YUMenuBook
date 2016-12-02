package com.nyi.yumenubook.data.VOs;

/**
 * Created by IN-3442 on 30-Nov-16.
 */

public class UserVO {
    private String name;
    private String email;
    private String photoURL;
    private String phone;
    private String major;
    private int age;

    private boolean isSignIn;
    private String userID;

    public UserVO() {
    }

    public UserVO(String name, String email, String photoURL) {
        this.name = name;
        this.email = email;
        this.photoURL = photoURL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSignIn(boolean signIn) {
        isSignIn = signIn;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
