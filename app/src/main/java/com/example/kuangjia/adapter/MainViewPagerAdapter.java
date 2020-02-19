package com.example.kuangjia.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private String[] mNames={"首页","专题","分类","购物车","我的"};
    private List<Fragment> mFragment;

    public MainViewPagerAdapter(FragmentManager fm, List<Fragment> mFragment) {
        super(fm);
        this.mFragment = mFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mNames[position];
    }
}
