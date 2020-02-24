package com.example.kuangjia.models.api;

import com.example.kuangjia.models.bean.BrandBean;
import com.example.kuangjia.models.bean.BrandDetialBean;
import com.example.kuangjia.models.bean.BrandGoodsBean;
import com.example.kuangjia.models.bean.HotCommoditBean;
import com.example.kuangjia.models.bean.IndexBean;
import com.example.kuangjia.models.bean.LookingBean;
import com.example.kuangjia.models.bean.NewsCommoditBean;
import com.example.kuangjia.models.bean.NewsDetailBean;
import com.example.kuangjia.models.bean.RelatedBean;
import com.example.kuangjia.models.bean.SortDetialBean;
import com.example.kuangjia.models.bean.SortDetialGoodsBean;
import com.example.kuangjia.models.bean.TabItemBean;
import com.example.kuangjia.models.bean.TopBean;
import com.example.kuangjia.models.bean.TopDetailBean;
import com.example.kuangjia.models.bean.TopRecommend;
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
    //新品详情页面的banner
    @GET("goods/hot")
    Flowable<NewsDetailBean> getNewsDetailBean();
    @GET("/brand/list")
    Flowable<BrandDetialBean> getBrandDetailBean(@Query("page") int page, @Query("size") int size);
    //新品，首发，居家等商品购买页的RecyclerView数据
    @GET("goods/related")
    Flowable<LookingBean> getLookBean(@Query("id")int id);
    //新品标题打开的列表页
    @GET("goods/list")
    Flowable<NewsCommoditBean> getNewsCommoditBean(@Query("isNew") int isNews,@Query("page") int page,@Query("size") int size,@Query("order") String order,@Query("sort") String sort,@Query("categoryId") int categoryId);
    //人气推荐标题打开的列表页
    @GET("goods/list")
    Flowable<HotCommoditBean> getHotCommoditBean(@Query("isHot") int isHot,@Query("page") int page,@Query("size") int size,@Query("order") String order,@Query("sort") String sort,@Query("categoryId") int categoryId);
    //专题列表
    @GET("topic/list")
    Flowable<TopBean> getTop();
    //专题详情
    @GET("topic/detail")
    Flowable<TopDetailBean> getTopDetail(@Query("id") int id);
    //专题推荐
    @GET("topic/related")
    Flowable<TopRecommend> getTopRecommend(@Query("id") int id);
    //商品购买页面的数据接口
    @GET("goods/detail")
    Flowable<RelatedBean> getRelatedData(@Query("id") int id);

}
