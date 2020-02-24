package com.example.kuangjia.interfaces.sort;

import com.example.kuangjia.interfaces.IBasePersenter;
import com.example.kuangjia.interfaces.IBaseView;
import com.example.kuangjia.models.bean.RelatedBean;
import com.example.kuangjia.models.bean.RelatedBottonBean;

public interface GoodInfoConstract {
     interface View extends IBaseView {
        void getRelatedDataReturn(RelatedBean result);
        void getRelateBottonRetrun(RelatedBottonBean result);
    }

     interface Persenter extends IBasePersenter<View> {
        void getRelatedData(int id);
        void getBottonData(int id);
    }
}
