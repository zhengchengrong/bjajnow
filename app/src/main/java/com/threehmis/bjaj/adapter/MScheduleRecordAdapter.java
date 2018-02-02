package com.threehmis.bjaj.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.bean.respon.GetScheduleRecordRsp;
import com.threehmis.bjaj.dialog.EditDeleteDialog;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.schedule.ScheduleDetailActivity;

import java.util.ArrayList;
import java.util.List;


public class MScheduleRecordAdapter extends RecyclerView.Adapter<MScheduleRecordAdapter.ViewHolder> {

    private List<GetScheduleRecordRsp> listRsps = new ArrayList<GetScheduleRecordRsp>();

    Activity activity;

    public MScheduleRecordAdapter(Activity activity) {
        this.activity = activity;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.m_layout_schedule_record_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
//        viewHolder.setIsRecyclable(false);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.num.setText((position+1)+"");
        holder.part.setText(listRsps.get(position).projectXXJD);
        holder.time.setText(listRsps.get(position).updatedate);
        holder.formSchedule.setText(listRsps.get(position).projectPart);

        holder.layout_all.setOnClickListener(new cliclistener(listRsps.get(position).projectID,listRsps.get(position).id,listRsps.get(position).isSubmit));

    }

    @Override
    public int getItemCount() {
        return listRsps.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView time,formSchedule,num,part;
        LinearLayout layout_all;

        public ViewHolder(View itemView) {
            super(itemView);
            layout_all= (LinearLayout) itemView.findViewById(R.id.layout_all);
            time = (TextView) itemView.findViewById(R.id.tv_time_schedule_record);//时间
            formSchedule = (TextView) itemView.findViewById(R.id.tv_isreform_schedule_record);//形象进度
            num = (TextView) itemView.findViewById(R.id.tv_Num_schedule_record);//序号
            part = (TextView) itemView.findViewById(R.id.tv_part_schedule_record);//施工部位
        }

    }

    public void notifyData(List<GetScheduleRecordRsp> listRsps, int pageIndex) {

        if (pageIndex == 1)
            this.listRsps = listRsps;
        else
            this.listRsps.addAll(listRsps);

        notifyDataSetChanged();

    }


    private class cliclistener implements View.OnClickListener {

        String projectID,id,isSubmit;
        private cliclistener(String projectID, String id, String isSubmit) {
            this.projectID=projectID;
            this.id=id;
            this.isSubmit=isSubmit;
        }

        @Override
        public void onClick(View view) {

            if (!isSubmit.equals("1"))
                new EditDeleteDialog(activity,projectID,id,"形象进度").show();
            else {

                Intent intent2 = new Intent(activity, ScheduleDetailActivity.class);
                Bundle bundle2 = new Bundle(); //该类用作携带数据
                bundle2.putString("projectID", projectID);
                bundle2.putString("id", id);
                intent2.putExtras(bundle2);
                activity.startActivity(intent2);

                //Toast.makeText(activity, "此信息已提交!", Toast.LENGTH_SHORT).show();

            }


        }
    }


}
