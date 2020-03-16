package com.example.kuangjia.persenter.shop;


import com.example.kuangjia.base.BasePersenter;
import com.example.kuangjia.common.CommonSubscriber;
import com.example.kuangjia.interfaces.shop.ShoppingConstact;
import com.example.kuangjia.models.HttpManager;
import com.example.kuangjia.models.bean.AddressBean;
import com.example.kuangjia.utils.RxUtils;

public class AdressPresenter extends BasePersenter<ShoppingConstact.AdressView> implements ShoppingConstact.AdressPresenter {

    @Override
    public void getAdressList() {
        addSubscribe(HttpManager.getInstance().getShopApi().getAddress()
        .compose(RxUtils.<AddressBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<AddressBean>(mView){

            @Override
            public void onNext(AddressBean addressBean) {
                mView.getAdressListReturn(addressBean);
            }
        }));
    }
}
