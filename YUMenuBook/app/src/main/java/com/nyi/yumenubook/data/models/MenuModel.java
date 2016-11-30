package com.nyi.yumenubook.data.models;

import com.nyi.yumenubook.data.VOs.MenuItemVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-3442 on 20-Nov-16.
 */

public class MenuModel{

    private List<MenuItemVO> cartMenuItemVOList;
    private MenuItemVO menuItemVO;

    private static MenuModel objInstance;

    private MenuModel(){
        cartMenuItemVOList = new ArrayList<>();
    }

    public static MenuModel getobjInstance(){
        if(objInstance == null) objInstance = new MenuModel();
        return objInstance;
    }

    public void addMenuItemToCartMenuList(MenuItemVO menuItemVO){
        cartMenuItemVOList.add(menuItemVO);
        this.menuItemVO = menuItemVO;
    }

    public List<MenuItemVO> getCartMenuItemVOList() {
        return cartMenuItemVOList;
    }

    public MenuItemVO getMenuItemVO() {
        return menuItemVO;
    }
}
