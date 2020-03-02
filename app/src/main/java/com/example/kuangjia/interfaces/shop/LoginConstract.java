package com.example.kuangjia.interfaces.shop;

import com.example.kuangjia.interfaces.IBasePersenter;
import com.example.kuangjia.interfaces.IBaseView;
import com.example.kuangjia.models.bean.UserBean;

public interface LoginConstract {
    interface View extends IBaseView {
        void loginReturn(UserBean result);
    }

    interface Persenter extends IBasePersenter<View> {
        void login(String nickname,String password);
    }
}
