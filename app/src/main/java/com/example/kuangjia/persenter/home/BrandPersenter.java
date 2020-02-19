package com.example.kuangjia.persenter.home;

import com.example.kuangjia.base.BasePersenter;
import com.example.kuangjia.common.CommonSubscriber;
import com.example.kuangjia.interfaces.home.BrandConstract;
import com.example.kuangjia.models.HttpManager;
import com.example.kuangjia.models.bean.BrandBean;
import com.example.kuangjia.models.bean.BrandGoodsBean;
import com.example.kuangjia.utils.RxUtils;

public class BrandPersenter extends BasePersenter<BrandConstract.View> implements BrandConstract.Persenter {

    @Override
    public void getBrandInfo(String id) {
        addSubscribe(HttpManager.getInstance().getShopApi().getBrandInfo(id)
                .compose(RxUtils.<BrandBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandBean>(mView){

                    @Override
                    public void onNext(BrandBean brandBean) {
                        mView.getBrandInfoReturn(brandBean);
                    }
                }));
    }

    @Override
    public void getBrandGoods(String brandId, int page, int size) {
        addSubscribe(HttpManager.getInstance().getShopApi().getBrandGoods(brandId,page,size)
                .compose(RxUtils.<BrandGoodsBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandGoodsBean>(mView){

                    @Override
                    public void onNext(BrandGoodsBean brandGoodsBean) {
                        mView.getBrandGoodsReturn(brandGoodsBean);
                    }
                }));
    }
}
