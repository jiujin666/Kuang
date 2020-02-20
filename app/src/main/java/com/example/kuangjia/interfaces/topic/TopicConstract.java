package com.example.kuangjia.interfaces.topic;

import com.example.kuangjia.interfaces.IBasePersenter;
import com.example.kuangjia.interfaces.IBaseView;
import com.example.kuangjia.models.bean.TopicBean;

public interface TopicConstract {
    interface View extends IBaseView {
        void getTopicDataReturn(TopicBean result);
    }

    interface Persenter extends IBasePersenter<View> {
        void getTopicData();
    }
}
