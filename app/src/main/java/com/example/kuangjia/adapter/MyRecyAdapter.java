package com.example.kuangjia.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kuangjia.R;
import com.example.kuangjia.base.BaseAdapter;
import com.example.kuangjia.models.bean.MyHomeItemBean;

import java.util.List;

public class MyRecyAdapter extends BaseAdapter {
    public MyRecyAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return R.layout.me_item;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {
        MyHomeItemBean homeItemBean= (MyHomeItemBean) o;
        ImageView me_iv = (ImageView) holder.getView(R.id.me_recy_iv);
        TextView me_tv = (TextView) holder.getView(R.id.me_recy_tv);
        me_iv.setImageResource(homeItemBean.getImage());
        me_tv.setText(homeItemBean.getTitle());
    }
}
