package com.example.kuangjia.persenter.sort;

import com.example.kuangjia.base.BasePersenter;
import com.example.kuangjia.common.CommonSubscriber;
import com.example.kuangjia.interfaces.sort.SortItemConstract;
import com.example.kuangjia.models.HttpManager;
import com.example.kuangjia.models.bean.SortDetialBean;
import com.example.kuangjia.models.bean.SortDetialGoodsBean;
import com.example.kuangjia.models.bean.VtlNameBean;
import com.example.kuangjia.utils.RxUtils;

public class SortDetialPersenter extends BasePersenter<SortItemConstract.DetailView> implements SortItemConstract.DetailPersenter {

    @Override
    public void getSortDetailTab(int id) {
        addSubscribe(HttpManager.getInstance().getShopApi().getSortDetialTab(id)
                .compose(RxUtils.<SortDetialBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<SortDetialBean>(mView) {
                    @Override
                    public void onNext(SortDetialBean sortDetialBean) {
                        mView.getSortDetailReturn(sortDetialBean);
                    }
                }));
    }

    @Override
    public void getSortDetailGoods(int id, int page, int size) {
        addSubscribe(HttpManager.getInstance().getShopApi().getSortDetailGoods(id,page,size)
                .compose(RxUtils.<SortDetialGoodsBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<SortDetialGoodsBean>(mView) {
                    @Override
                    public void onNext(SortDetialGoodsBean sortDetialGoodsBean) {
                        mView.getSortDetailGoodsReturn(sortDetialGoodsBean);
                    }
                }));
    }
}
