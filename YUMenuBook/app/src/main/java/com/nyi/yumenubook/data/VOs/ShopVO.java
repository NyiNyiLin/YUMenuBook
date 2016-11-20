package com.nyi.yumenubook.data.VOs;

import java.util.List;

/**
 * Created by IN-3442 on 21-Oct-16.
 */

public class ShopVO {

    private String ID;
    private String name;
    private String imageLink;
    private String place;
    private List<String> type;

    public ShopVO() {
    }

    public ShopVO(String ID, String name, String imageLink) {
        this.ID = ID;
        this.name = name;
        this.imageLink = imageLink;
    }

    public ShopVO(String ID, String name, String imageLink, String place, List<String> type) {
        this.ID = ID;
        this.name = name;
        this.imageLink = imageLink;
        this.place = place;
        this.type = type;
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

    public String getPlace() {
        return place;
    }

    public List<String> getType() {
        return type;
    }
}
