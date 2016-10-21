package com.nyi.yumenubook.data.VOs;

/**
 * Created by IN-3442 on 21-Oct-16.
 */

public class ShopVO {

    private String ID;
    private String name;
    private String imageLink;

    public ShopVO() {
    }

    public ShopVO(String ID, String name, String imageLink) {
        this.ID = ID;
        this.name = name;
        this.imageLink = imageLink;
    }

    public ShopVO(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getImageLink() {
        return imageLink;
    }
}
