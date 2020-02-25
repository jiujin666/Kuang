package com.example.kuangjia.persenter.home;

import com.example.kuangjia.base.BasePersenter;
import com.example.kuangjia.common.CommonSubscriber;
import com.example.kuangjia.interfaces.home.NewsConstract;
import com.example.kuangjia.models.HttpManager;
import com.example.kuangjia.models.bean.NewsDetailBean;
import com.example.kuangjia.utils.RxUtils;

public class NewsPersenter extends BasePersenter<NewsConstract.View> implements NewsConstract.Persenter {
    @Override
    public void getNewsDetailBean() {
        addSubscribe(HttpManager.getInstance().getShopApi().getNewsDetailBean()
                .compose(RxUtils.<NewsDetailBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<NewsDetailBean>(mView){

                    @Override
                    public void onNext(NewsDetailBean newsDetailBean) {
                        mView.getNewsDetailReturn(newsDetailBean);
                    }
                }));
    }
}
