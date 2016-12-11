package com.nyi.yumenubook.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.nyi.yumenubook.data.VOs.MenuItemVO;
import com.nyi.yumenubook.data.VOs.OrderItemVO;
import com.nyi.yumenubook.data.VOs.OrderVO;
import com.nyi.yumenubook.data.VOs.ShopVO;
import com.nyi.yumenubook.data.models.MenuModel;
import com.nyi.yumenubook.data.models.ShopModel;
import com.nyi.yumenubook.data.models.UserModel;
import com.nyi.yumenubook.utils.Constants;
import com.nyi.yumenubook.utils.DateUtil;
import com.nyi.yumenubook.utils.FirebaseUtil;
import com.nyi.yumenubook.utils.ProgressDialogUtil;
import com.nyi.yumenubook.views.holders.CartMenuItemViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity implements CartMenuItemViewHolder.ControllerCartMenuItem, View.OnClickListener{
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

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure to order these items?  Order can't be cancelled")
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
        final ProgressDialog progressDialog = ProgressDialogUtil.createProgressDialoge(this, "Ordering...");
        progressDialog.show();

        ShopVO shopVO = ShopModel.getobjInstance().getShopVO();

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Constants.DETAIL).child(shopVO.getShopID()).child(Constants.ORDER);

        final String id = databaseReference.push().getKey();
        Log.d(Constants.TAG, "CartActivity Cart Order Click push new order id " + id);
        OrderVO orderVO = new OrderVO(UserModel.objInstance().getName(), UserModel.objInstance().getPhone(), id, ShopModel.getobjInstance().getShopVO().getShopID(), DateUtil.getCurrentDate(), "12:12");

        databaseReference.child(id).setValue(orderVO).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                for(OrderItemVO orderItemVO : mMenuItemVOList){
                    orderItemVO.setID(id);
                    databaseReference.child(id).child(Constants.ORDER_ITEM).push().setValue(orderItemVO);
                }
                progressDialog.dismiss();
            }
        });


    }
}
