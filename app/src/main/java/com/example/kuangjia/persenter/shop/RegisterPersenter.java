package com.example.kuangjia.persenter.shop;

import com.example.kuangjia.base.BasePersenter;
import com.example.kuangjia.common.CommonSubscriber;
import com.example.kuangjia.interfaces.shop.RegisterConstract;
import com.example.kuangjia.models.HttpManager;
import com.example.kuangjia.models.bean.UserBean;
import com.example.kuangjia.models.bean.VerifyBean;
import com.example.kuangjia.utils.RxUtils;

public class RegisterPersenter extends BasePersenter<RegisterConstract.View> implements RegisterConstract.Persenter {
    @Override
    public void getVerify() {
        addSubscribe(HttpManager.getInstance().getShopApi().getVerify()
                .compose(RxUtils.<VerifyBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<VerifyBean>(mView){

                    @Override
                    public void onNext(VerifyBean verifyBean) {
                        mView.getVerifyReturn(verifyBean);
                    }
                }));
    }

    @Override
    public void getUser(String nickname,String password,String verify) {
        addSubscribe(HttpManager.getInstance().getShopApi().regist(nickname,password,verify)
                .compose(RxUtils.<UserBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<UserBean>(mView){

                    @Override
                    public void onNext(UserBean verifyBean) {
                        mView.getUserReturn(verifyBean);
                    }
                }));
    }
}
