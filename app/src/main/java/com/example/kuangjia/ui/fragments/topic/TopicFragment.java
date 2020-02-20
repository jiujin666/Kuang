package com.example.kuangjia.ui.fragments.topic;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kuangjia.R;
import com.example.kuangjia.adapter.TopicAdapter;
import com.example.kuangjia.base.BaseFragment;
import com.example.kuangjia.interfaces.IBasePersenter;
import com.example.kuangjia.interfaces.home.HomeConstract;
import com.example.kuangjia.interfaces.topic.TopicConstract;
import com.example.kuangjia.models.bean.TopicBean;
import com.example.kuangjia.persenter.topic.TopicPersenter;

import java.util.ArrayList;

import butterknife.BindView;

public class TopicFragment extends BaseFragment <TopicConstract.Persenter> implements TopicConstract.View {

    @BindView(R.id.topic_recy)
    RecyclerView mTopic_recy;
    private ArrayList<TopicBean.DataBean.TopicListBean> topicBean;
    private TopicAdapter topicAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_topic;
    }

    @Override
    protected void initView() {
        topicBean = new ArrayList<>();
        topicAdapter = new TopicAdapter(topicBean, context);
        mTopic_recy.setLayoutManager(new LinearLayoutManager(getContext()));
        mTopic_recy.setAdapter(topicAdapter);
    }

    @Override
    protected void initData() {
        persenter.getTopicData();
    }

    @Override
    protected TopicConstract.Persenter createPersenter() {
        return new TopicPersenter();
    }

    @Override
    public void getTopicDataReturn(TopicBean result) {
        topicAdapter.updata(result.getData().getTopicList());
    }
}
