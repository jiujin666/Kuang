package com.example.kuangjia.ui.activitys;

import android.util.Log;
import android.widget.FrameLayout;

import androidx.fragment.app.FragmentTransaction;

import com.example.kuangjia.R;
import com.example.kuangjia.base.BaseActivity;
import com.example.kuangjia.interfaces.test.TestConstract;
import com.example.kuangjia.models.bean.FuLiBean;
import com.example.kuangjia.persenter.test.TestPersenter;
import com.example.kuangjia.ui.fragments.home.HomeFragment;

import butterknife.BindView;

public class TestActivity extends BaseActivity<TestConstract.Persenter> implements TestConstract.View {

    @BindView(R.id.fl)
    FrameLayout frameLayout;


    @Override
    protected int getLayout() {
        return R.layout.test;
    }

    @Override
    protected void initView() {
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl,homeFragment).commit();
    }

    @Override
    protected void initData() {
        persenter.getFuLi();
    }

    @Override
    protected TestConstract.Persenter createPersenter() {
        return new TestPersenter();
    }

    @Override
    public void getFuLiReturn(FuLiBean fuLi) {
        Log.i("shuju",fuLi.toString());
    }
}
