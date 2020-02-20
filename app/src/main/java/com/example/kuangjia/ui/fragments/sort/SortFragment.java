package com.example.kuangjia.ui.fragments.sort;

import androidx.viewpager.widget.ViewPager;

import com.example.kuangjia.R;
import com.example.kuangjia.adapter.VtbAdapter;
import com.example.kuangjia.base.BaseFragment;
import com.example.kuangjia.interfaces.sort.SortConstract;
import com.example.kuangjia.models.bean.TabItemBean;
import com.example.kuangjia.models.bean.VtlNameBean;
import com.example.kuangjia.persenter.sort.SortPersenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;

public class SortFragment extends BaseFragment<SortConstract.Persenter> implements SortConstract.View {

    @BindView(R.id.sort_vtb)
    VerticalTabLayout mSortVtb;
    @BindView(R.id.sort_vp)
    ViewPager mSortVp;

    @Override
    protected int getLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    protected void initView() {

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
        List<VtlNameBean.DataBean.CategoryListBean> categoryList = result.getData().getCategoryList();
        VtbAdapter vpsp = new VtbAdapter(getActivity().getSupportFragmentManager(),categoryList);
        mSortVp.setAdapter(vpsp);
        //进行关联
        mSortVtb.setupWithViewPager(mSortVp);
    }
}
