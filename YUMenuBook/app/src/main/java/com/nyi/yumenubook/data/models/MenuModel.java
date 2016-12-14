package com.nyi.yumenubook.data.models;

import android.util.Log;

import com.nyi.yumenubook.data.VOs.MenuItemVO;
import com.nyi.yumenubook.data.VOs.OrderItemVO;
import com.nyi.yumenubook.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-3442 on 20-Nov-16.
 */

public class MenuModel{


    private List<OrderItemVO> mOrderItemVIList;

    private static MenuModel objInstance;

    private MenuModel(){
        mOrderItemVIList = new ArrayList<>();
    }

    public static MenuModel getobjInstance(){
        if(objInstance == null) objInstance = new MenuModel();
        return objInstance;
    }

    public void addMenuItemToCartMenuList(MenuItemVO menuItemVO){

        boolean isNew = true;
        int size = mOrderItemVIList.size();
        Log.d(Constants.TAG, "MenuModel Order Item Size " + size);
        for(int a=0; a< size; a++){
            OrderItemVO orderItemVO = mOrderItemVIList.get(a);

            if(orderItemVO.getID().equals(menuItemVO.getMenuItemID())){
                int quatity = orderItemVO.getQuantity();
                int price = orderItemVO.getPrice();
                OrderItemVO orderItemVO1 = new OrderItemVO(menuItemVO.getMenuItemID(), menuItemVO.getName(), quatity+1, price+menuItemVO.getPrice());
                mOrderItemVIList.remove(a);
                mOrderItemVIList.add(a, orderItemVO1);
                isNew = false;
                Log.d(Constants.TAG, "MenuModel existing Menu Item " + menuItemVO.getName());
                break;
            }else{
                Log.d(Constants.TAG, "MenuModel existing Menu Item not equal" + menuItemVO.getName());
            }
        }
        if(isNew){
            Log.d(Constants.TAG, "MenuModel new Menu Item " + menuItemVO.getName());
            OrderItemVO orderItemVO = new OrderItemVO(menuItemVO.getMenuItemID(), menuItemVO.getName(), 1, menuItemVO.getPrice());
            mOrderItemVIList.add(orderItemVO);
        }
    }

    public List<OrderItemVO> getCartMenuItemVOList() {
        return mOrderItemVIList;
    }

    public void clearCartMenuItemList(){
        mOrderItemVIList.clear();
    }

}
