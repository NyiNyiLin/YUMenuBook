package com.nyi.yumenubook.data.VOs;

/**
 * Created by IN-3442 on 08-Dec-16.
 */

public class OrderItemVO {
    private String ID;
    private String name;
    private int price;
    private int quantity;

    public OrderItemVO(String ID, String name, int quantity, int price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderItemVO(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
