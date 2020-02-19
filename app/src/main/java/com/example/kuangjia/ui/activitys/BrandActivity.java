package com.example.kuangjia.ui.activitys;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kuangjia.R;
import com.example.kuangjia.adapter.BrandGoodsAdapter;
import com.example.kuangjia.base.BaseActivity;
import com.example.kuangjia.interfaces.home.BrandConstract;
import com.example.kuangjia.models.bean.BrandBean;
import com.example.kuangjia.models.bean.BrandGoodsBean;
import com.example.kuangjia.persenter.home.BrandPersenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BrandActivity extends BaseActivity<BrandConstract.Persenter> implements BrandConstract.View {

    int brandId;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.brand_details)
    TextView details;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    BrandGoodsAdapter brandGoodsAdapter;
    List<BrandGoodsBean.DataBeanX.GoodsListBean> goodsList;

    @Override
    protected int getLayout() {
        return R.layout.activity_brand;
    }

    @Override
    protected void initView() {
        goodsList = new ArrayList<>();
        brandGoodsAdapter = new BrandGoodsAdapter(goodsList,this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(brandGoodsAdapter);
    }

    @Override
    protected void initData() {
        brandId = getIntent().getIntExtra("brandId", 0);
        persenter.getBrandInfo(String.valueOf(brandId));
        persenter.getBrandGoods(String.valueOf(brandId), 1, 1000);
    }

    @Override
    protected BrandConstract.Persenter createPersenter() {
        return new BrandPersenter();
    }

    @Override
    public void getBrandInfoReturn(BrandBean result) {
        Glide.with(this).load(result.getData().getBrand().getNew_pic_url()).into(imgBack);
        txtTitle.setText(result.getData().getBrand().getName());
        details.setText(result.getData().getBrand().getSimple_desc());
    }

    @Override
    public void getBrandGoodsReturn(BrandGoodsBean result) {
        brandGoodsAdapter.updata(result.getData().getGoodsList());
    }

}
