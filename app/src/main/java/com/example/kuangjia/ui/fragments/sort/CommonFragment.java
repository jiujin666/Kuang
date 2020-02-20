package com.example.kuangjia.ui.fragments.sort;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kuangjia.R;
import com.example.kuangjia.adapter.CommonAdapter;
import com.example.kuangjia.base.BaseFragment;
import com.example.kuangjia.interfaces.IBasePersenter;
import com.example.kuangjia.interfaces.sort.SortConstract;
import com.example.kuangjia.interfaces.sort.SortItemConstract;
import com.example.kuangjia.models.bean.TabItemBean;
import com.example.kuangjia.models.bean.VtlNameBean;
import com.example.kuangjia.persenter.sort.SortItemPersenter;

import java.util.ArrayList;

import butterknife.BindView;

public class CommonFragment extends BaseFragment<SortItemConstract.Persenter> implements SortItemConstract.View {
    @BindView(R.id.comm_iv)
    ImageView comm_iv;
    @BindView(R.id.comm_tv)
    TextView comm_tv;
    @BindView(R.id.heng_tv)
    TextView heng_tv;
    @BindView(R.id.comm_rv)
    RecyclerView comm_rv;

    private static int mTabId = 0;
    private ArrayList<TabItemBean.DataBean.CurrentCategoryBean.SubCategoryListBean> sortItemList;
    private CommonAdapter commonAdapter;

    public static CommonFragment getId(int id) {
        mTabId = id;
        return new CommonFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_common;
    }

    @Override
    protected void initView() {
        heng_tv.setText("—分类—");
        sortItemList = new ArrayList<>();
        commonAdapter = new CommonAdapter(sortItemList, context);
        comm_rv.setLayoutManager(new GridLayoutManager(getContext(), 3));
        comm_rv.setAdapter(commonAdapter);
    }

    @Override
    protected void initData() {
        persenter.getSort();
        persenter.getSortItem(mTabId);
    }

    @Override
    protected SortItemConstract.Persenter createPersenter() {
        return new SortItemPersenter();
    }

    @Override
    public void getSortReturn(VtlNameBean result) {
        VtlNameBean.DataBean.CurrentCategoryBean currentBean = result.getData().getCurrentCategory();
        Glide.with(getContext()).load(currentBean.getWap_banner_url()).into(comm_iv);
        comm_tv.setText(currentBean.getFront_name());
    }

    @Override
    public void getSortItemReturn(TabItemBean result) {
        commonAdapter.updata(result.getData().getCurrentCategory().getSubCategoryList());
    }

}
