package com.threehmis.bjaj.module.more;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.adapter.ScorehistoryAdapter;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.GetScoreHistoryReq;
import com.threehmis.bjaj.api.bean.respon.GetCheckScoreHistoryRsp;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

//评分记录
public class ScoreHistoryFragment extends Fragment {

    public static String DATAREFRESH = "datarefresh";
    XRecyclerView recyclerview;
    private String inspectionId;
    // 加载第几页
    private int pageIndex = 1,pagesize=20;
    BaseBeanRsp<GetCheckScoreHistoryRsp> getCheckScoreRsp;
    private ScorehistoryAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_action_history, container, false);

        recyclerview = (XRecyclerView) view.findViewById(R.id.recyclerview);

        Bundle bundle = getActivity().getIntent().getExtras();
        inspectionId = bundle.getString("pk");

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerview.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false));

        recyclerview.setRefreshProgressStyle(ProgressStyle.BallScaleRippleMultiple);

        recyclerview.setLaodingMoreProgressStyle(ProgressStyle.Pacman);

        recyclerview.setArrowImageView(R.drawable.iconfont_downgrey);
        recyclerview.setLoadingListener(new XRecyclerViewLoadingListener());

        mAdapter = new ScorehistoryAdapter();

        recyclerview.setAdapter(mAdapter);

        getData();
    }

    private void getData() {

        GetScoreHistoryReq req = new GetScoreHistoryReq();

        req.inspectionPK=inspectionId;
        req.pageno=pageIndex+"";
        req.pagesize=pagesize+"";

        AndroidApplication.getInstance().doPostAsyncfile(RetrofitFactory.BASE_URL+"score/scoreList",req,new okhttpcall());


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

            getCheckScoreRsp = JSON.parseObject(body, new TypeReference<BaseBeanRsp<GetCheckScoreHistoryRsp>>() {});


            mHandler.sendEmptyMessage(getCheckScoreRsp.verification ? RetrofitFactory.MSG_SUCESS : RetrofitFactory.MSG_FAIL);

        }
    }

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case RetrofitFactory.MSG_SUCESS:

                    if (pageIndex == 1) recyclerview.refreshComplete();
                    if (pageIndex != 1) recyclerview.loadMoreComplete();
                    mAdapter.notifyData(getCheckScoreRsp.projectList, pageIndex);

                    break;
                case RetrofitFactory.MSG_FAIL:

                    if (pageIndex == 1) recyclerview.refreshComplete();
                    if (pageIndex != 1) recyclerview.loadMoreComplete();
                    Toast.makeText(getContext(), "获取数据失败!", Toast.LENGTH_SHORT).show();
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
        IntentFilter filter =new IntentFilter();
        filter.addAction(DATAREFRESH);
        getActivity().registerReceiver(receiver,filter);
    }

    BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent!=null)
                getData();
        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();

        getActivity().unregisterReceiver(receiver);
        mHandler.removeCallbacksAndMessages(null);
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
