package com.example.kuangjia.persenter.sort;

import com.example.kuangjia.base.BasePersenter;
import com.example.kuangjia.common.CommonSubscriber;
import com.example.kuangjia.interfaces.sort.SortConstract;
import com.example.kuangjia.models.HttpManager;
import com.example.kuangjia.models.bean.TabItemBean;
import com.example.kuangjia.models.bean.VtlNameBean;
import com.example.kuangjia.utils.RxUtils;

public class SortPersenter extends BasePersenter<SortConstract.View> implements SortConstract.Persenter {
    @Override
    public void getSort() {
        addSubscribe(HttpManager.getInstance().getShopApi().getVtlNames()
                .compose(RxUtils.<VtlNameBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<VtlNameBean>(mView) {
                    @Override
                    public void onNext(VtlNameBean vtlNameBean) {
                        mView.getSortReturn(vtlNameBean);
                    }
                }));
    }

    @Override
    public void getSortItem(int id) {
        addSubscribe(HttpManager.getInstance().getShopApi().getSortItems(id)
                .compose(RxUtils.<TabItemBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<TabItemBean>(mView) {
                    @Override
                    public void onNext(TabItemBean tabItemBean) {
                        mView.getSortItemReturn(tabItemBean);
                    }
                }));
    }
}
