package com.example.kuangjia.ui.fragments.home;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.kuangjia.R;
import com.example.kuangjia.adapter.BrandAdapter;
import com.example.kuangjia.adapter.HotAdapter;
import com.example.kuangjia.adapter.NewsAdapter;
import com.example.kuangjia.adapter.TopAdapter;
import com.example.kuangjia.base.BaseAdapter;
import com.example.kuangjia.base.BaseFragment;
import com.example.kuangjia.interfaces.home.HomeConstract;
import com.example.kuangjia.models.bean.IndexBean;
import com.example.kuangjia.persenter.home.HomePersenter;
import com.example.kuangjia.ui.activitys.BrandActivity;
import com.example.kuangjia.ui.activitys.BrandDetailActivity;
import com.example.kuangjia.ui.activitys.HotActivity;
import com.example.kuangjia.ui.activitys.NewsActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomeConstract.Persenter> implements HomeConstract.View, BaseAdapter.ItemClickHandler {


    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.home_zhizao_recy)
    RecyclerView mHomeZhizaoRecy;
    @BindView(R.id.home_news_recy)
    RecyclerView home_news_recy;
    @BindView(R.id.home_hot_recy)
    RecyclerView home_hot_recy;
    @BindView(R.id.home_top_recy)
    RecyclerView home_top_recy;
    @BindView(R.id.brand_heading)
    TextView brand_heading;
    @BindView(R.id.news_heading)
    TextView news_heading;
    @BindView(R.id.hot_heading)
    TextView hot_heading;

    private List<IndexBean.DataBean.BrandListBean> brandList;
    BrandAdapter brandAdapter;
    private ArrayList<IndexBean.DataBean.NewGoodsListBean> goodsList;
    private NewsAdapter newsAdapter;
    private ArrayList<IndexBean.DataBean.TopicListBean> topList;
    private ArrayList<IndexBean.DataBean.HotGoodsListBean> hotList;
    private HotAdapter hotAdapter;
    private TopAdapter topAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        brandList=new ArrayList<>();
        brandAdapter = new BrandAdapter(brandList,context);
        mHomeZhizaoRecy.setLayoutManager(new GridLayoutManager(context,2));
        mHomeZhizaoRecy.setAdapter(brandAdapter);
        brandAdapter.setOnItemClickHandler(this);

        goodsList = new ArrayList<>();
        newsAdapter = new NewsAdapter(goodsList, context);
        home_news_recy.setLayoutManager(new GridLayoutManager(context,2));
        home_news_recy.setAdapter(newsAdapter);

        hotList = new ArrayList<>();
        hotAdapter = new HotAdapter(hotList,context);
        home_hot_recy.setLayoutManager(new LinearLayoutManager(getContext()));
        home_hot_recy.setAdapter(hotAdapter);

        topList = new ArrayList<>();
        topAdapter = new TopAdapter(topList,context);
        home_top_recy.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        home_top_recy.setAdapter(topAdapter);

        brand_heading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BrandDetailActivity.class);
                startActivity(intent);
            }
        });

        news_heading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewsActivity.class);
                startActivity(intent);
            }
        });

        hot_heading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HotActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        persenter.getHomeData();
    }

    @Override
    protected HomeConstract.Persenter createPersenter() {
        return new HomePersenter();
    }


    @Override
    public void itemClick(int position, BaseAdapter.BaseViewHolder holder) {
        IndexBean.DataBean.BrandListBean bean = brandList.get(position);
        Intent intent = new Intent(getContext(), BrandActivity.class);
        intent.putExtra("brandId",bean.getId());
        startActivity(intent);
    }

    @Override
    public void getHomeDataReturn(IndexBean result) {
        List<String> bannerList = new ArrayList<>();
        List<IndexBean.DataBean.BannerBean> banner = result.getData().getBanner();
        for (int i = 0; i <banner.size() ; i++) {
            String image_url = banner.get(i).getImage_url();
            bannerList.add(image_url);
        }
        mBanner.setImages(bannerList);
        mBanner.setImageLoader(new GlideImafeLoader());
        mBanner.start();
        //刷新Brand列表数据
        brandAdapter.updata(result.getData().getBrandList());
        newsAdapter.updata(result.getData().getNewGoodsList());
        hotAdapter.updata(result.getData().getHotGoodsList());
        topAdapter.updata(result.getData().getTopicList());

    }

    public class GlideImafeLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }


}