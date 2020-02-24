package com.example.kuangjia.ui.activitys;

import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kuangjia.R;
import com.example.kuangjia.base.BaseActivity;
import com.example.kuangjia.interfaces.sort.GoodInfoConstract;
import com.example.kuangjia.models.bean.RelatedBean;
import com.example.kuangjia.persenter.sort.GoodInfoPersenter;
import com.example.kuangjia.ui.fragments.home.HomeFragment;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GoodInfoActivity  extends BaseActivity<GoodInfoConstract.Persenter> implements GoodInfoConstract.View {

    @BindView(R.id.goodinfo_banner)
    Banner goodinfo_banner;
    @BindView(R.id.txt_title)
    TextView txt_title;
    @BindView(R.id.txt_des)
    TextView txt_des;
    @BindView(R.id.txt_price)
    TextView txt_price;
    @BindView(R.id.myWebView)
    WebView webView;
    @BindView(R.id.txt_metarial)
    TextView txt_metarial;
    @BindView(R.id.txt_size)
    TextView txt_size;
    @BindView(R.id.txt_color)
    TextView txt_color;
    @BindView(R.id.txt_norm)
    TextView txt_norm;
    @BindView(R.id.txt_place)
    TextView txt_place;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int getLayout() {
        return R.layout.activity_goodinfo;
    }

    @Override
    protected void initView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    protected void initData() {
        int relatedId = getIntent().getIntExtra("ids", 0);
        persenter.getRelatedData(relatedId);
    }

    @Override
    protected GoodInfoConstract.Persenter createPersenter() {
        return new GoodInfoPersenter();
    }

    @Override
    public void getRelatedDataReturn(RelatedBean result) {
        updateBanner(result.getData().getGallery());
        String price = getResources().getString(R.string.price_news_model).replace("$",String.valueOf(result.getData().getInfo().getRetail_price()));
        updatePrice(result.getData().getInfo().getName(),
                result.getData().getInfo().getGoods_brief(),price);
        //updateParam(result.getData().getAttribute());
        updateWebView(result.getData().getInfo());
    }

    private void updateBanner(List<RelatedBean.DataBeanX.GalleryBean> list){
        ArrayList<String> bannerList = new ArrayList<>();
        for (int i = 0; i <list.size(); i++) {
            bannerList.add(list.get(i).getImg_url());
        }
        goodinfo_banner.setImages(bannerList);
        goodinfo_banner.setImageLoader(new GlideImafeLoader());
        goodinfo_banner.start();
    }

    public class GlideImafeLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

    //填充信息数据
    private void updatePrice(String name,String des,String price){
        txt_title.setText(name);
        txt_des.setText(des);
        txt_price.setText(price);
    }
    //填充规格数据
    private void updateParam(List<RelatedBean.DataBeanX.AttributeBean> attribute){
        txt_metarial.setText(attribute.get(0).getValue());
        txt_size.setText(attribute.get(1).getValue());
        txt_color.setText(attribute.get(2).getValue());
        txt_norm.setText(attribute.get(3).getValue());
        txt_price.setText(attribute.get(4).getValue());
    }
    //商品介绍描述信息
    private void updateWebView(RelatedBean.DataBeanX.InfoBean infoBean){
        String css_str = getResources().getString(R.string.css_goods);
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head>");
        sb.append("<style>"+css_str+"</style></head><body>");
        sb.append(infoBean.getGoods_desc()+"</body></html>");
        webView.loadData(sb.toString(),"text/html","utf-8");
    }

    //商品列表
    private void updateGoodList(){

    }
}
