package com.threehmis.bjaj.module.home.fragment.map.griddetail.projectinfo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.GetTongJiReq;
import com.threehmis.bjaj.api.bean.respon.GetTongJiRsp;
import com.threehmis.bjaj.widget.chartview.HistogramFourView;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;


public class TongJiFragment extends Fragment {


    private int[] progress;
    private TextView name, status;
    private String projectID;
    BaseBeanRsp<GetTongJiRsp> getTongJiRsp;
    private HistogramFourView blue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tongji, container, false);

        Bundle bundle = getActivity().getIntent().getExtras();
        projectID = bundle.getString("projectID");

        // 蓝色柱状图
        blue = (HistogramFourView) view.findViewById(R.id.blue);
        name = (TextView) view.findViewById(R.id.name);
        status = (TextView) view.findViewById(R.id.status);

        TextView titleback = (TextView) view.findViewById(R.id.titleback);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText("执法统计");
        titleback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        getDate();
    }

    private void getDate() {

        GetTongJiReq req = new GetTongJiReq();
        req.projectId = projectID;
        AndroidApplication.getInstance().doPostAsyncfile(RetrofitFactory.BASE_URL + "projectTj/projectByTj", req, new OkHttpcallback());

    }

    class OkHttpcallback implements Callback {

        @Override
        public void onFailure(Call call, IOException e) {

            mHandler.sendEmptyMessage(RetrofitFactory.MSG_FAIL);
        }

        @Override
        public void onResponse(Call call, okhttp3.Response response) throws IOException {

            String body = response.body().string();

            // 获取数据源
            getTongJiRsp = JSON.parseObject(body, new TypeReference<BaseBeanRsp<GetTongJiRsp>>() {
            });

            mHandler.sendEmptyMessage(getTongJiRsp.verification ? RetrofitFactory.MSG_SUCESS : RetrofitFactory.MSG_FAIL);

        }
    }

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case RetrofitFactory.MSG_SUCESS:

                    SpannableStringBuilder ss = new SpannableStringBuilder("工程名称 : " + getTongJiRsp.projectList.get(0).projectName);
                    ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getActivity(), R.color.green)), 7, 7 + getTongJiRsp.projectList.get(0).projectName.length(),
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    name.setText(ss);

                    SpannableStringBuilder ss2 = new SpannableStringBuilder("状态 : " + getTongJiRsp.projectList.get(0).projectStatus);
                    ss2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getActivity(), R.color.green)), 5, 5 + getTongJiRsp.projectList.get(0).projectStatus.length(),
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    status.setText(ss2);

                    if (getTongJiRsp.projectList.get(0).max != 0) {
                        // 9 5 5 0
                        progress = new int[]{getTongJiRsp.projectList.get(0).checkValue, getTongJiRsp.projectList.get(0).refromValue, getTongJiRsp.projectList.get(0).reformStatusValue, getTongJiRsp.projectList.get(0).misconductValue};
                        // 初始化蓝色柱状图
                        blue.start(2, getTongJiRsp.projectList.get(0).max, progress);
                    }

                    break;
                case RetrofitFactory.MSG_FAIL:

                    Toast.makeText(getActivity(), "获取数据失败!", Toast.LENGTH_SHORT).show();
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


}
