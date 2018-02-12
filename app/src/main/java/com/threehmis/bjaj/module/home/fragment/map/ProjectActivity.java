package com.threehmis.bjaj.module.home.fragment.map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.TypeReference;
import com.jude.rollviewpager.RollPagerView;
import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.adapter.MenusGridviewAdapter;
import com.threehmis.bjaj.adapter.ProjectRollAdapter;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.GetMenusListReq;
import com.threehmis.bjaj.api.bean.respon.GetMenusListRsp;
import com.threehmis.bjaj.utils.CDUtil;
import com.threehmis.bjaj.utils.OkhttpCallbackUtils;
import com.threehmis.bjaj.widget.NoScrollGridView;


import java.io.IOException;

public class ProjectActivity extends AppCompatActivity implements View.OnClickListener {

    private RollPagerView rollviewpager;
    private ProjectRollAdapter squareRollAdapter;
    private ImageView back;
    private TextView title;
    NoScrollGridView mygridView;

    private String projectID,projectName,customerId;
    private MenusGridviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        projectID=getIntent().getStringExtra(Const.PROJECTID);
        projectName=getIntent().getStringExtra(Const.PROJECTNAME);
        customerId=getIntent().getStringExtra(Const.CUSTOMERID);
        if(TextUtils.isEmpty(customerId)){
            customerId = "BJAJ001";
        }
        title = (TextView) findViewById(R.id.title);
        title.setText(projectName);
        mygridView = (NoScrollGridView) findViewById(R.id.mygridView);
        rollviewpager = (RollPagerView) findViewById(R.id.roll_view_pager);
        squareRollAdapter = new ProjectRollAdapter();
        rollviewpager.setHintView(null);
        rollviewpager.setAdapter(squareRollAdapter);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        adapter = new MenusGridviewAdapter(this,projectID,projectName);
        mygridView.setAdapter(adapter);
        getData();
    }
    private void getData() {
        GetMenusListReq req =new GetMenusListReq();
        req.customerId=customerId;
        req.appType=1+"";
            AndroidApplication.getInstance().doPostAsyncfilexx(RetrofitFactory.BASE_URL+"menu/getMenus", req,
                    new OkhttpCallbackUtils<GetMenusListRsp>(new TypeReference<BaseBeanRsp<GetMenusListRsp>>() {
                    }) {
                        @Override
                        public void onFailure(IOException e) {
                            super.onFailure(e);
                            Toast.makeText(getApplicationContext(), "网络连接失败，请稍后重试！", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onResponse(BaseBeanRsp<GetMenusListRsp> t) {
                            super.onResponse(t);
                            if (t.verification) {
                                adapter.DateNotify(t.projectList);
                                CDUtil.saveObject(t.projectList, "MenusDate");
                            } else {
                                Toast.makeText(getApplicationContext(), t.result, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }
}