package com.threehmis.bjaj.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.adapter.UnqualifiedDialogListAdapter;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.GetUnqualifiedReq;
import com.threehmis.bjaj.api.bean.respon.StateSuccessRsp;
import com.threehmis.bjaj.utils.ScreenUtil;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author CD
 */
public class UnqualifiedDialog extends Dialog implements
        View.OnClickListener {

    Activity context;

    private GettypeListener listener;

    private XRecyclerView recyclerview;
    // 加载第几页
    private int pageIndex = 1,pagesize=20;
    private BaseBeanRsp<StateSuccessRsp> stateSuccessRsp;
    private UnqualifiedDialogListAdapter adapter;

    private String projectID;

    public UnqualifiedDialog(Activity context) {
        this(context, R.style.shareDialog);
        this.context = context;
    }

    public UnqualifiedDialog(Context context, int theme) {
        super(context, theme);
    }

    public UnqualifiedDialog(Activity context, String projectID , GettypeListener listener) {
        this(context, R.style.shareDialog);

        this.context = context;
        this.projectID = projectID;
        this.listener = listener;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_questionclass);
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.alpha = 1.0f;
        wl.gravity = Gravity.CENTER;
        wl.width = ScreenUtil.getScreenWidth(context);
        wl.height = ScreenUtil.getScreenHeight(context);
        window.setAttributes(wl);
        setCancelable(true); //点击返回键取消
        setCanceledOnTouchOutside(true); //点击外围空间取消

        recyclerview = (XRecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false));

        recyclerview.setRefreshProgressStyle(ProgressStyle.BallScaleRippleMultiple);

        recyclerview.setLaodingMoreProgressStyle(ProgressStyle.Pacman);

        recyclerview.setArrowImageView(R.drawable.iconfont_downgrey);
        recyclerview.setLoadingListener(new XRecyclerViewLoadingListener());


        adapter = new UnqualifiedDialogListAdapter();
        recyclerview.setAdapter(adapter);

        adapter.setOnItemClickListener(new UnqualifiedDialogListAdapter.OnRecyclerViewItemClickListener(){

            @Override
            public void onItemClick(String date1) {
                listener.Getdate(date1);
                dismiss();
                mHandler.removeCallbacksAndMessages(null);
            }

        });

        TextView title = (TextView) findViewById(R.id.title);
        title.setText("检测项目列表");
        findViewById(R.id.titleback).setOnClickListener(this);

        getData();
    }

    @Override
    public void onClick(View v) {
        cancel();
        switch (v.getId()) {
            case R.id.titleback:
                dismiss();
                mHandler.removeCallbacksAndMessages(null);
                break;
        }
    }


    //接口传参
    public interface GettypeListener {
        /**
         * 回调函数，用于在Dialog的监听事件触发后刷新Activity的UI显示
         */
        void Getdate(String str1);


    }


    //获取数据
    private void getData() {

        GetUnqualifiedReq req = new GetUnqualifiedReq();
        req.projectId = projectID;
        AndroidApplication.getInstance().doPostAsyncfile(RetrofitFactory.BASE_URL + "projectTj/findAllItemName", req, new okhttpcall());


    }

    class okhttpcall implements Callback {


        @Override
        public void onFailure(Call call, IOException e) {

            mHandler.sendEmptyMessage(RetrofitFactory.MSG_FAIL);
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            String body = response.body().string();

            Log.d("CD", "DDDDDDialog=" + body);

            stateSuccessRsp = JSON.parseObject(body, new TypeReference<BaseBeanRsp<StateSuccessRsp>>() {});


            mHandler.sendEmptyMessage(stateSuccessRsp.verification ? RetrofitFactory.MSG_SUCESS : RetrofitFactory.MSG_FAIL);

        }
    }

    protected android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case RetrofitFactory.MSG_SUCESS:

                    if (pageIndex == 1) recyclerview.refreshComplete();
                    if (pageIndex != 1) recyclerview.loadMoreComplete();
                    adapter.notifyData(stateSuccessRsp.projectList, pageIndex);

                    break;
                case RetrofitFactory.MSG_FAIL:

                    Toast.makeText(context, "获取数据失败!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };


    class XRecyclerViewLoadingListener implements XRecyclerView.LoadingListener {

        @Override
        public void onRefresh() {
            pageIndex = 1;
            getData();
        }

        @Override
        public void onLoadMore() {
            if (adapter.getItemCount() % 20 == 0) {
                pageIndex = ++pageIndex;
                getData();
            } else {
                recyclerview.noMoreLoading();
            }
        }
    }


}
