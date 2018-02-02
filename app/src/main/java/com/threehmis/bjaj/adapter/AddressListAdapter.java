package com.threehmis.bjaj.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.threehmis.bjaj.R;
import com.threehmis.bjaj.utils.VHUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 3hcd on 2017/3/9.
 */

public class AddressListAdapter extends android.widget.BaseAdapter{


    List<String> list = new ArrayList<>();

//    public AddressListAdapter(List<String> list) {
//        this.list = list;
//    }

    @Override
    public int getCount() {
        return list!=null?list.size():0;
    }

    @Override
    public String getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int positon, View convertView, ViewGroup viewGroup) {

        if (convertView==null)
            convertView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_address_list_item, viewGroup, false);

        TextView name = VHUtil.ViewHolder.get(convertView, R.id.name);
        name.setText(list.get(positon));

        return convertView;
    }



    public void adddate(List<String> str) {
        this.list = str;
        notifyDataSetChanged();
    }

}
