package com.example.kuangjia.ui.fragments.me;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.kuangjia.R;
import com.example.kuangjia.adapter.MyRecyAdapter;
import com.example.kuangjia.base.BaseFragment;
import com.example.kuangjia.interfaces.IBasePersenter;
import com.example.kuangjia.models.bean.MyHomeItemBean;

import java.util.ArrayList;

import butterknife.BindView;

public class MeFragment extends BaseFragment {

    @BindView(R.id.my_iv)
    ImageView my_iv;
    @BindView(R.id.my_tv)
    TextView my_tv;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private ArrayList<MyHomeItemBean> al;
    private MyRecyAdapter myRecyAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {
        RequestOptions requestOptions = new RequestOptions();
        Glide.with(getContext()).load(R.mipmap.touxiang).apply(requestOptions.circleCrop()).into(my_iv);
        al = new ArrayList<>();
        initList();
        myRecyAdapter = new MyRecyAdapter(al,context);
        mRecyclerview.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mRecyclerview.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL));
        mRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecyclerview.setAdapter(myRecyAdapter);
        myRecyAdapter.notifyDataSetChanged();
    }

    private void initList() {
        al.add(new MyHomeItemBean(R.mipmap.weicang,"我的订单"));
        al.add(new MyHomeItemBean(R.mipmap.weicang,"优惠券"));
        al.add(new MyHomeItemBean(R.mipmap.weicang,"礼品卡"));
        al.add(new MyHomeItemBean(R.mipmap.weicang,"我的收藏"));
        al.add(new MyHomeItemBean(R.mipmap.weicang,"我的足迹"));
        al.add(new MyHomeItemBean(R.mipmap.weicang,"会员福利"));
        al.add(new MyHomeItemBean(R.mipmap.weicang,"地址管理"));
        al.add(new MyHomeItemBean(R.mipmap.weicang,"账号安全"));
        al.add(new MyHomeItemBean(R.mipmap.weicang,"联系客服"));
        al.add(new MyHomeItemBean(R.mipmap.weicang,"帮助中心"));
        al.add(new MyHomeItemBean(R.mipmap.weicang,"意见反馈"));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected IBasePersenter createPersenter() {
        return null;
    }
}
