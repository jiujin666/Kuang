package com.example.kuangjia.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kuangjia.R;
import com.example.kuangjia.base.BaseAdapter;
import com.example.kuangjia.models.bean.IndexBean;
import com.example.kuangjia.models.bean.TopicBean;

import java.util.List;

public class TopAdapter extends BaseAdapter {
    public TopAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_top_item;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {
        IndexBean.DataBean.TopicListBean topBean= (IndexBean.DataBean.TopicListBean) o;
        ImageView image = (ImageView) holder.getView(R.id.top_iv);
        TextView special_tv_black = (TextView) holder.getView(R.id.top_name);
        TextView special_tv_gray = (TextView) holder.getView(R.id.top_title);
        TextView special_tv_red = (TextView) holder.getView(R.id.top_price);
        Glide.with(mContext).load(topBean.getItem_pic_url()).into(image);
        special_tv_black.setText(topBean.getTitle());
        special_tv_gray.setText(topBean.getSubtitle());
        special_tv_red.setText(topBean.getPrice_info()+"元起");
    }
}
