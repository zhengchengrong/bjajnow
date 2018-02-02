package com.threehmis.bjaj.module.home.fragment.notice;

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

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.adapter.WilldoAdapter;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.request.GetHistoryReq;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class WilldoFragment extends Fragment {

    private XRecyclerView recyclerview;
    private int pageIndex = 1,pagesize=20;
    private WilldoAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_willdo, container, false);

        recyclerview = (XRecyclerView) view.findViewById(R.id.recyclerview);


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

        mAdapter = new WilldoAdapter();
        recyclerview.setAdapter(mAdapter);

//        getData();

    }

    private void getData() {

        GetHistoryReq req = new GetHistoryReq();

//        req.projectId=projectId;
//        req.inspectionType=isquality?"0":"1"; //0.质量 1.安全
        req.pageno=pageIndex+"";
        req.pagesize=pagesize+"";

        AndroidApplication.getInstance().doPostAsyncfile(RetrofitFactory.BASE_URL+"inspection/inspectionList",req,new okhttpcall());


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

//            getHistoryRsp = JSON.parseObject(body, new TypeReference<BaseBeanRsp<GetHistoryRsp>>() {});


//            mHandler.sendEmptyMessage(getHistoryRsp.verification ? RetrofitFactory.MSG_SUCESS : RetrofitFactory.MSG_FAIL);

        }
    }

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case RetrofitFactory.MSG_SUCESS:

//                    if (pageIndex == 1) recyclerview.refreshComplete();
//                    if (pageIndex != 1) recyclerview.loadMoreComplete();
//                    mAdapter.notifyData(getHistoryRsp.projectList, pageIndex);

                    break;
                case RetrofitFactory.MSG_FAIL:

                    Toast.makeText(getContext(), "获取数据失败!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();

        mHandler.removeCallbacksAndMessages(null);
    }


    class XRecyclerViewLoadingListener implements XRecyclerView.LoadingListener {

        @Override
        public void onRefresh() {

            recyclerview.refreshComplete();
        }

        @Override
        public void onLoadMore() {
            recyclerview.loadMoreComplete();
        }
    }


}
