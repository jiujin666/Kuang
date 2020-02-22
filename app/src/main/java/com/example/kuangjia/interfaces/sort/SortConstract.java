package com.example.kuangjia.interfaces.sort;

import com.example.kuangjia.interfaces.IBasePersenter;
import com.example.kuangjia.interfaces.IBaseView;
import com.example.kuangjia.models.bean.SortDetialBean;
import com.example.kuangjia.models.bean.SortDetialGoodsBean;
import com.example.kuangjia.models.bean.TabItemBean;
import com.example.kuangjia.models.bean.VtlNameBean;

public interface SortConstract {
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

    interface DetailView extends IBaseView {
        //返回数据结果
        void getSortDetailReturn(SortDetialBean result);
        void getSortDetailGoodsReturn(SortDetialGoodsBean result);
    }

    interface DetailPersenter extends IBasePersenter<DetailView> {
        //获取数据
        void getSortDetailTab(int id);
        void getSortDetailGoods(int id,int page,int size);
    }
}
