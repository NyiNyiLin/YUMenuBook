package com.nyi.yumenubook.data.VOs;

/**
 * Created by IN-3442 on 19-Nov-16.
 */

public class MenuItemVO {
    private String menuItemID;
    private String name;
    private int price;
    private int available;

    public MenuItemVO() {
    }

    public MenuItemVO(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public MenuItemVO(String name, int price, int available) {
        this.name = name;
        this.price = price;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getAvailable() {
        return available;
    }

    public String getMenuItemID() {
        return menuItemID;
    }

    public void setMenuItemID(String menuItemID) {
        this.menuItemID = menuItemID;
    }
}
