/*
package com.threehmis.bjaj.module.home.fragment.map.griddetail.projectinfo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.adapter.ProjectInfoListAdapter;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.GetProjectInfoReq;
import com.threehmis.bjaj.api.bean.respon.GetProjectInfoBean;
import com.threehmis.bjaj.api.bean.respon.GetProjectInfoRsp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;


public class GaiKuangFragment2 extends Fragment {
    private TextView title, titleback;
    private ListView list1;
    private ProjectInfoListAdapter adapter;
    private String projectID;
    private GetProjectInfoBean getProjectInfoRsp;
    //header
    LinearLayout item1, item2, item3, item4, item5, item6, item7,item8,item9,item10,item11,item12,item13,item14;
    String[] itemStr = { "工程名称", "安全监督编号", "地址", "工程类型", "层数/幢数", "建筑物总高(m)", "建筑面积","工程造价","建设企业","施工企业","监理企业","建设单位项目负责人"
    ,"施工总承包单位项目负责人","监理单位总监理工程师"};
    TextView[] itemName, itemLabel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gaikuang, container, false);
        Bundle bundle = getActivity().getIntent().getExtras();
        projectID = bundle.getString("projectID");
        list1 = (ListView) view.findViewById(R.id.list1);
        header();
        adapter = new ProjectInfoListAdapter();
        list1.setAdapter(adapter);
        title = (TextView) view.findViewById(R.id.title);
        title.setText("工程信息");
        titleback = (TextView) view.findViewById(R.id.titleback);
        titleback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        getInteract();
        return view;
    }

    private void header() {
        //header
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_project_infohead, null);
        item1 = (LinearLayout) headerView.findViewById(R.id.item1);
        item2 = (LinearLayout) headerView.findViewById(R.id.item2);
        item3 = (LinearLayout) headerView.findViewById(R.id.item3);
        item4 = (LinearLayout) headerView.findViewById(R.id.item4);
        item5 = (LinearLayout) headerView.findViewById(R.id.item5);
        item6 = (LinearLayout) headerView.findViewById(R.id.item6);
        item7 = (LinearLayout) headerView.findViewById(R.id.item7);
        item8 = (LinearLayout) headerView.findViewById(R.id.item8);
        item9 = (LinearLayout) headerView.findViewById(R.id.item9);
        item10 = (LinearLayout) headerView.findViewById(R.id.item10);
        item11= (LinearLayout) headerView.findViewById(R.id.item11);
        item12 = (LinearLayout) headerView.findViewById(R.id.item12);
        item13 = (LinearLayout) headerView.findViewById(R.id.item13);
        item14 = (LinearLayout) headerView.findViewById(R.id.item14);

        LinearLayout[] item = { item1, item2, item3, item4, item5, item6, item7 ,item8,item9,item10,item11,item12,item13,item14};
        itemName = new TextView[item.length];
        itemLabel = new TextView[item.length];
        for (int i = 0; i < item.length; i++) {
            itemName[i] = (TextView) item[i].findViewById(R.id.itemName);
            itemLabel[i] = (TextView) item[i].findViewById(R.id.itemLabel);
            itemName[i].setText(itemStr[i]);
        }
        list1.addHeaderView(headerView);

    }

    private void getInteract() {
        GetProjectInfoReq req = new GetProjectInfoReq();
        req.projectId = projectID;
        AndroidApplication.getInstance().doPostAsyncfile(RetrofitFactory.BASE_URL+"project/getOneProjectInfo", req, new OkHttpcallback());
    }

    class OkHttpcallback implements Callback {
        @Override
        public void onFailure(Call call, IOException e) {
            mHandler.sendEmptyMessage(RetrofitFactory.MSG_FAIL);
        }

        @Override
        public void onResponse(Call call, okhttp3.Response response) throws IOException {
            String body = response.body().string();
            getProjectInfoRsp = new Gson().fromJson(body,GetProjectInfoBean.class);

            mHandler.sendEmptyMessage(getProjectInfoRsp.isVerification() ? RetrofitFactory.MSG_SUCESS : RetrofitFactory.MSG_FAIL);
        }
    }

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case RetrofitFactory.MSG_SUCESS:
                    itemLabel[0].setText(getProjectInfoRsp.getProjectInfo().getProjectName());
                    itemLabel[1].setText(getProjectInfoRsp.getProjectInfo().getProjectNum());
                    itemLabel[2].setText(getProjectInfoRsp.getProjectInfo().getAddress());
                    itemLabel[3].setText(getProjectInfoRsp.getProjectInfo().getProjectType());
                    itemLabel[4].setText(getProjectInfoRsp.getProjectInfo().getFloorUp()+"");
                    itemLabel[5].setText(getProjectInfoRsp.getProjectInfo().getHigh()+"");
                    itemLabel[6].setText(getProjectInfoRsp.getProjectInfo().getArea()+"m²");
                    itemLabel[7].setText(getProjectInfoRsp.getProjectInfo().getProjectCost()+"万元");

                    if(!TextUtils.isEmpty(getProjectInfoRsp.getProjectInfo().getJsdw()))
                    itemLabel[8].setText(getProjectInfoRsp.getProjectInfo().getJsdw());

                    if(!TextUtils.isEmpty(getProjectInfoRsp.getProjectInfo().getSgdw()))
                    itemLabel[9].setText(getProjectInfoRsp.getProjectInfo().getSgdw());

                    if(!TextUtils.isEmpty(getProjectInfoRsp.getProjectInfo().getJldw()))
                        itemLabel[10].setText(getProjectInfoRsp.getProjectInfo().getJldw());

                    if(!TextUtils.isEmpty(getProjectInfoRsp.getProjectInfo().getJsdwMan()))
                    itemLabel[11].setText(getProjectInfoRsp.getProjectInfo().getJsdwMan());

                    if(!TextUtils.isEmpty(getProjectInfoRsp.getProjectInfo().getSgzcbMan()))
                        itemLabel[12].setText(getProjectInfoRsp.getProjectInfo().getSgzcbMan());

                    if(!TextUtils.isEmpty(getProjectInfoRsp.getProjectInfo().getJldwMan()))
                        itemLabel[13].setText(getProjectInfoRsp.getProjectInfo().getJldwMan());




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
*/
