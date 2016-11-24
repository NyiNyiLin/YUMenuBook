package com.nyi.yumenubook.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.adapters.MenuCartItemAdapter;
import com.nyi.yumenubook.data.VOs.MenuItem;
import com.nyi.yumenubook.data.models.MenuModel;
import com.nyi.yumenubook.utils.Constants;
import com.nyi.yumenubook.views.holders.CartMenuItemViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity implements CartMenuItemViewHolder.ControllerCartMenuItem{
    @BindView(R.id.rv_cart_item)
    RecyclerView rvCartMenuItem;

    @BindView(R.id.tv_cart_total_price)
    TextView tvCartTotalPrice;

    private MenuCartItemAdapter menuItemAdapter;
    private List<MenuItem> mMenuItemList;
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

        if(mMenuItemList == null){
            mMenuItemList = new ArrayList<>();
        }

        mMenuItemList = MenuModel.getobjInstance().getCartMenuItemList();

        Log.d(Constants.TAG, "Cart Item Count " + mMenuItemList.size());

        for(MenuItem menuItem: mMenuItemList){
            total = total + menuItem.getPrice();
        }
        tvCartTotalPrice.setText(total + " KS");
        menuItemAdapter = new MenuCartItemAdapter(mMenuItemList, this);
        rvCartMenuItem.setAdapter(menuItemAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(YUMenuBookApp.getContext(), LinearLayoutManager.VERTICAL, false);
        rvCartMenuItem.setLayoutManager(layoutManager);

    }

    public void backCart(View view){
        this.finish();
    }

    @Override
    public void onTapMenuItem(MenuItem menuItem, int position) {
        Toast.makeText(YUMenuBookApp.getContext(), menuItem.getName() + " is removed from your Cart", Toast.LENGTH_SHORT).show();
        //mMenuItemList.remove(position);
        total = total - menuItem.getPrice();
        tvCartTotalPrice.setText(total + " KS");
        menuItemAdapter.removeMenu(position);
    }
}
