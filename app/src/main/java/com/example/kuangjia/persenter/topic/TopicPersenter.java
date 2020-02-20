package com.example.kuangjia.persenter.topic;

import com.example.kuangjia.base.BasePersenter;
import com.example.kuangjia.common.CommonSubscriber;
import com.example.kuangjia.interfaces.topic.TopicConstract;
import com.example.kuangjia.models.HttpManager;
import com.example.kuangjia.models.bean.IndexBean;
import com.example.kuangjia.models.bean.TopicBean;
import com.example.kuangjia.utils.RxUtils;

public class TopicPersenter extends BasePersenter<TopicConstract.View> implements TopicConstract.Persenter {
    @Override
    public void getTopicData() {
        addSubscribe(HttpManager.getInstance().getShopApi().getTopicData()
                .compose(RxUtils.<TopicBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<TopicBean>(mView){

                    @Override
                    public void onNext(TopicBean topicBean) {
                        mView.getTopicDataReturn(topicBean);
                    }
                }));
    }
}
