package com.example.kuangjia.interfaces.home;

import com.example.kuangjia.interfaces.IBasePersenter;
import com.example.kuangjia.interfaces.IBaseView;
import com.example.kuangjia.models.bean.BrandDetialBean;

public interface BrandDetailConstract {
    interface View extends IBaseView {
        //返回数据结果
        void getBrandDetailReturn(BrandDetialBean result);
    }

    interface Persenter extends IBasePersenter<View> {
        //获取数据
        void getBrandDetail(int page,int size);
    }
}
