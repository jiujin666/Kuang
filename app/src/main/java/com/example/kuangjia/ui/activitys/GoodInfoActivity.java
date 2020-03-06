package com.example.kuangjia.ui.activitys;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kuangjia.R;
import com.example.kuangjia.adapter.RelateBottonAdapter;
import com.example.kuangjia.base.BaseActivity;
import com.example.kuangjia.interfaces.sort.GoodInfoConstract;
import com.example.kuangjia.models.bean.RelatedBean;
import com.example.kuangjia.models.bean.RelatedBottonBean;
import com.example.kuangjia.persenter.sort.GoodInfoPersenter;
import com.example.kuangjia.ui.fragments.home.HomeFragment;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GoodInfoActivity  extends BaseActivity<GoodInfoConstract.Persenter> implements GoodInfoConstract.View {

    @BindView(R.id.cons)
    ConstraintLayout constraintLayout;
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
    @BindView(R.id.txt_cart)
    TextView txt_cart;
    @BindView(R.id.txt_addCart)
    TextView txt_addCart;
    @BindView(R.id.txt_buy)
    TextView txt_buy;
    @BindView(R.id.txt_collect)
    TextView txt_collect;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ArrayList<RelatedBottonBean.DataBean.GoodsListBean> bottonList;
    private RelateBottonAdapter relateBottonAdapter;
    private ArrayList<String> bannerList;
    private String price;


    @Override
    protected int getLayout() {
        return R.layout.activity_goodinfo;
    }

    @Override
    protected void initView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        bottonList = new ArrayList<>();
        relateBottonAdapter = new RelateBottonAdapter(bottonList, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(relateBottonAdapter);
        txt_addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupwindow();
            }
        });
        txt_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                showPopupwindow();
            }
        });
    }

    public void showPopupwindow(){
        View view = LayoutInflater.from(this).inflate(R.layout.shop_pop,null);
        ImageView shop_img = view.findViewById(R.id.shop_img);
        TextView shop_price = view.findViewById(R.id.shop_price);
        TextView minus = view.findViewById(R.id.minus);
        TextView num = view.findViewById(R.id.num);
        TextView put = view.findViewById(R.id.put);
        Glide.with(this).load(bannerList.get(0)).into(shop_img);
        shop_price.setText(price);
        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(constraintLayout,Gravity.BOTTOM,0,-200);
    }

    @Override
    protected void initData() {
        int relatedId = getIntent().getIntExtra("ids", 0);
        persenter.getRelatedData(relatedId);
        persenter.getBottonData(relatedId);
    }

    @Override
    protected GoodInfoConstract.Persenter createPersenter() {
        return new GoodInfoPersenter();
    }

    @Override
    public void getRelatedDataReturn(RelatedBean result) {
        updateBanner(result.getData().getGallery());
        price = getResources().getString(R.string.price_news_model).replace("$",String.valueOf(result.getData().getInfo().getRetail_price()));
        updatePrice(result.getData().getInfo().getName(),
                result.getData().getInfo().getGoods_brief(), price);
        updateParam(result.getData().getAttribute());
        updateWebView(result.getData().getInfo());
    }

    @Override
    public void getRelateBottonRetrun(RelatedBottonBean result) {
        relateBottonAdapter.updata(result.getData().getGoodsList());
    }

    private void updateBanner(List<RelatedBean.DataBeanX.GalleryBean> list){
        bannerList = new ArrayList<>();
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

}
