package com.example.kuangjia.ui.fragments.home;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.kuangjia.R;
import com.example.kuangjia.activity.BrandActivity;
import com.example.kuangjia.adapter.BrandAdapter;
import com.example.kuangjia.base.BaseAdapter;
import com.example.kuangjia.base.BaseFragment;
import com.example.kuangjia.interfaces.home.HomeConstract;
import com.example.kuangjia.models.bean.IndexBean;
import com.example.kuangjia.persenter.home.HomePersenter;
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
    private List<IndexBean.DataBean.BrandListBean> brandList;
    BrandAdapter brandAdapter;

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
    }

    public class GlideImafeLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }


}