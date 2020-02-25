package com.example.kuangjia.interfaces.home;

import com.example.kuangjia.interfaces.IBasePersenter;
import com.example.kuangjia.interfaces.IBaseView;
import com.example.kuangjia.models.bean.NewsDetailBean;

public interface NewsConstract {
    interface View extends IBaseView {
        //返回数据结果
        void getNewsDetailReturn(NewsDetailBean result);
    }

    interface Persenter extends IBasePersenter<View> {
        //获取数据
        void getNewsDetailBean();
    }
}
