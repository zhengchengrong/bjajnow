package com.threehmis.bjaj.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.bean.respon.GetCheckQuestionClassRsp;
import com.threehmis.bjaj.utils.VHUtil;

import java.util.ArrayList;
import java.util.List;


public class PopupList3Adapter extends BaseAdapter {

    List<GetCheckQuestionClassRsp.TitleListBean> liststr = new ArrayList<>();

    public PopupList3Adapter(List<GetCheckQuestionClassRsp.TitleListBean> liststr) {
        this.liststr = liststr;
    }

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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_popuplist_item, parent, false);

        TextView num = VHUtil.ViewHolder.get(convertView, R.id.num);

        num.setText(liststr.get(position).projectBranch);

        return convertView;
    }

//    public void adddate(List<String> str) {
//        this.liststr = str;
//        notifyDataSetChanged();
//    }

}
