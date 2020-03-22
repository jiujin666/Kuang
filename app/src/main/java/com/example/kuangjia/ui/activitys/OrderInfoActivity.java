package com.example.kuangjia.ui.activitys;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kuangjia.R;
import com.example.kuangjia.adapter.ShoppingAdapter;
import com.example.kuangjia.base.BaseActivity;
import com.example.kuangjia.interfaces.shop.ShoppingConstact;
import com.example.kuangjia.models.bean.CartBean;
import com.example.kuangjia.models.bean.OrderInfoBean;
import com.example.kuangjia.persenter.shop.OrderPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderInfoActivity  extends BaseActivity<ShoppingConstact.OrderPresenter> implements ShoppingConstact.OrderView  {
    int addressId, couponId = 0; //地址ID+优惠券ID
    @BindView(R.id.txt_noaddress_tips)
    TextView txtNoaddressTips;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_phone)
    TextView txtPhone;
    @BindView(R.id.txt_default)
    TextView txtDefault;
    @BindView(R.id.txt_address)
    TextView txtAddress;
    @BindView(R.id.layout_address)
    ConstraintLayout layoutAddress;
    @BindView(R.id.layout_addressInfo)
    ConstraintLayout layoutAddressInfo;
    @BindView(R.id.txt_couponNums)
    TextView txtCouponNums;
    @BindView(R.id.layout_coupon)
    ConstraintLayout layoutCoupon;
    @BindView(R.id.txt_totalPrice)
    TextView txtTotalPrice;
    @BindView(R.id.txt_freight)
    TextView txtFreight;
    @BindView(R.id.txt_couponPrice)
    TextView txtCouponPrice;
    @BindView(R.id.layout_goods_info)
    ConstraintLayout layoutGoodsInfo;
    @BindView(R.id.recy_goodList)
    RecyclerView recyGoodList;
    @BindView(R.id.layout_goodList)
    ConstraintLayout layoutGoodList;
    @BindView(R.id.txt_pay)
    TextView txtPay;
    @BindView(R.id.layout_pay)
    ConstraintLayout layoutPay;
    @BindView(R.id.order_recy)
    RecyclerView order_recy;
    ShoppingAdapter shoppingAdapter;
    List<CartBean.DataBean.CartListBean> list;

    @Override
    protected int getLayout() {
        return R.layout.activity_orderinfo;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        shoppingAdapter = new ShoppingAdapter(list,OrderInfoActivity.this);
        order_recy.setLayoutManager(new LinearLayoutManager(this));
        order_recy.setAdapter(shoppingAdapter);

    }

    @Override
    protected void initData() {
        persenter.getCartIndex();
        persenter.getOrderList(addressId, couponId);
    }

    @Override
    protected ShoppingConstact.OrderPresenter createPersenter() {
        return new OrderPresenter();
    }

    @Override
    public void getCartIndexReturn(CartBean result) {
        shoppingAdapter.updata(result.getData().getCartList());
    }

    //获取订单商品信息返回
    @Override
    public void getOrderListReturn(OrderInfoBean result) {

        //刷新地址
        updateAddress(result.getData().getCheckedAddress());

        //刷新优惠券
        updateCoupon(result.getData().getCouponList());

        updateOrderInfo(result.getData());

        updateGoodList(result.getData().getCheckedGoodsList());

    }

    @OnClick({R.id.txt_noaddress_tips, R.id.layout_address, R.id.layout_coupon, R.id.txt_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_noaddress_tips:
                Intent intent = new Intent(this,AdressEditorActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_address:
                Intent intent1 = new Intent(this,AdressListActivity.class);
                startActivity(intent1);
                break;
            case R.id.layout_coupon:
                break;
            case R.id.txt_pay:
                break;
        }
    }

    //刷新地址
    private void updateAddress(OrderInfoBean.DataBean.CheckedAddressBean checkedAddress){

    }

    //刷新优惠券
    private void updateCoupon(List<OrderInfoBean.DataBean.CouponListBean> couponList){

    }

    //刷新商品信息
    private void updateOrderInfo(OrderInfoBean.DataBean data){

    }

    private void updateGoodList(List<OrderInfoBean.DataBean.CheckedGoodsListBean> checkedGoodsList){

    }
}

