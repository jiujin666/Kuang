package com.example.kuangjia.persenter.home;

import com.example.kuangjia.base.BasePersenter;
import com.example.kuangjia.common.CommonSubscriber;
import com.example.kuangjia.interfaces.home.BrandDetailConstract;
import com.example.kuangjia.models.HttpManager;
import com.example.kuangjia.models.bean.BrandDetialBean;
import com.example.kuangjia.utils.RxUtils;

public class BrandDetailPersenter extends BasePersenter<BrandDetailConstract.View> implements BrandDetailConstract.Persenter {
    @Override
    public void getBrandDetail(int page,int sieze) {
        addSubscribe(HttpManager.getInstance().getShopApi().getBrandDetailBean(page,sieze)
                .compose(RxUtils.<BrandDetialBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandDetialBean>(mView){

                    @Override
                    public void onNext(BrandDetialBean brandDetialBean) {
                        mView.getBrandDetailReturn(brandDetialBean);
                    }
                }));
    }
}
