package com.example.kuangjia.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kuangjia.interfaces.IBasePersenter;
import com.example.kuangjia.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

//定义Activity的基类，用来实现Activity的基础功能
//1.应该包含用来处理网络数据逻辑的P层
//2.具备界面初始化的方法initViewBaseActivity()
//3.具备数据初始化的方法initData()
//4.获取当前电视的xml布局页面
//5.生命周期结束时解绑P层关联
public abstract class BaseActivity<P extends IBasePersenter> extends AppCompatActivity implements IBaseView {

    public   P persenter;
    Unbinder unbinder;

    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        context=this;
        unbinder= ButterKnife.bind(this);
        initView();
        persenter=createPersenter();
        if (persenter!=null){
            persenter.attachView(this);
        }
        initData();
    }

    //通过模板的设计模式，定义需要处理的方法
    protected abstract int getLayout();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract P createPersenter();

    @Override
    public void showError(String str) {
        Log.i("shibai",str);
    }

    //在界面移出的时候解绑P层和V层，以及ButterKnife
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (persenter!=null){
            persenter.detachView();
        }
        if (unbinder!=null){
            unbinder.unbind();
        }
    }
}
