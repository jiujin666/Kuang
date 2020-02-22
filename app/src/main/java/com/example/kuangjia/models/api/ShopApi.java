package com.example.kuangjia.models.api;

import com.example.kuangjia.models.bean.BrandBean;
import com.example.kuangjia.models.bean.BrandGoodsBean;
import com.example.kuangjia.models.bean.IndexBean;
import com.example.kuangjia.models.bean.SortDetialBean;
import com.example.kuangjia.models.bean.SortDetialGoodsBean;
import com.example.kuangjia.models.bean.TabItemBean;
import com.example.kuangjia.models.bean.TopicBean;
import com.example.kuangjia.models.bean.VtlNameBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShopApi {
    @GET("index")
    Flowable<IndexBean> getIndexData();
    //品牌直供的详情页数据接口
    @GET("brand/detail")
    Flowable<BrandBean> getBrandInfo(@Query("id") String id);
    //品牌直供详情的商品列表数据接口
    @GET("goods/list")
    Flowable<BrandGoodsBean> getBrandGoods(@Query("brandId") String brandId, @Query("page") int page, @Query("size") int size);
    //分类名字的接口
    @GET("catalog/index")
    Flowable<VtlNameBean> getVtlNames();
    @GET("catalog/current")
    Flowable<TabItemBean> getSortItems(@Query("id")int id);
    @GET("index")
    Flowable<TopicBean> getTopicData();
    @GET("goods/category")
    Flowable<SortDetialBean> getSortDetialTab(@Query("id")int id);
    @GET("goods/list")
    Flowable<SortDetialGoodsBean> getSortDetailGoods(@Query("categoryId")int id, @Query("page")int page, @Query("size") int size);
}
