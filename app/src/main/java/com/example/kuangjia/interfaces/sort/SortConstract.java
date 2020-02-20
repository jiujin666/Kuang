package com.example.kuangjia.interfaces.sort;

import com.example.kuangjia.interfaces.IBasePersenter;
import com.example.kuangjia.interfaces.IBaseView;
import com.example.kuangjia.models.bean.VtlNameBean;

public interface SortConstract {
    interface View extends IBaseView {
        //返回数据结果
        void getSortReturn(VtlNameBean result);
    }

    interface Persenter extends IBasePersenter<View> {
        //获取数据
        void getSort();
    }
}
