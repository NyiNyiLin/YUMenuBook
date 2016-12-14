package com.nyi.yumenubook.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.adapters.MenuCartItemAdapter;
import com.nyi.yumenubook.data.VOs.OrderItemVO;
import com.nyi.yumenubook.data.VOs.OrderVO;
import com.nyi.yumenubook.data.VOs.ShopVO;
import com.nyi.yumenubook.data.models.MenuModel;
import com.nyi.yumenubook.data.models.ShopModel;
import com.nyi.yumenubook.data.models.UserModel;
import com.nyi.yumenubook.events.DataEvent;
import com.nyi.yumenubook.fragments.EditDialogFragment;
import com.nyi.yumenubook.utils.Constants;
import com.nyi.yumenubook.utils.DateUtil;
import com.nyi.yumenubook.utils.DialogUtil;
import com.nyi.yumenubook.utils.NetworkUtil;
import com.nyi.yumenubook.utils.RealmUtil;
import com.nyi.yumenubook.views.holders.CartMenuItemViewHolder;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class CartActivity extends AppCompatActivity implements CartMenuItemViewHolder.ControllerCartMenuItem, View.OnClickListener, TimePickerDialog.OnTimeSetListener{
    @BindView(R.id.rv_cart_item)
    RecyclerView rvCartMenuItem;

    @BindView(R.id.tv_cart_total_price)
    TextView tvCartTotalPrice;

    @BindView(R.id.tv_shop_cart_title)
    TextView tvShopCartTitle;

    @BindView(R.id.iv_cart_order)
    ImageView ivCartOrder;

    private MenuCartItemAdapter menuItemAdapter;
    private List<OrderItemVO> mMenuItemVOList;
    private int total;
    String time = "12:12";

    private final String PHONE_TEXT = "Require Phone No to order";

    public static Intent newIntent(){
        Intent intent = new Intent(YUMenuBookApp.getContext(), CartActivity.class);
        return  intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this, this);

        ShopVO shopVO = ShopModel.getobjInstance().getShopVO();
        tvShopCartTitle.setText(shopVO.getName());
        tvShopCartTitle.setTypeface(YUMenuBookApp.getTitleTypeface());

        if(mMenuItemVOList == null){
            mMenuItemVOList = new ArrayList<>();
        }

        mMenuItemVOList = MenuModel.getobjInstance().getCartMenuItemVOList();

        Log.d(Constants.TAG, "Cart Item Count " + mMenuItemVOList.size());

        for(OrderItemVO orderItemVO : mMenuItemVOList){
            total = total + orderItemVO.getPrice();
        }
        tvCartTotalPrice.setText(total + " KS");
        menuItemAdapter = new MenuCartItemAdapter(mMenuItemVOList, this);
        rvCartMenuItem.setAdapter(menuItemAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(YUMenuBookApp.getContext(), LinearLayoutManager.VERTICAL, false);
        rvCartMenuItem.setLayoutManager(layoutManager);

        ivCartOrder.setOnClickListener(this);

    }

    public void backCart(View view){
        this.finish();
    }


    @Override
    public void onTapMenuItem(OrderItemVO orderItemVO, int position) {
        Toast.makeText(YUMenuBookApp.getContext(), orderItemVO.getName() + " is removed from your Cart", Toast.LENGTH_SHORT).show();
        //mMenuItemVOList.remove(position);
        total = total - orderItemVO.getPrice();
        tvCartTotalPrice.setText(total + " KS");
        menuItemAdapter.removeMenu(position);
    }

    @Override
    public void onClick(View view) {
        Log.d(Constants.TAG, "CartActivity Cart Order Click");

        if(mMenuItemVOList.size() == 0){
            AlertDialog alertDialog = DialogUtil.createAlertDialoge(this, "There is no item selected.", "", "OK");
            alertDialog.show();
        }
        else if(!UserModel.objInstance().isSignIn()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You need to sign in to order")
                    .setTitle("Warning")
                    .setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).setNegativeButton("Cancel", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else if(! NetworkUtil.checkInternet(getApplicationContext())) {
            AlertDialog alertDialog = DialogUtil.createAlertDialoge(this, "Require Internet Connection to order!!", "No Connection", "OK");
            alertDialog.show();

        }
        else if(UserModel.objInstance().getPhone().compareTo("")==0){
            DialogFragment dialog = EditDialogFragment.newInstance(PHONE_TEXT, "");
            dialog.show(getSupportFragmentManager(), "Update User Phone");
        }
        else {
            showTimePicker();
            //showLastWarning();
        }
    }

    private void showLastWarning(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure to order these items?  Order can't be cancelled. Delivery Time " + time)
                .setTitle("Comfirm")
                .setPositiveButton("Order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        order();
                    }
                }).setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void order(){
        final ProgressDialog progressDialog = DialogUtil.createProgressDialoge(this, "Ordering...");
        progressDialog.show();

        ShopVO shopVO = ShopModel.getobjInstance().getShopVO();

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Constants.DETAIL).child(shopVO.getShopID()).child(Constants.ORDER);

        final String id = databaseReference.push().getKey();
        Log.d(Constants.TAG, "CartActivity Cart Order Click push new order id " + id);
        OrderVO orderVO = new OrderVO(UserModel.objInstance().getName(), UserModel.objInstance().getPhone(), id, ShopModel.getobjInstance().getShopVO().getShopID(), DateUtil.getCurrentDate(), time, total);

        //save in realm database
        final Realm realm = RealmUtil.objInstance().getRealm();
        realm.beginTransaction();
        realm.copyToRealm(orderVO);
        realm.commitTransaction();

        databaseReference.child(id).setValue(orderVO).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                for(OrderItemVO orderItemVO : mMenuItemVOList){
                    orderItemVO.setID(id);
                    databaseReference.child(id).child(Constants.ORDER_ITEM).push().setValue(orderItemVO);

                    //save in realm database
                    realm.beginTransaction();
                    realm.copyToRealm(orderItemVO);
                    realm.commitTransaction();
                }

                tvCartTotalPrice.setText("");
                menuItemAdapter.deleteAllMenu();
                progressDialog.dismiss();

            }
        });

    }

    // This method will be called when you update a phone no in editdialogFragment
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DataEvent.ChangeUserInfo event) {
        if(event.getTitle().equals(PHONE_TEXT)){
            UserModel.objInstance().setUserPhone(event.getValue());
            //showLastWarning();
            showTimePicker();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private void showTimePicker(){
        TimePickerDialog thirdPartyTimePicker = TimePickerDialog.newInstance(this, 12, 12, 12, false);

        thirdPartyTimePicker.setTitle("Delivery Time");
        thirdPartyTimePicker.show(this.getFragmentManager(), "ThirdPartyDatePicker");
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        Log.d(Constants.TAG, "Cart Activity hourOfDay " + hourOfDay + " Minute " + minute + " second " + second);
        time = hourOfDay + ":" + minute;
        showLastWarning();
    }
}
