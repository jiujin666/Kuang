package com.example.kuangjia.interfaces.sort;

import com.example.kuangjia.interfaces.IBasePersenter;
import com.example.kuangjia.interfaces.IBaseView;
import com.example.kuangjia.models.bean.TabItemBean;
import com.example.kuangjia.models.bean.VtlNameBean;

public interface SortItemConstract {
    interface View extends IBaseView {
        //返回数据结果
        void getSortReturn(VtlNameBean result);
        void getSortItemReturn(TabItemBean result);
    }

    interface Persenter extends IBasePersenter<View> {
        //获取数据
        void getSort();
        void getSortItem(int id);
    }
}
