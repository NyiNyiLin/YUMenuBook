package com.nyi.yumenubook.views.holders;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nyi.yumenubook.R;
import com.nyi.yumenubook.YUMenuBookApp;
import com.nyi.yumenubook.adapters.OrderItemAdapter;
import com.nyi.yumenubook.data.VOs.OrderItemVO;
import com.nyi.yumenubook.data.VOs.OrderVO;
import com.nyi.yumenubook.utils.RealmUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by IN-3442 on 04-Dec-16.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tv_item_order_shop_name)
    TextView tvShopName;

    @BindView(R.id.tv_item_order_date)
    TextView tvDate;

    @BindView(R.id.tv_item_order_id)
    TextView tvOrderId;

    @BindView(R.id.tv_item_order_time)
    TextView tvOrderTime;

    @BindView(R.id.btn_item_order)
    Button btnOrder;

    @BindView(R.id.rv_item_order_item)
    RecyclerView rvOrderItem;

    private List<OrderItemVO> mOrderItemVOList = new ArrayList<>();
    private OrderItemAdapter mOrderItemAdapter;

    public OrderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        tvShopName.setTypeface(YUMenuBookApp.getTextTypeface());
        tvOrderId.setTypeface(YUMenuBookApp.getTextTypeface());
        tvDate.setTypeface(YUMenuBookApp.getTextTypeface());
        tvOrderTime.setTypeface(YUMenuBookApp.getTextTypeface());

        mOrderItemAdapter = new OrderItemAdapter(mOrderItemVOList);
        rvOrderItem.setAdapter(mOrderItemAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(YUMenuBookApp.getContext(), LinearLayoutManager.VERTICAL, false);
        rvOrderItem.setLayoutManager(layoutManager);
    }

    public void bindData(OrderVO orderVO){
        tvShopName.setText(orderVO.getShopName());
        //tvOrderId.setText(orderVO.getOrderID());
        tvDate.setText(orderVO.getDate());
        tvOrderTime.setText(orderVO.getTime());
        btnOrder.setText(orderVO.getTotal() + " KS");

        //dummyDat();
        getOrderMenuItemFromRealm(orderVO.getOrderID());
        mOrderItemAdapter.setOrderItemVOList(mOrderItemVOList);
    }

    private void getOrderMenuItemFromRealm(String id){
        Realm realm = RealmUtil.objInstance().getRealm();
        RealmQuery<OrderItemVO> realmQuery = realm.where(OrderItemVO.class);

        RealmResults<OrderItemVO> realmResults = realmQuery.equalTo(OrderItemVO.PARAM_ID, id).findAll();

        for(OrderItemVO orderItemVO : realmResults){
            mOrderItemVOList.add(orderItemVO);
        }
    }

    private void dummyDat(){
        mOrderItemVOList.clear();
        mOrderItemVOList.add(new OrderItemVO("Fried Rice 1 ", 2, 200));
        mOrderItemVOList.add(new OrderItemVO("Fried Rice 2 ", 2, 200));
        mOrderItemVOList.add(new OrderItemVO("Fried Rice 3 ", 2, 200));
        mOrderItemVOList.add(new OrderItemVO("Fried Rice 4 ", 2, 200));
        mOrderItemVOList.add(new OrderItemVO("Fried Rice 5 ", 2, 200));
    }
}
