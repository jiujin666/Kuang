package com.example.kuangjia.interfaces.shop;

import com.example.kuangjia.interfaces.IBasePersenter;
import com.example.kuangjia.interfaces.IBaseView;
import com.example.kuangjia.models.bean.CartBean;

public interface ShoppingConstact {
    interface View extends IBaseView {
        void getCartIndexReturn(CartBean result);
    }

    interface Presenter extends IBasePersenter<View> {
        void getCartIndex();
    }
}
