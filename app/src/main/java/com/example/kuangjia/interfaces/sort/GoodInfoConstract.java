package com.example.kuangjia.interfaces.sort;

import com.example.kuangjia.interfaces.IBasePersenter;
import com.example.kuangjia.interfaces.IBaseView;
import com.example.kuangjia.models.bean.RelatedBean;

public class GoodInfoConstract {
    public interface View extends IBaseView {
        void getRelatedDataReturn(RelatedBean result);
    }

    public interface Persenter extends IBasePersenter<View> {
        void getRelatedData(int id);
    }
}
