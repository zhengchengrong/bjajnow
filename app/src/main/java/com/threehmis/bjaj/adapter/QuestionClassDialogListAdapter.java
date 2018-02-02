package com.threehmis.bjaj.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.bean.respon.GetCheckQuestionClassRsp;

import java.util.ArrayList;
import java.util.List;


public class QuestionClassDialogListAdapter extends RecyclerView.Adapter<QuestionClassDialogListAdapter.ViewHolder>{

    private List<GetCheckQuestionClassRsp.QuestionListBean> liststr = new ArrayList<>();

    private  OnRecyclerViewItemClickListener mOnItemClickListener = null;


    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_questionclassdapter_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
//        viewHolder.setIsRecyclable(false);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.type.setVisibility(View.VISIBLE);
        holder.type.setText(liststr.get(position).type);

        if (position != 0) {
            holder.type.setVisibility(liststr.get(position).type.equals(liststr.get(position - 1).type) ? View.GONE : View.VISIBLE);
        }

        holder.itemName.setText(liststr.get(position).questions);
        holder.itemLabel.setText(liststr.get(position).dealNote);

        holder.layout_itemclick.setOnClickListener(new clicklistener(liststr.get(position).type,liststr.get(position).questions,liststr.get(position).dealNote));
    }

    @Override
    public int getItemCount() {
        return liststr.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView type, itemName, itemLabel;
        LinearLayout layout_itemclick;

        public ViewHolder(View itemView) {
            super(itemView);

            layout_itemclick= (LinearLayout) itemView.findViewById(R.id.layout_itemclick);

            type = (TextView) itemView.findViewById(R.id.type);
            itemName = (TextView) itemView.findViewById(R.id.itemName);
            itemLabel = (TextView) itemView.findViewById(R.id.itemLabel);

        }

    }

    public void notifyData(List<GetCheckQuestionClassRsp.QuestionListBean> listRsps, int pageIndex) {

        if (pageIndex == 1)
            this.liststr = listRsps;
        else
            this.liststr.addAll(listRsps);

        notifyDataSetChanged();

    }

    class clicklistener implements View.OnClickListener{

        String date1, date2,date3;

        public clicklistener(String date1, String date2, String date3) {
            this.date1 = date1;
            this.date2 = date2;
            this.date3 = date3;
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                //注意这里使用getTag方法获取数据
                mOnItemClickListener.onItemClick(date1,date2,date3);
            }
        }
    }

    public  interface  OnRecyclerViewItemClickListener {

        void onItemClick(String date1, String date2, String date3);

    }


}
