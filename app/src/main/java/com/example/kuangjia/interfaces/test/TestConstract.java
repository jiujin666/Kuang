package com.example.kuangjia.interfaces.test;

import com.example.kuangjia.interfaces.IBasePersenter;
import com.example.kuangjia.interfaces.IBaseView;
import com.example.kuangjia.models.bean.FuLiBean;

public interface TestConstract {
    interface View extends IBaseView {
        void getFuLiReturn(FuLiBean fuLi);
    }

    interface Persenter extends IBasePersenter<View> {
        void getFuLi();
    }
}
