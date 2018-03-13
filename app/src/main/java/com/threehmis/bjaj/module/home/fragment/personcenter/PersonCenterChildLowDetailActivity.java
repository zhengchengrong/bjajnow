/*
 * Copyright (c) zcr 2018.
 */

package com.threehmis.bjaj.module.home.fragment.personcenter;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.BaseObserver;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.RxSchedulers;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.CommonProjectIdReq;
import com.threehmis.bjaj.module.base.BaseActivity;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * Created by llz on 2018/3/13.
 */

public class PersonCenterChildLowDetailActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.other)
    TextView mOther;
    @BindView(R.id.tv_back)
    TextView mTvBack;
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
    private String pk;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_person_center_child_low_detail;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        pk = getIntent().getStringExtra(Const.PK);
        initTitle();
        getData();
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
    private void getData() {
        CommonProjectIdReq req = new CommonProjectIdReq();
        req.setPk(pk);
        Observable<BaseBeanRsp<Object>> observable = RetrofitFactory.getInstance().getLawDataInfo(req);
        observable.compose(RxSchedulers.<BaseBeanRsp<Object>>compose(
        )).subscribe(new BaseObserver<Object>() {
            @Override
            protected void onHandleSuccess(BaseBeanRsp<Object> t) {
                if(t.getObjectVO()!=null){
                    mTv01.setText(t.getObjectVO().getLawDataType());
                    mTv02.setText(t.getObjectVO().getLawDataTitle());
                    mTv03.setText(t.getObjectVO().getEvaluationCriterion());
                    mTv04.setText(t.getObjectVO().getSubItem());
                    mTv05.setText(t.getObjectVO().getInspectionObject());
                    mTv06.setText(t.getObjectVO().getEvaluationPhase());
                    if(t.getObjectVO().getIsPhotoUpload()!=null) {
                        mTv07.setText(t.getObjectVO().getIsPhotoUpload().equals("0") ? "否" : "是");
                    }
                    mTv08.setText(t.getObjectVO().getLawDataScore());
                    mTv09.setText(t.getObjectVO().getLawDataIndex());
                    mTv10.setText(t.getObjectVO().getLawDataContent());

                }
            }

            @Override
            protected void onHandleEmpty(BaseBeanRsp<Object> t) {
                RxToast.showToast(t.getResult());
            }
        });
    }
    private void initTitle() {
        mTvTitle.setText("安全管理查看");
        mTvBack.setVisibility(View.VISIBLE);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
