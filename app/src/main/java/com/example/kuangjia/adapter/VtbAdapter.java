package com.example.kuangjia.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.kuangjia.models.bean.VtlNameBean;
import com.example.kuangjia.ui.fragments.sort.CommonFragment;
import java.util.List;

public class VtbAdapter extends FragmentPagerAdapter {

    private List<VtlNameBean.DataBean.CategoryListBean> vtlNameList;

    public VtbAdapter(FragmentManager fm, List<VtlNameBean.DataBean.CategoryListBean> vtlNameList) {
        super(fm);
        this.vtlNameList = vtlNameList;
    }

    @Override
    public Fragment getItem(int position) {
        return CommonFragment.getId(vtlNameList.get(position).getId());
    }

    @Override
    public int getCount() {
        return vtlNameList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return vtlNameList.get(position).getName();
    }
}
