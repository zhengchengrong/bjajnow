package com.threehmis.bjaj.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.respon.GetMenusListRsp;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.jsj.JsjActivity;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.localcheck.LocalCheckActivity;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.monitorrecode.MonitorRecodeActivity;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.projectinfo.ProjectInfoActivity;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.qzjx.QzjxActivity;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.rectificationnotify.RectificationNotifyActivity;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.safecomment.SafeCommentActivity;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.schedule.ScheduleActivity;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.taskcheck.TaskCheckActivity;
import com.threehmis.bjaj.utils.SPUtils;
import com.threehmis.bjaj.utils.VHUtil;
import com.vondear.rxtools.RxSPUtils;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by 3hcd on 2017/3/9.
 */

public class MenusGridviewAdapter extends android.widget.BaseAdapter {

    List<GetMenusListRsp> listRsps = new ArrayList<>();
    private Activity activity;
    private String projectID, projectName,projectCode,sgxkzh;

    public MenusGridviewAdapter(Activity activity, String projectID, String projectName,String projectCode,String sgxkzh) {
        this.activity = activity;
        this.projectID = projectID;
        this.projectName = projectName;
        this.projectCode = projectCode;
        this.sgxkzh = sgxkzh;
    }

    @Override
    public int getCount() {
        return listRsps.size();
    }

    @Override
    public GetMenusListRsp getItem(int i) {
        return listRsps.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int positon, View convertView, ViewGroup viewGroup) {

        if (convertView == null)
            convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_menus_gridview_item, viewGroup, false);

        ImageView url = VHUtil.ViewHolder.get(convertView, R.id.imageurl);
        TextView num = VHUtil.ViewHolder.get(convertView, R.id.textname);

