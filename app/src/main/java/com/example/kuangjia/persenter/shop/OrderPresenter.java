package com.example.kuangjia.persenter.shop;

import com.example.kuangjia.base.BasePersenter;
import com.example.kuangjia.common.CommonSubscriber;
import com.example.kuangjia.interfaces.shop.ShoppingConstact;
import com.example.kuangjia.models.HttpManager;
import com.example.kuangjia.models.bean.OrderInfoBean;
import com.example.kuangjia.utils.RxUtils;

public class OrderPresenter extends BasePersenter<ShoppingConstact.OrderView> implements ShoppingConstact.OrderPresenter {
    @Override
    public void getCartIndex() {

    }

    //获取订单信息
    @Override
    public void getOrderList(int addressId, int couponId) {
        addSubscribe(HttpManager.getInstance().getShopApi().getOrderInfo(addressId,couponId)
                .compose(RxUtils.<OrderInfoBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<OrderInfoBean>(mView){

                    @Override
                    public void onNext(OrderInfoBean orderInfoBean) {
                        mView.getOrderListReturn(orderInfoBean);
                    }
                }));
    }
}
