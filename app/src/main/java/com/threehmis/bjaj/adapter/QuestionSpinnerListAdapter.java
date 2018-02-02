package com.threehmis.bjaj.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.threehmis.bjaj.R;
import com.threehmis.bjaj.utils.VHUtil;


public class QuestionSpinnerListAdapter extends BaseAdapter {

   String[] liststr; //= {"行为管理","实体抽查"};

    public QuestionSpinnerListAdapter(String[] liststr) {
        this.liststr = liststr;
    }


    @Override
    public int getCount() {
        return liststr == null ? 0 : liststr.length;
    }

    @Override
    public String getItem(int i) {
        return liststr[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public int getPosition(String str){
        int i=0;
        for (String str2: liststr) {
            i++;
            if (str2.equals(str))
                return i;

        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_question_spinnerapter_item, parent, false);

        TextView num = VHUtil.ViewHolder.get(convertView, R.id.num);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(10, 5, 0, 0);
        num.setLayoutParams(lp);

        num.setText(liststr[position]);

        return convertView;
    }


}
