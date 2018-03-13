/*
 * Copyright (c) zcr 2018.
 */

package com.threehmis.bjaj.module.home.fragment.map.griddetail.jsj;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.bean.respon.JsjBeanRsp;
import com.threehmis.bjaj.module.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by llz on 2018/3/9.
 */

public class JsjDetailActivity extends BaseActivity {
    @BindView(R.id.tv_01)
    TextView mTv01;
    @BindView(R.id.tv_02)
    TextView mTv02;
    @BindView(R.id.tv_03)
    TextView mTv03;
    @BindView(R.id.tv_04)
    TextView mTv04;
    @BindView(R.id.tv_05)
    TextView mTv05;
    @BindView(R.id.tv_06)
    TextView mTv06;
    @BindView(R.id.tv_07)
    TextView mTv07;
    @BindView(R.id.tv_08)
    TextView mTv08;
    @BindView(R.id.tv_09)
    TextView mTv09;
    @BindView(R.id.tv_10)
    TextView mTv10;
    @BindView(R.id.tv_11)
    TextView mTv11;
    @BindView(R.id.tv_12)
    TextView mTv12;
    @BindView(R.id.tv_13)
    TextView mTv13;
    @BindView(R.id.tv_14)
    TextView mTv14;
    @BindView(R.id.tv_15)
    TextView mTv15;
    @BindView(R.id.tv_16)
    TextView mTv16;
    @BindView(R.id.tv_17)
    TextView mTv17;
    @BindView(R.id.tv_18)
    TextView mTv18;
    @BindView(R.id.tv_19)
    TextView mTv19;
    @BindView(R.id.tv_20)
    TextView mTv20;
    @BindView(R.id.tv_21)
    TextView mTv21;
    @BindView(R.id.tv_22)
    TextView mTv22;
    @BindView(R.id.tv_23)
    TextView mTv23;
    @BindView(R.id.tv_24)
    TextView mTv24;
    @BindView(R.id.tv_25)
    TextView mTv25;
    @BindView(R.id.tv_26)
    TextView mTv26;
    @BindView(R.id.tv_27)
    TextView mTv27;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.other)
    TextView mOther;
    @BindView(R.id.tv_back)
    TextView mTvBack;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_jsj_detail;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initTitle();
        JsjBeanRsp rowsBean = (JsjBeanRsp) getIntent().getSerializableExtra(Const.JSJ);
        if (rowsBean != null) {
            mTv01.setText(rowsBean.getDtgcmc());
            mTv02.setText(rowsBean.getBadjbbh());
            mTv03.setText(rowsBean.getCpmc());
            mTv04.setText(rowsBean.getCpxh());
            mTv05.setText(rowsBean.getSccsmc());
            mTv06.setText(rowsBean.getKxjscgjdzsbh());
            mTv07.setText(rowsBean.getJcdwmc());
            mTv08.setText(rowsBean.getJcdwlxr());
            mTv09.setText(rowsBean.getJcdwlxdh());
            mTv10.setText(rowsBean.getJcbgbh());
            mTv11.setText(rowsBean.getJcsj());
            mTv12.setText(rowsBean.getJcjl());
            mTv13.setText(rowsBean.getSyjws());
            mTv14.setText(rowsBean.getJtgd());
            mTv15.setText(rowsBean.getJtkd());
            mTv16.setText(rowsBean.getJtzczdkd());
            mTv17.setText(rowsBean.getDytshz());
            mTv18.setText(rowsBean.getDlxs());
            mTv19.setText(rowsBean.getTssd());
            mTv20.setText(rowsBean.getSfycc());
            mTv21.setText(rowsBean.getYccbsczsj());
            mTv22.setText(rowsBean.getCcr());
            mTv23.setText(rowsBean.getCcrspsj());
            mTv24.setText(rowsBean.getCcrszjg());
            mTv25.setText(rowsBean.getCcrszbm());
            mTv26.setText(rowsBean.getCjsj());
            mTv27.setText(rowsBean.getXgsj());


        }

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    private void initTitle() {
        mTvTitle.setText("脚手架查看");
        mTvBack.setVisibility(View.VISIBLE);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
