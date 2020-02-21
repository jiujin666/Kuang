package com.example.kuangjia.ui.activitys;

import android.widget.TableLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kuangjia.R;
import com.example.kuangjia.adapter.SortDetailAdapter;
import com.example.kuangjia.base.BaseActivity;
import com.example.kuangjia.interfaces.IBasePersenter;
import com.example.kuangjia.interfaces.sort.SortItemConstract;
import com.example.kuangjia.models.bean.SortDetialBean;
import com.example.kuangjia.models.bean.SortDetialGoodsBean;
import com.example.kuangjia.persenter.sort.SortDetialPersenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SortDetailActivity extends BaseActivity<SortItemConstract.DetailPersenter> implements SortItemConstract.DetailView, TabLayout.BaseOnTabSelectedListener {

    @BindView(R.id.sort_tl)
    TabLayout sort_tl;
    @BindView(R.id.sort_name)
    TextView sort_name;
    @BindView(R.id.sort_title)
    TextView sort_title;
    @BindView(R.id.sort_recy)
    RecyclerView sort_recy;
    private List<SortDetialBean.DataBean.BrotherCategoryBean> tabs;
    private ArrayList<Object> goodsList;
    private SortDetailAdapter sortDetailAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_sortdetial;
    }

    @Override
    protected void initView() {
        sort_tl.setTabMode(TabLayout.MODE_SCROLLABLE);
        sort_tl.addOnTabSelectedListener(this);

        goodsList = new ArrayList<>();
        sortDetailAdapter = new SortDetailAdapter(goodsList, this);
        sort_recy.setLayoutManager(new GridLayoutManager(this,2));
        sort_recy.setAdapter(sortDetailAdapter);
    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("sortid", 0);
        persenter.getSortDetailTab(id);
    }

    @Override
    protected SortItemConstract.DetailPersenter createPersenter() {
        return new SortDetialPersenter();
    }

    @Override
    public void getSortDetailReturn(SortDetialBean result) {

        int position=-1;
        tabs = result.getData().getBrotherCategory();
        //动态添加tab导航的内容
        for (int i=0;i<result.getData().getBrotherCategory().size();i++){
            SortDetialBean.DataBean.BrotherCategoryBean item=result.getData().getBrotherCategory().get(i);
            TabLayout.Tab tab=sort_tl.newTab();
            tab.setText(item.getName());
            tab.setTag(item.getId());
            sort_tl.addTab(tab);
            if (result.getData().getCurrentCategory().getId()==item.getId()){
                position=i;
            }
        }
        if(position >= 0){
            sort_tl.getTabAt(position).select();
        }
    }

    @Override
    public void getSortDetailGoodsReturn(SortDetialGoodsBean result) {
       sortDetailAdapter.updata(result.getData().getGoodsList());
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        SortDetialBean.DataBean.BrotherCategoryBean bean = tabs.get(tab.getPosition());
        sort_name.setText(bean.getFront_name());
        sort_title.setText(bean.getFront_desc());
        int id = (int) tab.getTag();
        persenter.getSortDetailGoods(id,1,1000);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
