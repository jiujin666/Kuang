package com.example.kuangjia.interfaces.home;

import com.example.kuangjia.interfaces.IBasePersenter;
import com.example.kuangjia.interfaces.IBaseView;
import com.example.kuangjia.models.bean.BrandBean;
import com.example.kuangjia.models.bean.BrandGoodsBean;

public interface BrandConstract {
    interface View extends IBaseView {
        void getBrandInfoReturn(BrandBean result);
        void getBrandGoodsReturn(BrandGoodsBean result);
    }

    interface Persenter extends IBasePersenter<View> {
        void getBrandInfo(String id);
        void getBrandGoods(String brandId, int page, int size);
    }
}
