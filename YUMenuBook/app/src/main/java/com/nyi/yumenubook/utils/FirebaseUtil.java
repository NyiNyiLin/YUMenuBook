package com.nyi.yumenubook.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.connection.idl.ConnectionConfig;
import com.nyi.yumenubook.data.VOs.MenuItem;
import com.nyi.yumenubook.data.VOs.ShopVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-3442 on 20-Nov-16.
 */

public class FirebaseUtil {
    private static FirebaseUtil objInstance;
    private DatabaseReference mDatabase;

    private FirebaseUtil(){

    }

    public static FirebaseUtil getObjInstance(){
        if(objInstance == null) objInstance = new FirebaseUtil();
        return objInstance;
    }

    public void uploadTestShop(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        List<String> type = new ArrayList<>();
        type.add("Breakfast");
        type.add("Lunch");
        type.add("Drink");
        ShopVO shopVO1 = new ShopVO("1", "Shan Ma Lay", Constants.DUMMY_LINK, Constants.TAUNGNOO_CANTEEN, type);
        ShopVO shopVO2 = new ShopVO("2", "Shwe Pha Lar", Constants.DUMMY_LINK, Constants.TAUNGNOO_CANTEEN, type);
        ShopVO shopVO3 = new ShopVO("3", "Inn Wa", Constants.DUMMY_LINK, Constants.SCIENCE_CANTEEN, type);

        mDatabase.child(Constants.SHOP).push().setValue(shopVO1);
        mDatabase.child(Constants.SHOP).push().setValue(shopVO2);
        mDatabase.child(Constants.SHOP).push().setValue(shopVO3);
    }

    public void uploadTestMenu(){
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child(Constants.DETAIL).child("1").child(Constants.MENUITEM).child("Breakfast").push().setValue(new MenuItem("Fried Rice 1", 1300, 1));
        mDatabase.child(Constants.DETAIL).child("1").child(Constants.MENUITEM).child("Breakfast").push().setValue(new MenuItem("Mone Hin Khar 1", 300, 1));
        mDatabase.child(Constants.DETAIL).child("1").child(Constants.MENUITEM).child("Breakfast").push().setValue(new MenuItem("Pa Lar Tar 1", 100, 1));

        mDatabase.child(Constants.DETAIL).child("1").child(Constants.MENUITEM).child("Lunch").push().setValue(new MenuItem("Soe Myint Kyaw 1", 1500, 1));
        mDatabase.child(Constants.DETAIL).child("1").child(Constants.MENUITEM).child("Lunch").push().setValue(new MenuItem("Chinese Fried Rice 1", 1500, 1));
        mDatabase.child(Constants.DETAIL).child("1").child(Constants.MENUITEM).child("Lunch").push().setValue(new MenuItem("Thai Fried Rice 1", 1500, 1));

        mDatabase.child(Constants.DETAIL).child("1").child(Constants.MENUITEM).child("Drink").push().setValue(new MenuItem("Cola 1", 300, 1));
        mDatabase.child(Constants.DETAIL).child("1").child(Constants.MENUITEM).child("Drink").push().setValue(new MenuItem("Pa Pa Ya 1", 500, 1));
        mDatabase.child(Constants.DETAIL).child("1").child(Constants.MENUITEM).child("Drink").push().setValue(new MenuItem("Juice 1", 500, 1));

        mDatabase.child(Constants.DETAIL).child("2").child(Constants.MENUITEM).child("Breakfast").push().setValue(new MenuItem("Fried Rice 2", 1300, 1));
        mDatabase.child(Constants.DETAIL).child("2").child(Constants.MENUITEM).child("Breakfast").push().setValue(new MenuItem("Mone Hin Khar 2", 300, 1));
        mDatabase.child(Constants.DETAIL).child("2").child(Constants.MENUITEM).child("Breakfast").push().setValue(new MenuItem("Pa Lar Tar 2", 100, 1));

        mDatabase.child(Constants.DETAIL).child("2").child(Constants.MENUITEM).child("Lunch").push().setValue(new MenuItem("Soe Myint Kyaw 2", 1500, 1));
        mDatabase.child(Constants.DETAIL).child("2").child(Constants.MENUITEM).child("Lunch").push().setValue(new MenuItem("Chinese Fried Rice 2", 1500, 1));
        mDatabase.child(Constants.DETAIL).child("1").child(Constants.MENUITEM).child("Lunch").push().setValue(new MenuItem("Thai Fried Rice 2", 1500, 1));

        mDatabase.child(Constants.DETAIL).child("2").child(Constants.MENUITEM).child("Drink").push().setValue(new MenuItem("Cola 2", 300, 1));
        mDatabase.child(Constants.DETAIL).child("2").child(Constants.MENUITEM).child("Drink").push().setValue(new MenuItem("Pa Pa Ya 2", 500, 1));
        mDatabase.child(Constants.DETAIL).child("2").child(Constants.MENUITEM).child("Drink").push().setValue(new MenuItem("Juice 2", 500, 1));

    }

}
