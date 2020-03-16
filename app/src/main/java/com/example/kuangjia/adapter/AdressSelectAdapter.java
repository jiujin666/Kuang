package com.example.kuangjia.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;


import com.example.kuangjia.R;
import com.example.kuangjia.base.BaseAdapter;
import com.example.kuangjia.models.bean.RegionBean;

import java.util.List;

public class AdressSelectAdapter extends BaseAdapter {

    public int curSelectId;

    public AdressSelectAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_adress_select_item;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {
        RegionBean.DataBean data = (RegionBean.DataBean) o;
        TextView txtName = (TextView) holder.getView(R.id.txt_name);
        if(curSelectId == data.getId()){
            txtName.setTextColor(Color.parseColor("#ffff00"));
        }else{
            txtName.setTextColor(Color.parseColor("#000000"));
        }
        txtName.setText(data.getName());

    }
}
