package com.example.kuangjia.ui.fragments.sort;

import android.content.Intent;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kuangjia.R;
import com.example.kuangjia.adapter.SortAdapter;
import com.example.kuangjia.base.BaseAdapter;
import com.example.kuangjia.base.BaseFragment;
import com.example.kuangjia.interfaces.sort.SortConstract;
import com.example.kuangjia.models.bean.TabItemBean;
import com.example.kuangjia.models.bean.VtlNameBean;
import com.example.kuangjia.persenter.sort.SortPersenter;
import com.example.kuangjia.ui.activitys.SortDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class SortFragment extends BaseFragment<SortConstract.Persenter> implements SortConstract.View,VerticalTabLayout.OnTabSelectedListener, BaseAdapter.ItemClickHandler {

    @BindView(R.id.sort_vtb)
    VerticalTabLayout mSortVtb;
    @BindView(R.id.sort_rv)
    RecyclerView sort_rv;
    @BindView(R.id.sort_iv)
    ImageView sort_iv;
    @BindView(R.id.sort_tv)
    TextView sort_tv;
    @BindView(R.id.tab_name)
     TextView tab_name;

    List<String> titleName;
    private ArrayList<TabItemBean.DataBean.CurrentCategoryBean.SubCategoryListBean> goodsItemList;
    private SortAdapter goodsAdapter;
    private List<VtlNameBean.DataBean.CategoryListBean> vtlNameList;

    @Override
    protected int getLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    protected void initView() {
        titleName=new ArrayList<>();
        mSortVtb.addOnTabSelectedListener(this);
        goodsItemList = new ArrayList<>();
        goodsAdapter = new SortAdapter(goodsItemList, context);
        sort_rv.setLayoutManager(new GridLayoutManager(context,3));
        sort_rv.setAdapter(goodsAdapter);
        goodsAdapter.setOnItemClickHandler(this);
    }

    @Override
    protected void initData() {
        persenter.getSort();
    }

    @Override
    protected SortConstract.Persenter createPersenter() {
        return new SortPersenter();
    }




    @Override
    public void getSortReturn(VtlNameBean result) {
        vtlNameList = result.getData().getCategoryList();
        for (VtlNameBean.DataBean.CategoryListBean item:result.getData().getCategoryList()){
            titleName.add(item.getName());
        }
        mSortVtb.setTabAdapter(tabAdapter);
        updateInfo(result.getData().getCurrentCategory().getBanner_url(),
                result.getData().getCurrentCategory().getFront_desc(),
                result.getData().getCurrentCategory().getName());
        List<TabItemBean.DataBean.CurrentCategoryBean.SubCategoryListBean> list = new ArrayList<>();
        for(VtlNameBean.DataBean.CurrentCategoryBean.SubCategoryListBean item:result.getData().getCurrentCategory().getSubCategoryList()){
            TabItemBean.DataBean.CurrentCategoryBean.SubCategoryListBean object = new TabItemBean.DataBean.CurrentCategoryBean.SubCategoryListBean();
            object.setName(item.getName());
            object.setFront_desc(item.getFront_desc());
            object.setId(item.getId());
            object.setIcon_url(item.getIcon_url());
            object.setWap_banner_url(item.getWap_banner_url());
            list.add(object);
        }
        goodsAdapter.updata(list);
    }

    private void updateInfo(String imgUrl,String name,String title){
        Glide.with(context).load(imgUrl).into(sort_iv);
        tab_name.setText(name);
        sort_tv.setText(title+"分类");
    }

    @Override
    public void getSortItemReturn(TabItemBean result) {
        updateInfo(result.getData().getCurrentCategory().getWap_banner_url(),
                result.getData().getCurrentCategory().getFront_desc(),
                result.getData().getCurrentCategory().getName());
        List<TabItemBean.DataBean.CurrentCategoryBean.SubCategoryListBean> list = new ArrayList<>();
        list.addAll(result.getData().getCurrentCategory().getSubCategoryList());
        goodsAdapter.updata(list);
    }

    @Override
    public void onTabSelected(TabView tab, int position) {
        if(position < vtlNameList.size()){
            int id = vtlNameList.get(position).getId();
            persenter.getSortItem(id);
        }
    }

    @Override
    public void onTabReselected(TabView tab, int position) {

    }

    //竖导航的tabadapter
    TabAdapter tabAdapter = new TabAdapter() {
        @Override
        public int getCount() {
            return titleName.size();
        }

        @Override
        public ITabView.TabBadge getBadge(int position) {
            return null;
        }

        @Override
        public ITabView.TabIcon getIcon(int position) {
            return null;
        }

        @Override
        public ITabView.TabTitle getTitle(int position) {
            QTabView.TabTitle title = new QTabView.TabTitle.Builder()
                    .setContent(titleName.get(position))
                    .build();
            return title;
        }

        @Override
        public int getBackground(int position) {
            return Color.parseColor("#EE3E3E");
        }
    };

    @Override
    public void itemClick(int position, BaseAdapter.BaseViewHolder holder) {
        int id = goodsItemList.get(position).getId();
        Intent intent = new Intent(context, SortDetailActivity.class);
        intent.putExtra("sortid",id);
        startActivity(intent);
    }
}
