package com.example.kuangjia.persenter.shop;


import com.example.kuangjia.base.BasePersenter;
import com.example.kuangjia.common.CommonSubscriber;
import com.example.kuangjia.interfaces.shop.ShoppingConstact;
import com.example.kuangjia.models.HttpManager;
import com.example.kuangjia.models.bean.AdressSaveBean;
import com.example.kuangjia.models.bean.RegionBean;
import com.example.kuangjia.utils.RxUtils;

import java.util.Map;

public class AdressNewPresenter extends BasePersenter<ShoppingConstact.AdressNewView> implements ShoppingConstact.AdressNewPresenter{

    //保存地址
    @Override
    public void saveAdress(Map map) {
        addSubscribe(HttpManager.getInstance().getShopApi().saveAdress(map)
                .compose(RxUtils.<AdressSaveBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<AdressSaveBean>(mView){

                    @Override
                    public void onNext(AdressSaveBean saveBean) {
                        mView.saveAdressReturn(saveBean);
                    }
                }));
    }

    //获取省市区数据
    @Override
    public void getRegion(int parentId) {
        addSubscribe(HttpManager.getInstance().getShopApi().getRegion(parentId)
                .compose(RxUtils.<RegionBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<RegionBean>(mView){

                    @Override
                    public void onNext(RegionBean regionBean) {
                        mView.getRegionReturn(regionBean);
                    }
                }));
    }
}
