package com.nyi.yumenubook.data.VOs;

/**
 * Created by IN-3442 on 19-Nov-16.
 */

public class MenuItem {
    private String name;
    private int price;

    public MenuItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
