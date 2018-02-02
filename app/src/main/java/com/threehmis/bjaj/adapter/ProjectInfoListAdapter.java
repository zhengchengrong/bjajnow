package com.threehmis.bjaj.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.bean.respon.GetProjectInfoRsp;
import com.threehmis.bjaj.utils.VHUtil;

import java.util.ArrayList;
import java.util.List;


public class ProjectInfoListAdapter extends BaseAdapter {

    List<GetProjectInfoRsp.DataListBean> liststr = new ArrayList<>();


    @Override
    public int getCount() {
        return liststr == null ? 0 : liststr.size();
    }

    @Override
    public Object getItem(int i) {
        return liststr.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_infoadapter_item, parent, false);

        TextView type = VHUtil.ViewHolder.get(convertView, R.id.type);
        View line = VHUtil.ViewHolder.get(convertView, R.id.line);
        TextView itemName = VHUtil.ViewHolder.get(convertView, R.id.itemName);
        TextView itemLabel = VHUtil.ViewHolder.get(convertView, R.id.itemLabel);

        type.setVisibility(View.VISIBLE);
        line.setVisibility(View.VISIBLE);
        type.setText(liststr.get(position).type);

        if (position != 0) {
            type.setVisibility(liststr.get(position).type.equals(liststr.get(position - 1).type) ? View.GONE : View.VISIBLE);
            line.setVisibility(liststr.get(position).type.equals(liststr.get(position - 1).type) ? View.GONE : View.VISIBLE);
        }

        itemName.setText(liststr.get(position).duty);
        itemLabel.setText(liststr.get(position).name);

        return convertView;
    }

    public void adddate(List<GetProjectInfoRsp.DataListBean> str) {
        this.liststr = str;
        notifyDataSetChanged();
    }

}
