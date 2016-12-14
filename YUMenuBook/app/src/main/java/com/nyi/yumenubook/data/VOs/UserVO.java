package com.nyi.yumenubook.data.VOs;

import io.realm.RealmObject;

/**
 * Created by IN-3442 on 30-Nov-16.
 */

public class UserVO extends RealmObject{
    private String phone = "";
    private String major;
    private int age;

    public UserVO() {
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


    public String getPhone() {
        return phone;
    }

    public String getMajor() {
        return major;
    }

    public int getAge() {
        return age;
    }

}
