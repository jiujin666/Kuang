package com.example.kuangjia.persenter.sort;

import com.example.kuangjia.base.BasePersenter;
import com.example.kuangjia.common.CommonSubscriber;
import com.example.kuangjia.interfaces.sort.GoodInfoConstract;
import com.example.kuangjia.models.HttpManager;
import com.example.kuangjia.models.bean.AddShopBean;
import com.example.kuangjia.models.bean.RelatedBean;
import com.example.kuangjia.models.bean.RelatedBottonBean;
import com.example.kuangjia.utils.RxUtils;

public class GoodInfoPersenter extends BasePersenter<GoodInfoConstract.View> implements GoodInfoConstract.Persenter  {

    @Override
    public void getRelatedData(int id) {
        addSubscribe(HttpManager.getInstance().getShopApi().getRelatedData(id)
                .compose(RxUtils.<RelatedBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<RelatedBean>(mView){

                    @Override
                    public void onNext(RelatedBean relatedBean) {
                        mView.getRelatedDataReturn(relatedBean);
                    }
                }));
    }

    @Override
    public void getBottonData(int id) {
        addSubscribe(HttpManager.getInstance().getShopApi().getBottonBean(id)
                .compose(RxUtils.<RelatedBottonBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<RelatedBottonBean>(mView){

                    @Override
                    public void onNext(RelatedBottonBean relatedBean) {
                        mView.getRelateBottonRetrun(relatedBean);
                    }
                }));
    }

    @Override
    public void getaddShopData(String goodsId, String number, String id) {
        addSubscribe(HttpManager.getInstance().getShopApi().addshop(goodsId,number,id)
                .compose(RxUtils.<AddShopBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<AddShopBean>(mView){

                    @Override
                    public void onNext(AddShopBean shopBean) {
                        mView.addShop(shopBean);
                    }
                }));
    }
}
