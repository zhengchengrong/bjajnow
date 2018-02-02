package com.threehmis.bjaj.module.home.fragment.map.griddetail.schedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.TypeReference;
import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.MGetFormScheduleReq;
import com.threehmis.bjaj.api.bean.respon.GetFormScheduleRsp;
import com.threehmis.bjaj.utils.OkhttpCallbackUtils;


import java.io.IOException;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScheduleDetailActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.remark)
    TextView remark;

    @BindViews({R.id.item1, R.id.item2, R.id.item3})
    LinearLayout items[];

    String[] itemStr = {"形象进度", "具体施工部位", "更新日期"};
    TextView[] itemName, itemLabel;

    String projectId, id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_detail);
        ButterKnife.bind(this);

        title.setText("形象进度");
        Bundle bundle = getIntent().getExtras();
        projectId = bundle.getString("projectID");
        id = bundle.getString("id") != null ? bundle.getString("id") : "";


        itemName = new TextView[itemStr.length];
        itemLabel = new TextView[itemStr.length];
        for (int i = 0; i < itemStr.length; i++) {
            itemName[i] = (TextView) items[i].findViewById(R.id.itemName);
            itemLabel[i] = (TextView) items[i].findViewById(R.id.itemLabel);
            itemName[i].setText(itemStr[i]);
        }

        getdate();

    }

    private void getdate() {

        MGetFormScheduleReq req = new MGetFormScheduleReq();
        req.projectId = projectId;
        req.id = id;

        AndroidApplication.getInstance().doPostAsyncfilexx(RetrofitFactory.BASE_URL + "progress/getProgressByProId", req,
                new OkhttpCallbackUtils<GetFormScheduleRsp>(new TypeReference<BaseBeanRsp<GetFormScheduleRsp>>() {
                }) {

                    @Override
                    public void onFailure(IOException e) {
                        super.onFailure(e);
                        Toast.makeText(getApplicationContext(), "网络连接失败，请稍后重试！", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(BaseBeanRsp<GetFormScheduleRsp> t) {
                        super.onResponse(t);
                        if (t.verification) {
                            itemLabel[0].setText(t.projectList.get(0).projectXXJD);
                            itemLabel[1].setText(t.projectList.get(0).projectPart);
                            remark.setText(t.projectList.get(0).remark);
                            itemLabel[2].setText(t.projectList.get(0).updatedate);

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

}
