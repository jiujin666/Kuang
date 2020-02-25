package com.example.kuangjia.ui.activitys;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kuangjia.R;
import com.example.kuangjia.base.BaseActivity;
import com.example.kuangjia.interfaces.home.HotConstract;
import com.example.kuangjia.interfaces.home.NewsConstract;
import com.example.kuangjia.models.bean.NewsDetailBean;
import com.example.kuangjia.persenter.home.HotPersenter;
import com.example.kuangjia.persenter.home.NewsPersenter;

import butterknife.BindView;

public class HotActivity extends BaseActivity <HotConstract.Persenter> implements HotConstract.View {

    @BindView(R.id.hot_banner)
    ImageView iv;
    @BindView(R.id.hot_name)
    TextView tv;

    @Override
    protected int getLayout() {
        return R.layout.activity_hot;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        persenter.getNewsDetailBean();
    }

    @Override
    protected HotConstract.Persenter createPersenter() {
        return new HotPersenter();
    }

    @Override
    public void getNewsDetailReturn(NewsDetailBean result) {
        updateBanner(result.getData().getBannerInfo().getImg_url(),result.getData().getBannerInfo().getName());
    }

    private void updateBanner(String url,String name) {
        Glide.with(this).load(url).into(iv);
        tv.setText(name);
    }
}
