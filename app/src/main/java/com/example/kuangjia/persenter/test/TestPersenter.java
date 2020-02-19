package com.example.kuangjia.persenter.test;

import com.example.kuangjia.base.BasePersenter;
import com.example.kuangjia.common.CommonSubscriber;
import com.example.kuangjia.interfaces.test.TestConstract;
import com.example.kuangjia.models.HttpManager;
import com.example.kuangjia.models.bean.FuLiBean;
import com.example.kuangjia.utils.RxUtils;

public class TestPersenter extends BasePersenter<TestConstract.View> implements TestConstract.Persenter {
    @Override
    public void getFuLi() {
        addSubscribe(HttpManager.getInstance().getFuLiApi().getFuLi()
        .compose(RxUtils.<FuLiBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<FuLiBean>(mView) {
                    @Override
                    public void onNext(FuLiBean fuLiBean) {
                        mView.getFuLiReturn(fuLiBean);
                    }
                }));

    }
}
