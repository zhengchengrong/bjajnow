package com.threehmis.bjaj.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.GetDeleteReq;
import com.threehmis.bjaj.api.bean.respon.StateSuccessRsp;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.schedule.ScheduleActivity;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.schedule.ScheduleRecordFragment;
import com.threehmis.bjaj.utils.ScreenUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author CD
 *         记录里编辑和删除dialog
 */
public class EditDeleteDialog extends android.app.Dialog implements
        View.OnClickListener {

    Activity activity;
    private String projectID, id, whichnoe;
    private BaseBeanRsp<StateSuccessRsp> stateSuccessRsp;


    public EditDeleteDialog(Activity activity, String projectID, String id, String whichnoe) {
        this(activity, R.style.enterDialog);
        this.activity = activity;
        this.projectID = projectID;
        this.id = id;
        this.whichnoe = whichnoe;
    }

    public EditDeleteDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_editdelete_dialog);
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.alpha = 1.0f;
        wl.gravity = Gravity.BOTTOM;
        wl.width = ScreenUtil.getScreenWidth(activity);
        window.setAttributes(wl);
        setCancelable(true);
        setCanceledOnTouchOutside(true);

        findViewById(R.id.edit).setOnClickListener(this);
        findViewById(R.id.dele).setOnClickListener(this);
        findViewById(R.id.cancer).setOnClickListener(this);

        this.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                mHandler.removeCallbacksAndMessages(null);
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.edit:

                switch (whichnoe) {
                    case "形象进度":
                        activity.finish();
                        Intent intent2 = new Intent(activity, ScheduleActivity.class);
                        Bundle bundle2 = new Bundle(); //该类用作携带数据
                        bundle2.putString("projectID", projectID);
                        bundle2.putString("id", id);
                        intent2.putExtras(bundle2);
                        activity.startActivity(intent2);
                        break;
                  /*  case "安全监督":
                        activity.finish();
                        Intent intent3 = new Intent(activity, SafeSupervisionActivity.class);
                        Bundle bundle = new Bundle(); //该类用作携带数据
                        bundle.putString("projectID", projectID);
                        bundle.putString("pk", id);
                        intent3.putExtras(bundle);
                        activity.startActivity(intent3);
                        break;
                    case "质量监督":
                        activity.finish();
                        Intent intent4 = new Intent(activity, QualitySupervisionActivity.class);
                        Bundle bundl4 = new Bundle(); //该类用作携带数据
                        bundl4.putString("projectID", projectID);
                        bundl4.putString("pk", id);
                        bundl4.putBoolean("isquality", true);
                        intent4.putExtras(bundl4);
                        activity.startActivity(intent4);
                        break;

                    case "整改未回复":

                        activity.finish();
                        Intent intent5 = new Intent(activity, MRectificationNotificationActivity.class);
                        Bundle bundle5 = new Bundle(); //该类用作携带数据
                        bundle5.putString("projectID",projectID);
                        bundle5.putString("pk",id);
                        bundle5.putString("status","整改未回复");
                        intent5.putExtras(bundle5);
                        activity.startActivity(intent5);
                        break;
                    case "不良行为":

                        activity.finish();
                        //不良行为管理
                        Intent intent9 = new Intent(activity, BadBehaviorActivity.class);
                        Bundle bundl9 = new Bundle(); //该类用作携带数据
                        bundl9.putString("projectID",projectID);
                        bundl9.putString("pk",id);
                        intent9.putExtras(bundl9);
                        activity.startActivity(intent9);
                        break;

                    case "停工记录":

                        activity.finish();
                        //停工管理
                        Intent intent6 = new Intent(activity, StopWorkActivity.class);
                        Bundle bundl6 = new Bundle(); //该类用作携带数据
                        bundl6.putString("projectID",projectID);
                        bundl6.putString("pk",id);
                        bundl6.putBoolean("isstop",true);
                        intent6.putExtras(bundl6);
                        activity.startActivity(intent6);
                        break;
                    case "复工记录":

                        activity.finish();
                        //复工管理
                        Intent intent7 = new Intent(activity, ReworkActivity.class);
                        Bundle bundl7 = new Bundle(); //该类用作携带数据
                        bundl7.putString("projectID",projectID);
                        bundl7.putString("pk",id);
                        intent7.putExtras(bundl7);
                        activity.startActivity(intent7);
                        break;
                    case "监督检测":

                        activity.finish();
                        //复工管理
                        Intent intent = new Intent(activity, InspectionActivity.class);
                        intent.putExtra("projectID", projectID);
                        intent.putExtra("pk", id);
                        activity.startActivity(intent);
                        break;
                    case "监督验收":

                        activity.finish();
                        //复工管理
                        Intent intent8 = new Intent(activity, AcceptanceActivity.class);
                        intent8.putExtra("projectID", projectID);
                        intent8.putExtra("pk", id);
                        activity.startActivity(intent8);
                        break;
                    case "日常巡查":

                        activity.finish();
                        //复工管理
                        Intent intent10 = new Intent(activity, RichangActivity.class);
                        intent10.putExtra("projectID", projectID);
                        intent10.putExtra("pk", id);
                        activity.startActivity(intent10);
                        break;*/

                }

                dismiss();
                break;
            case R.id.dele:

                switch (whichnoe) {
                    case "形象进度":

                        delet("progress/deleteProgress");
                        break;
                    case "质量监督":
                    case "安全监督":

                        delet("inspection/deleteInspection");
                        break;
                    case "整改未回复":

                        delet("reform/deleteReform");
                        break;
                    case "不良行为":

                        delet("misconduct/deleteMisconduct");
                        break;
                    case "停工记录":

                        delet("stop/deleteStop");
                        break;
                    case "复工记录":

                        delet("stop/deleteRestart");
                        break;
                    case "监督检测":

                        delet("monitorCheck/deleteCheck.action");
                        break;
                    case "监督验收":

                        delet("monitorAccept/deleteAccept.action");
                        break;
                    case "日常巡查":

                        delet("inspection/deleteInspection");
                        break;
                }

                break;

            case R.id.cancer:
                dismiss();
                break;
        }
    }


    private void delet(String interfacename) {

        GetDeleteReq req = new GetDeleteReq();

        req.id = id;
        req.pk= id;

        AndroidApplication.getInstance().doPostAsyncfile(RetrofitFactory.BASE_URL + interfacename, req, new okhttpcall());
    }

    class okhttpcall implements Callback {


        @Override
        public void onFailure(Call call, IOException e) {

            mHandler.sendEmptyMessage(RetrofitFactory.MSG_FAIL);
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            String body = response.body().string();
            Log.d("CD", "DDDDDDDDDD=" + body);
            stateSuccessRsp = JSON.parseObject(body, new TypeReference<BaseBeanRsp<StateSuccessRsp>>() {
            });
            mHandler.sendEmptyMessage(stateSuccessRsp.verification ? RetrofitFactory.MSG_SUCESS : RetrofitFactory.MSG_FAIL);

        }
    }

    protected android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case RetrofitFactory.MSG_SUCESS:

                    Toast.makeText(activity, "删除成功!", Toast.LENGTH_SHORT).show();

                    switch (whichnoe) {
                        case "形象进度":
                            Intent intent = new Intent();
                            intent.setAction(ScheduleRecordFragment.DATAREFRESH);
                            activity.sendBroadcast(intent);
                            break;
                      /*  case "质量监督":
                        case "安全监督":
                            Intent intent2 = new Intent();
                            intent2.setAction(HistoryFragment.DATAREFRESH);
                            activity.sendBroadcast(intent2);
                            break;
                        case "整改未回复":
                            Intent intent3 = new Intent();
                            intent3.setAction(MRectificationNotNotificationFragment.DATAREFRESH);
                            activity.sendBroadcast(intent3);
                            break;
                        case "不良行为":
                            Intent intent4 = new Intent();
                            intent4.setAction(BadBehaviorRecordFragment.DATAREFERSH);
                            activity.sendBroadcast(intent4);
                            break;
                        case "停工记录":
                        case "复工记录":
                            Intent intent5 = new Intent();
                            intent5.setAction(StopWorkHistoryFragment.DATAREFRESH);
                            activity.sendBroadcast(intent5);
                            break;
                        case "监督检测":
                            Intent intent6 = new Intent();
                            intent6.setAction(InspectionHistoryFragment.DATAREFRESH);
                            activity.sendBroadcast(intent6);
                            break;
                        case "监督验收":
                            Intent intent7 = new Intent();
                            intent7.setAction(AcceptanceHistoryFragment.DATAREFRESH);
                            activity.sendBroadcast(intent7);
                            break;
                        case "日常巡查":
                            Intent intent8 = new Intent();
                            intent8.setAction(RichangHistoryFragment.DATAREFRESH);
                            activity.sendBroadcast(intent8);
                            break;*/
                    }

                    dismiss();
                    break;
                case RetrofitFactory.MSG_FAIL:
                    Toast.makeText(activity, "删除失败!", Toast.LENGTH_SHORT).show();
                    dismiss();
                    break;
                default:
                    break;

            }
            super.handleMessage(msg);
        }
    };

}
