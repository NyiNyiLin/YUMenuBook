package com.nyi.yumenubook.data.models;

import com.nyi.yumenubook.data.VOs.MenuItem;
import com.nyi.yumenubook.data.VOs.ShopVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-3442 on 20-Nov-16.
 */

public class ShopModel {
    private ShopVO shopVO;

    private static ShopModel objInstance;

    private ShopModel(){

    }

    public static ShopModel getobjInstance(){
        if(objInstance == null) objInstance = new ShopModel();
        return objInstance;
    }

    public void addUserSelectedShop(ShopVO shopVO){
        this.shopVO = shopVO;
    }

    public ShopVO getShopVO() {
        return shopVO;
    }
}
