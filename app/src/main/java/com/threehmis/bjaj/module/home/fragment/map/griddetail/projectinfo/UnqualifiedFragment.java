package com.threehmis.bjaj.module.home.fragment.map.griddetail.projectinfo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.GetUnqualifiedReq;
import com.threehmis.bjaj.api.bean.respon.GetUnqualifiedRsp;
import com.threehmis.bjaj.dialog.UnqualifiedDialog;
import com.threehmis.bjaj.widget.chartview.HistogramFourRedView;
import com.threehmis.bjaj.widget.chartview.LineChartView;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;

//不合格情况
public class UnqualifiedFragment extends Fragment implements View.OnClickListener{

    private int[] progress,progress2;
    private ImageView change;
    boolean isclick=false;
    private LinearLayout layouline;
    private TextView stye;
    private String projectID,itemName="";
    BaseBeanRsp<GetUnqualifiedRsp> getUnqualifiedRsp;
    private HistogramFourRedView blue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_unqualified, container, false);
        Bundle bundle = getActivity().getIntent().getExtras();
        projectID = bundle.getString("projectID");

        layouline = (LinearLayout) view.findViewById(R.id.layou_line);
        LineChartView line = (LineChartView) view.findViewById(R.id.line);
        LinearLayout chart = (LinearLayout) view.findViewById(R.id.chart);
        stye = (TextView) view.findViewById(R.id.stye);
        blue = (HistogramFourRedView) view.findViewById(R.id.blue);


        change = (ImageView) view.findViewById(R.id.change);
        change.setOnClickListener(this);
        stye.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getData();

    }

        private void getData() {

        GetUnqualifiedReq req = new GetUnqualifiedReq();
        req.projectId = projectID;
        req.itemName = itemName;

        AndroidApplication.getInstance().doPostAsyncfile(RetrofitFactory.BASE_URL + "projectTj/etlByTj", req, new OkHttpcallback());
    }

    class OkHttpcallback implements Callback {

        @Override
        public void onFailure(Call call, IOException e) {

            mHandler.sendEmptyMessage(RetrofitFactory.MSG_FAIL);
        }

        @Override
        public void onResponse(Call call, okhttp3.Response response) throws IOException {

            String body = response.body().string();
            Log.d("CD", "图表返回数据=" + body);

            getUnqualifiedRsp = JSON.parseObject(body, new TypeReference<BaseBeanRsp<GetUnqualifiedRsp>>() {
            });

            mHandler.sendEmptyMessage(getUnqualifiedRsp.verification ? RetrofitFactory.MSG_SUCESS : RetrofitFactory.MSG_FAIL);

        }
    }

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case RetrofitFactory.MSG_SUCESS:

                    if (getUnqualifiedRsp.projectList.get(0).max!=0) {
                        progress = new int[]{getUnqualifiedRsp.projectList.get(0).oneSucc, getUnqualifiedRsp.projectList.get(0).twoSucc,
                                getUnqualifiedRsp.projectList.get(0).threeSucc, getUnqualifiedRsp.projectList.get(0).fourSucc};// 4
                        progress2 = new int[]{getUnqualifiedRsp.projectList.get(0).oneError, getUnqualifiedRsp.projectList.get(0).twoError,
                                getUnqualifiedRsp.projectList.get(0).threeError, getUnqualifiedRsp.projectList.get(0).fourError};// 4
                        blue.start(getUnqualifiedRsp.projectList.get(0).max, progress2, progress);
                        if (Looper.getMainLooper() == Looper.myLooper()) {
                            blue.invalidate();
                        } else {
                            blue.postInvalidate();
                        }
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


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.change:

                change.setImageResource(isclick?R.drawable.s01:R.drawable.s02);
                isclick=!isclick;
                layouline.setVisibility(isclick? View.VISIBLE: View.GONE);
                break;
            case R.id.stye:

                UnqualifiedDialog dialog = new UnqualifiedDialog(getActivity(), projectID, new lisenter());
                dialog.show();
                break;

        }

    }

    //监听获取点击后返回的不良行为内容
    class lisenter implements UnqualifiedDialog.GettypeListener {


        @Override
        public void Getdate(String str1) {

            itemName = str1.equals("检测总数")?"":str1;
            stye.setText(str1);
            getData();

        }
    }


}
