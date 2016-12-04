package com.nyi.yumenubook.data.VOs;

import io.realm.RealmObject;

/**
 * Created by IN-3442 on 04-Dec-16.
 */

public class OrderVO extends RealmObject{
    private String shopName;
    private String orderID;
    private String date;
    private String time;

    private String userName;
    private String userPhone;

    public OrderVO() {
    }

    public OrderVO(String shopName, String orderID, String date, String time) {
        this.shopName = shopName;
        this.orderID = orderID;
        this.date = date;
        this.time = time;
    }

    public String getShopName() {
        return shopName;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhone() {
        return userPhone;
    }
}
