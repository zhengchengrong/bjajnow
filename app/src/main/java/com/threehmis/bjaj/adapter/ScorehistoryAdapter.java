package com.threehmis.bjaj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.bean.respon.GetCheckScoreHistoryRsp;
import com.threehmis.bjaj.dialog.DeleteDialog;

import java.util.ArrayList;
import java.util.List;


public class ScorehistoryAdapter extends RecyclerView.Adapter<ScorehistoryAdapter.ViewHolder> {

    private List<GetCheckScoreHistoryRsp> listRsps = new ArrayList<>();

    Context context;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        this.context=parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_scorehistory_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.title.setVisibility(View.VISIBLE);
        holder.title.setText(listRsps.get(position).type);

        if (position != 0) {
            holder.title.setVisibility(listRsps.get(position).type.equals(listRsps.get(position - 1).type) ? View.GONE : View.VISIBLE);
        }

        holder.content.setText(listRsps.get(position).itemName);
        holder.num.setText(listRsps.get(position).scoreValue+"");
        holder.num2.setText(listRsps.get(position).checkScore+"");

        holder.content_layout.setOnClickListener(new onclickListener(listRsps.get(position).pk));


    }

    @Override
    public int getItemCount() {
        return listRsps!=null?listRsps.size():0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView content,title,num,num2;
        LinearLayout content_layout;
        public ViewHolder(View itemView) {
            super(itemView);

            content_layout= (LinearLayout) itemView.findViewById(R.id.content_layout);
            num= (TextView) itemView.findViewById(R.id.num);
            num2= (TextView) itemView.findViewById(R.id.num2);
            title= (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);

        }

    }

    public void notifyData(List<GetCheckScoreHistoryRsp> listRsps, int pageIndex) {

        if (pageIndex == 1)
            this.listRsps = listRsps;
        else
            this.listRsps.addAll(listRsps);

        notifyDataSetChanged();

    }


    private class onclickListener implements View.OnClickListener {
        String pk;

        public onclickListener(String pk) {
            this.pk = pk;
        }

        @Override
        public void onClick(View view) {

            new DeleteDialog(context,pk,"评分记录").show();
        }
    }

}