        Glide.with(activity).load(RetrofitFactory.BASE_URL + listRsps.get(positon).menuUrl).diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.a1).centerCrop().into(url);

        num.setText(listRsps.get(positon).menuNameShow);

        convertView.setOnClickListener(new clicklistener(listRsps.get(positon).menuNameShow, positon));
        return convertView;
    }

    public void DateNotify(List<GetMenusListRsp> listRsps) {
        this.listRsps = listRsps;
        notifyDataSetChanged();

    }

    class clicklistener implements View.OnClickListener {

        String name;
        int pos;

        public clicklistener(String name, int pos) {

            this.name = name;
            this.pos = pos;
        }

        @Override
        public void onClick(View view) {
            RxSPUtils.putString(activity,Const.PROJECTID,projectID); // 保存项目id
            Intent  intent;
            switch (pos) {
                case 0:  //工程信息
                     intent = new Intent(activity, ProjectInfoActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putString(Const.PROJECTID, projectID);
                    intent.putExtra(Const.PROJECTNAME, projectName);
                    intent.putExtras(bundle1);
                    activity.startActivity(intent);
                    break;

               case 1://形象进度
                   intent = new Intent(activity, ScheduleActivity.class);
                   intent.putExtra(Const.PROJECTID,projectID);
                    activity.startActivity(intent);
                    break;
                case 4://任务指派
                    intent = new Intent(activity, TaskCheckActivity.class);
                    intent.putExtra(Const.PROJECTID,projectID);
                    activity.startActivity(intent);
                    break;
                case 5://现场检查
                    intent = new Intent(activity, LocalCheckActivity.class);
                    intent.putExtra(Const.PROJECTID,projectID);
                    activity.startActivity(intent);
                    break;
                case 6://监督记录
                    intent = new Intent(activity, MonitorRecodeActivity.class);
                    intent.putExtra(Const.PROJECTID,projectID);
                    activity.startActivity(intent);
                    break;
                case 7://整改通知
                    intent = new Intent(activity, RectificationNotifyActivity.class);
                    intent.putExtra(Const.PROJECTID,projectID);
                    activity.startActivity(intent);
                    break;
                case 9://安评信息
                    intent = new Intent(activity, SafeCommentActivity.class);
                    intent.putExtra(Const.PROJECTID,projectID);
                    intent.putExtra(Const.PROJECTCODE,projectCode);
                    activity.startActivity(intent);
                    break;
                case 11://起重机械
                    intent = new Intent(activity, QzjxActivity.class);
                    intent.putExtra(Const.PROJECTID,projectID);
                    intent.putExtra(Const.PROJECTCODE,projectCode);
                    intent.putExtra(Const.SGXKZH,sgxkzh);
                    activity.startActivity(intent);
                    break;
                case 12://脚手架
                    intent = new Intent(activity, JsjActivity.class);
                    intent.putExtra(Const.PROJECTID,projectID);
                    intent.putExtra(Const.PROJECTCODE,projectCode);
                    intent.putExtra(Const.SGXKZH,sgxkzh);
                    activity.startActivity(intent);
                    break;
                /*
                case "安全监督":  //安全监督

                    if (listRsps.get(pos).interfaceType == 1)//1表示对外使用
                    RetrofitFactory.BASE_URL = (String) SPUtils.get(activity, Const.INTERFACEURL,"");
                    else
                        RetrofitFactory.BASE_URL = GetData.morenurl;

                    Intent intent3 = new Intent(activity, SafeSupervisionActivity.class);
                    Bundle bundle = new Bundle(); //该类用作携带数据
                    bundle.putString("projectID", projectID);
                    intent3.putExtras(bundle);
                    activity.startActivity(intent3);
                    break;
                case "质量监督": //质量监督

                    if (listRsps.get(pos).interfaceType == 1)//1表示对外使用
                    RetrofitFactory.BASE_URL = (String) SPUtils.get(activity, Const.INTERFACEURL,"");
                    else
                        RetrofitFactory.BASE_URL = GetData.morenurl;
                    Intent intent4 = new Intent(activity, QualitySupervisionActivity.class);
                    Bundle bundl4 = new Bundle(); //该类用作携带数据
                    bundl4.putString("projectID", projectID);
                    bundl4.putBoolean("isquality", true);
                    intent4.putExtras(bundl4);
                    activity.startActivity(intent4);
                    break;
                case "整改通知": //整改通知
                    if (listRsps.get(pos).interfaceType == 1)//1表示对外使用
                    RetrofitFactory.BASE_URL = (String) SPUtils.get(activity, Const.INTERFACEURL,"");
                    else
                        RetrofitFactory.BASE_URL = GetData.morenurl;

                    Intent intent5 = new Intent(activity, MRectificationNotificationActivity.class);
                    Bundle bundle5 = new Bundle(); //该类用作携带数据
                    bundle5.putString("projectID", projectID);
                    intent5.putExtras(bundle5);
                    activity.startActivity(intent5);

                    break;
                case "停工管理"://停工管理
                    if (listRsps.get(pos).interfaceType == 1)//1表示对外使用
                    RetrofitFactory.BASE_URL = (String) SPUtils.get(activity, Const.INTERFACEURL,"");
                    else
                        RetrofitFactory.BASE_URL = GetData.morenurl;

                    Intent intent6 = new Intent(activity, StopWorkActivity.class);
                    Bundle bundl6 = new Bundle(); //该类用作携带数据
                    bundl6.putString("projectID", projectID);
                    bundl6.putBoolean("isstop", true);
                    intent6.putExtras(bundl6);
                    activity.startActivity(intent6);

                    break;
                case "复工管理": //复工管理
                    if (listRsps.get(pos).interfaceType == 1)//1表示对外使用
                    RetrofitFactory.BASE_URL = (String) SPUtils.get(activity, Const.INTERFACEURL,"");
                    else
                        RetrofitFactory.BASE_URL = GetData.morenurl;
                    Intent intent7 = new Intent(activity, ReworkActivity.class);
                    Bundle bundl7 = new Bundle(); //该类用作携带数据
                    bundl7.putString("projectID", projectID);
                    intent7.putExtras(bundl7);
                    activity.startActivity(intent7);

                    break;
                case "关键人员到岗"://关键人员到岗s
                    if (listRsps.get(pos).interfaceType == 1)//1表示对外使用
                    RetrofitFactory.BASE_URL = (String) SPUtils.get(activity, Const.INTERFACEURL,"");
                    else
                        RetrofitFactory.BASE_URL = GetData.morenurl;
//                Intent intent8 = new Intent(this,PersonPostActivity.class);
//                intent8.putExtra("projectID",projectID);
//                startActivity(intent8);

                    Intent intent8 = new Intent(activity, PersonPostOutActivity.class);
                    intent8.putExtra("projectID", projectID);
                    intent8.putExtra("pk", projectID);//工程编码+“-”+”当前的日期（例如：GCBM-20170331）
                    activity.startActivity(intent8);
                    break;
                case "不良行为管理":
                    //不良行为管理
                    if (listRsps.get(pos).interfaceType == 1)//1表示对外使用
                    RetrofitFactory.BASE_URL = (String) SPUtils.get(activity, Const.INTERFACEURL,"");
                    else
                        RetrofitFactory.BASE_URL = GetData.morenurl;
                    Intent intent9 = new Intent(activity, BadBehaviorActivity.class);
                    Bundle bundl9 = new Bundle(); //该类用作携带数据
                    bundl9.putString("projectID", projectID);
                    intent9.putExtras(bundl9);
                    activity.startActivity(intent9);

                    break;
                case "检测报告查验"://检测报告查验

                    Intent intent10 = new Intent(activity, ExamReportActivity.class);
                    activity.startActivity(intent10);
                    break;
                case "现场见证查询": //现场见证查询

                    Intent intent11 = new Intent(activity, EtlSamplcheckActivity.class);
                    activity.startActivity(intent11);
                    break;
                case "现场芯片查询": //芯片抽检

                    Intent intent12 = new Intent(activity, IcScanActivity.class);
                    activity.startActivity(intent12);
                    break;

                //7-20新需求
                case "监督检测": //监督检测
                    if (listRsps.get(pos).interfaceType == 1)//1表示对外使用
                    RetrofitFactory.BASE_URL = (String) SPUtils.get(activity, Const.INTERFACEURL,"");
                    else
                        RetrofitFactory.BASE_URL = GetData.morenurl;
                    Intent intent13 = new Intent(activity, InspectionActivity.class);
                    intent13.putExtra("projectID", projectID);
                    activity.startActivity(intent13);

                    break;
                case "监督验收": //监督验收
                    if (listRsps.get(pos).interfaceType == 1)//1表示对外使用
                    RetrofitFactory.BASE_URL = (String) SPUtils.get(activity, Const.INTERFACEURL,"");
                    else
                        RetrofitFactory.BASE_URL = GetData.morenurl;
                    Intent intent14 = new Intent(activity, AcceptanceActivity.class);
                    intent14.putExtra("projectID", projectID);
                    activity.startActivity(intent14);

                    break;
                case "日常巡查": //日常巡查
                    if (listRsps.get(pos).interfaceType == 1)//1表示对外使用
                    RetrofitFactory.BASE_URL = (String) SPUtils.get(activity, Const.INTERFACEURL,"");
                    else
                        RetrofitFactory.BASE_URL = GetData.morenurl;
                    Intent intent15 = new Intent(activity, RichangActivity.class);
                    intent15.putExtra("projectID", projectID);
                    activity.startActivity(intent15);
                    break;
*/
                default:
                    break;
            }
        }
    }
}
