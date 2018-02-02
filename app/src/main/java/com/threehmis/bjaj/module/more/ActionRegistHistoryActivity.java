package com.threehmis.bjaj.module.more;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.TypeReference;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.adapter.ActionHistoryAdapter2;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.GetActionHistoryReq;
import com.threehmis.bjaj.api.bean.respon.GetActionHistoryRsp;
import com.threehmis.bjaj.utils.OkhttpCallbackUtils;


import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActionRegistHistoryActivity extends AppCompatActivity {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recyclerview)
    XRecyclerView recyclerview;

    public static String DATAREFRESH = "DATAREFRESH"; //刷新数据

    // 加载第几页
    private int pageIndex = 1, pagesize = 20;
    private ActionHistoryAdapter2 mAdapter;
    private String inspectionId;
    private boolean isentity; //实体

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_regist_history);
        ButterKnife.bind(this);

        title.setText("登记记录");
        Bundle bundle = getIntent().getExtras();
        inspectionId = bundle.getString("pk");
        isentity = bundle.getBoolean("isentity");

        recyclerview.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false));

        recyclerview.setRefreshProgressStyle(ProgressStyle.BallScaleRippleMultiple);

        recyclerview.setLaodingMoreProgressStyle(ProgressStyle.Pacman);

        recyclerview.setArrowImageView(R.drawable.iconfont_downgrey);
        recyclerview.setLoadingListener(new XRecyclerViewLoadingListener());

        mAdapter = new ActionHistoryAdapter2();

        recyclerview.setAdapter(mAdapter);

        getData();
    }

    private void getData() {

        GetActionHistoryReq req = new GetActionHistoryReq();

        req.inspectionPK = inspectionId;
        req.checkType = isentity ? "实体抽查" : "行为管理";
        req.pageno = pageIndex + "";
        req.pagesize = pagesize + "";


        AndroidApplication.getInstance().doPostAsyncfilexx(RetrofitFactory.BASE_URL + "supervise/checkList", req,
                new OkhttpCallbackUtils<GetActionHistoryRsp>(new TypeReference<BaseBeanRsp<GetActionHistoryRsp>>() {
                }) {

                    @Override
                    public void onFailure(IOException e) {
                        super.onFailure(e);
                        Toast.makeText(getApplicationContext(), "网络连接失败，请稍后重试！", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(BaseBeanRsp<GetActionHistoryRsp> t) {
                        super.onResponse(t);

                        if (pageIndex == 1) recyclerview.refreshComplete();
                        if (pageIndex != 1) recyclerview.loadMoreComplete();
                        if (t.verification) {

                            mAdapter.notifyData(t.projectList, pageIndex);
                        } else {
                            Toast.makeText(getApplicationContext(), t.result, Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }


    @OnClick(R.id.titleback)
    void titleback() {
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(DATAREFRESH);
        registerReceiver(receiver, filter);


    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent != null)
                getData();
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }


    class XRecyclerViewLoadingListener implements XRecyclerView.LoadingListener {

        @Override
        public void onRefresh() {
            pageIndex = 1;
            getData();
        }

        @Override
        public void onLoadMore() {
            if (mAdapter.getItemCount() % 20 == 0) {
                pageIndex = ++pageIndex;
                getData();
            } else {
                recyclerview.noMoreLoading();
            }
        }
    }


}
