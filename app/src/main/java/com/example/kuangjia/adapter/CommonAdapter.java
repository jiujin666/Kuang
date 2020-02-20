package com.example.kuangjia.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kuangjia.R;
import com.example.kuangjia.base.BaseAdapter;
import com.example.kuangjia.models.bean.TabItemBean;

import java.util.List;

public class CommonAdapter extends BaseAdapter {
    public CommonAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return R.layout.comm_item;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {
        TabItemBean.DataBean.CurrentCategoryBean.SubCategoryListBean commItemBean= (TabItemBean.DataBean.CurrentCategoryBean.SubCategoryListBean) o;
        ImageView image = (ImageView) holder.getView(R.id.coitem_iv);
        TextView text = (TextView) holder.getView(R.id.coitem_tv);
        text.setText(commItemBean.getName());
        Glide.with(mContext).load(commItemBean.getWap_banner_url()).into(image);

    }
}
