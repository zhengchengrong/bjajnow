/*
 * Copyright (c) zcr 2018.
 */

package com.threehmis.bjaj.module.home.fragment.map.griddetail.supervisionplan;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.bean.respon.SupervisionPlanFirstRsp;
import com.threehmis.bjaj.module.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by llz on 2018/3/10.
 */

public class SupervisionPlanDetailActivity extends BaseActivity {

    @BindView(R.id.tv_01)
    TextView mTv01;
    @BindView(R.id.et_01)
    EditText mEt01;
    @BindView(R.id.tv_02)
    TextView mTv02;
    @BindView(R.id.tv_date_02)
    TextView mTvDate02;
    @BindView(R.id.tv_03)
    TextView mTv03;
    @BindView(R.id.et_03)
    EditText mEt03;
    @BindView(R.id.tv_04)
    TextView mTv04;
    @BindView(R.id.et_04)
    EditText mEt04;
    @BindView(R.id.tv_05)
    TextView mTv05;
    @BindView(R.id.et_05)
    EditText mEt05;
    @BindView(R.id.tv_06)
    TextView mTv06;
    @BindView(R.id.et_06)
    EditText mEt06;
    @BindView(R.id.tv_07)
    TextView mTv07;
    @BindView(R.id.tv_date_07)
    TextView mTvDate07;
    @BindView(R.id.tv_08)
    TextView mTv08;
    @BindView(R.id.tv_date_08)
    TextView mTvDate08;
    @BindView(R.id.ll_add_person)
    LinearLayout mLlAddPerson;
    @BindView(R.id.tv_bottom_01)
    TextView mTvBottom01;
    @BindView(R.id.tv_flag06)
    TextView mTvFlag06;
    @BindView(R.id.rb_01)
    RadioButton mRb01;
    @BindView(R.id.rb_02)
    RadioButton mRb02;
    @BindView(R.id.rg_01)
    RadioGroup mRg01;
    @BindView(R.id.tv_flag02)
    TextView mTvFlag02;

    @BindView(R.id.tv_bottom_02)
    TextView mTvBottom02;
    @BindView(R.id.et_bottom_03)
    TextView mEtBottom03;
    SupervisionPlanFirstRsp supervisionPlanFirstRsp;
    @BindView(R.id.tv_11)
    TextView mTv11;
    @BindView(R.id.et_07)
    TextView mEt07;
    @BindView(R.id.tv_12)
    TextView mTv12;
    @BindView(R.id.et_08)
    TextView mEt08;
    @BindView(R.id.tv_13)
    TextView mTv13;
    @BindView(R.id.et_09)
    TextView mEt09;
    @BindView(R.id.tv_14)
    TextView mTv14;
    @BindView(R.id.et_10)
    TextView mEt10;
    @BindView(R.id.tv_all)
    TextView mTvAll;
    @BindView(R.id.tv_detail)
    TextView mTvDetail;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.other)
    TextView mOther;
    @BindView(R.id.tv_back)
    TextView mTvBack;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_supervision_plan_detail;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initTitle();
        supervisionPlanFirstRsp = (SupervisionPlanFirstRsp) getIntent().getSerializableExtra(Const.BEAN);
        mEt01.setText(supervisionPlanFirstRsp.getJdjhJHBH() == null ? "" : supervisionPlanFirstRsp.getJdjhJHBH());
        mTvDate02.setText(supervisionPlanFirstRsp.getJdjhBZRQ() == null ? "" : supervisionPlanFirstRsp.getJdjhBZRQ());
        mEt03.setText(supervisionPlanFirstRsp.getJdjhBZR() == null ? "" : supervisionPlanFirstRsp.getJdjhBZR());
        mEt04.setText(supervisionPlanFirstRsp.getJdjhAQJDBH() == null ? "" : supervisionPlanFirstRsp.getJdjhAQJDBH());
        mEt05.setText(supervisionPlanFirstRsp.getJdjhGCMC() == null ? "" : supervisionPlanFirstRsp.getJdjhGCMC());
        mEt06.setText(supervisionPlanFirstRsp.getJdjhGCDZ() == null ? "" : supervisionPlanFirstRsp.getJdjhGCDZ());
        mTvDate07.setText(supervisionPlanFirstRsp.getJdjhJHKGRQ() == null ? "" : supervisionPlanFirstRsp.getJdjhJHKGRQ());
        mTvDate08.setText(supervisionPlanFirstRsp.getJdjhJHJGRQ() == null ? "" : supervisionPlanFirstRsp.getJdjhJHJGRQ());
        mTvAll.setText("共有" + supervisionPlanFirstRsp.getJdjhWDGC() + "项");
        mTvAll.setTextColor(getResources().getColor(R.color.main_color));
        mTvFlag02.setText("(共" + supervisionPlanFirstRsp.getJdjhAQSG() + "起)");
        mEtBottom03.setText(supervisionPlanFirstRsp.getJdjhAQCC() + "次");
        if (supervisionPlanFirstRsp.getJdjhWFS().equals("$U_CHECKBOX_ON$")) {
            mRb01.setChecked(true);
        } else {
            mRb02.setChecked(true);
        }
        mEt07.setText(supervisionPlanFirstRsp.getJdjhJHBGYY() == null ? "" : supervisionPlanFirstRsp.getJdjhJHBGYY());
        mEt08.setText(supervisionPlanFirstRsp.getJdjhJHBGNR() == null ? "" : supervisionPlanFirstRsp.getJdjhJHBGNR());
        mEt09.setText(supervisionPlanFirstRsp.getJdjhBGR() == null ? "" : supervisionPlanFirstRsp.getJdjhBGR());
        mEt10.setText(supervisionPlanFirstRsp.getJdjhBGRQ() == null ? "" : supervisionPlanFirstRsp.getJdjhBGRQ());

        mTvDetail.setText(supervisionPlanFirstRsp.getJdjhJTBK());
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }


    private void initTitle() {
        mTvTitle.setText("变更记录查看");
        mTvBack.setVisibility(View.VISIBLE);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
