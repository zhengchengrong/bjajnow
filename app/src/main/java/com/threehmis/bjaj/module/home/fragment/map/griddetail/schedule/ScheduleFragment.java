package com.threehmis.bjaj.module.home.fragment.map.griddetail.schedule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.MGetFormScheduleReq;
import com.threehmis.bjaj.api.bean.request.MGetFormScheduleSaveReq;
import com.threehmis.bjaj.api.bean.respon.GetFormScheduleRsp;
import com.threehmis.bjaj.api.bean.respon.StateSuccessRsp;
import com.threehmis.bjaj.dialog.AddOrEditDialog;
import com.threehmis.bjaj.dialog.DataDialog;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * 进度登记
 */
public class ScheduleFragment extends Fragment implements View.OnClickListener {

    private String projectId,id;

    private BaseBeanRsp<GetFormScheduleRsp> getFormScheduleRsp;

    private BaseBeanRsp<StateSuccessRsp> stateSuccessRsp;


    private String timestr;
    private TextView save, arrow,itemName,itemLabel;//保存

    private boolean issave = false; //回调判断 是true为保存回调
    private boolean issubmit = false; //回调判断 是true为点击提交

    private TextView formScheduleSubmit;//提交
    private EditText suggest1,jindu,part;
    private LinearLayout layout_all;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.m_fragment_schedule_register, container, false);

        Bundle bundle = getActivity().getIntent().getExtras();
        projectId = bundle.getString("projectID");
        id = bundle.getString("id")!=null?bundle.getString("id"):"";

        save = (TextView) view.findViewById(R.id.save);
        layout_all = (LinearLayout) view.findViewById(R.id.layout_all);
        formScheduleSubmit = (TextView) view.findViewById(R.id.tv_formScheduleSubmit);
        suggest1 = (EditText) view.findViewById(R.id.suggest);

        jindu= (EditText) view.findViewById(R.id.jindu);
        part= (EditText) view.findViewById(R.id.part);

        itemName =(TextView) view.findViewById(R.id.itemName);
        itemLabel =(TextView) view.findViewById(R.id.itemLabel);

        itemName.setText("更新日期");

        arrow = (TextView) view.findViewById(R.id.arrow);
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.icon_data);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        arrow.setCompoundDrawables(null, null, drawable, null);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DataDialog(getActivity(), new DataDialog.GetdatapickerLisenter() {

                    @Override
                    public void Getdata(String data) {
                        itemLabel.setText(data);
                    }
                }).show();
            }
        });


        //获取系统时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        timestr = formatter.format(curDate);
        itemLabel.setText(timestr);

        save.setOnClickListener(this);
        formScheduleSubmit.setOnClickListener(this);
        getdate();
        return view;
    }

    private void getdate() {  //取接口数据

        MGetFormScheduleReq req = new MGetFormScheduleReq();
        req.projectId = projectId;
        req.id = id;
        issave = false;
        AndroidApplication.getInstance().doPostAsyncfile(RetrofitFactory.BASE_URL + "progress/getProgressByProId", req, new okhttpcall());
    }

    private void getSaveDate(String isSubmit) {

        if (TextUtils.isEmpty(jindu.getText().toString())|| TextUtils.isEmpty(part.getText().toString())){

            Toast.makeText(getActivity(),"录入信息不完整！", Toast.LENGTH_SHORT).show();
            return;
        }

        MGetFormScheduleSaveReq req = new MGetFormScheduleSaveReq();
        req.projectID = projectId;//工程ID
        req.id = id;//工程ID
        req.isSubmit = isSubmit;//"1"提交其他保存
        req.projectXXJD = jindu.getText().toString();
        //形象进度
        req.projectPart = part.getText().toString();
        //工程部位
        req.remark = suggest1.getText().toString();//备注
        req.updatedate = itemLabel.getText().toString();//更新时间
        req.userId=AndroidApplication.getInstance().getuserId();
        issave = true;
        AndroidApplication.getInstance().doPostAsyncfile(RetrofitFactory.BASE_URL + "progress/saveProgress", req, new okhttpcall());
    }

    //在这里已经拿到了形象进度 和 工程部位 的数据
    class okhttpcall implements Callback {

        @Override
        public void onFailure(Call call, IOException e) {

            mHandler.sendEmptyMessage(RetrofitFactory.MSG_FAIL);
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            String body = response.body().string();

            Log.d("CD", "shane=" + body);

            if (issave) {
                //保存返回的数据
                stateSuccessRsp = JSON.parseObject(body, new TypeReference<BaseBeanRsp<StateSuccessRsp>>() {
                });
                mHandler.sendEmptyMessage(stateSuccessRsp.verification ? RetrofitFactory.MSG_SUCESS : RetrofitFactory.MSG_FAIL);
            } else {
                getFormScheduleRsp = JSON.parseObject(body, new TypeReference<BaseBeanRsp<GetFormScheduleRsp>>() {
                });
                mHandler.sendEmptyMessage(getFormScheduleRsp.verification ? RetrofitFactory.MSG_SUCESS : RetrofitFactory.MSG_FAIL);
            }
        }
    }

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case RetrofitFactory.MSG_SUCESS:

                    if (issave) {
                        Toast.makeText(getContext(), "保存或提交成功!", Toast.LENGTH_SHORT).show();
                        //layout_all.setVisibility(View.GONE);
                        Intent intent = new Intent();
                        intent.setAction(ScheduleRecordFragment.DATAREFRESH);
                        getActivity().sendBroadcast(intent);
                        if (!issubmit) {
                            new AddOrEditDialog(getActivity(), new AddOrEditLisenter(),false).show();
                        }

                        return;
                    }
                    jindu.setText(getFormScheduleRsp.projectList.get(0).projectXXJD);
                    jindu.setSelection(getFormScheduleRsp.projectList.get(0).projectXXJD!=null?getFormScheduleRsp.projectList.get(0).projectXXJD.length():0);
                    part.setText(getFormScheduleRsp.projectList.get(0).projectPart);
                    suggest1.setText(getFormScheduleRsp.projectList.get(0).remark);
                    itemLabel.setText(getFormScheduleRsp.projectList.get(0).updatedate!=null?
                            getFormScheduleRsp.projectList.get(0).updatedate:timestr);

                    break;
                case RetrofitFactory.MSG_FAIL:

                    Toast.makeText(getContext(), "操作失败!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ScheduleRecordFragment.DATAREFRESH);
        getActivity().registerReceiver(receiver,filter);

    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent!=null) {
                id = "";
                getdate();
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();

        getActivity().unregisterReceiver(receiver);
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.save: //点击保存
                issubmit=false;
                getSaveDate("0");

                break;

            case R.id.tv_formScheduleSubmit: //点击提交
                issubmit=true;
                getSaveDate("1");
                break;
        }
    }

    //dialog 保存后的新增和编辑选择
    class  AddOrEditLisenter implements AddOrEditDialog.Lisenter{

        @Override
        public void Getstats(int data) {
                id = data==0?stateSuccessRsp.projectList.get(0).id:"";
                getdate();
        }

    }

}
