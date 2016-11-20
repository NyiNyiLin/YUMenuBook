package com.nyi.yumenubook.data.models;

import com.nyi.yumenubook.data.VOs.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-3442 on 20-Nov-16.
 */

public class MenuModel{

    private List<MenuItem> cartMenuItemList;
    private MenuItem menuItem;

    private static MenuModel objInstance;

    private MenuModel(){
        cartMenuItemList = new ArrayList<>();
    }

    public static MenuModel getobjInstance(){
        if(objInstance == null) objInstance = new MenuModel();
        return objInstance;
    }

    public void addMenuItemToCartMenuList(MenuItem menuItem){
        cartMenuItemList.add(menuItem);
        this.menuItem = menuItem;
    }

    public List<MenuItem> getCartMenuItemList() {
        return cartMenuItemList;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }
}
