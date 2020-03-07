package com.example.kuangjia.interfaces.shop;

import com.example.kuangjia.interfaces.IBasePersenter;
import com.example.kuangjia.interfaces.IBaseView;
import com.example.kuangjia.models.bean.UserBean;
import com.example.kuangjia.models.bean.VerifyBean;

public interface RegisterConstract {
    interface View extends IBaseView {
        void getVerifyReturn(VerifyBean result);
        void getUserReturn(UserBean userBean);
    }

    interface Persenter extends IBasePersenter<View> {
        void getVerify();
        void getUser(String nickname,String password,String verify);
    }
}
