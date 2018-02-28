package com.threehmis.bjaj.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.bean.respon.GetLoginListRsp;
import com.threehmis.bjaj.module.home.fragment.map.ProjectActivity;

import java.util.ArrayList;
import java.util.List;


public class ProjectListotherAdapter extends RecyclerView.Adapter<ProjectListotherAdapter.ViewHolder> {

    private List<GetLoginListRsp> listRsps = new ArrayList<>();

    Context context;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        this.context =parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_projectlist_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
//        viewHolder.setIsRecyclable(false);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.num.setText((position+1)+"");
        holder.name.setText(listRsps.get(position).projectName);
        holder.area.setText(listRsps.get(position).address);

        holder.layout_item.setOnClickListener(new clicklisenter(position));

    }

    @Override
    public int getItemCount() {
        return listRsps!=null?listRsps.size():0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,area,num;
        LinearLayout layout_item;

        public ViewHolder(View itemView) {
            super(itemView);

            layout_item= (LinearLayout) itemView.findViewById(R.id.layout_item);
            name = (TextView) itemView.findViewById(R.id.name);
            area = (TextView) itemView.findViewById(R.id.area);
            num = (TextView) itemView.findViewById(R.id.num);

        }

    }



    private class clicklisenter implements View.OnClickListener {

        public clicklisenter(int position) {
            this.position = position;
        }

        int position;

        @Override
        public void onClick(View view) {

            Intent intent = new Intent(context, ProjectActivity.class);
            intent.putExtra("projectID",listRsps.get(position).projectID);
            intent.putExtra("projectName",listRsps.get(position).projectName);
            intent.putExtra(Const.PROJECTCODE,listRsps.get(position).getProjectCode());
            intent.putExtra("customerId", AndroidApplication.getInstance().getcustomerId());
            intent.putExtra(Const.SGXKZH,listRsps.get(position).getSgxkzh());

            context.startActivity(intent);
        }
    }

    public void notifyData(List<GetLoginListRsp> listRsps, int pageIndex) {

        if (pageIndex == 1)
            this.listRsps = listRsps;
        else
            this.listRsps.addAll(listRsps);

        notifyDataSetChanged();

    }


}
