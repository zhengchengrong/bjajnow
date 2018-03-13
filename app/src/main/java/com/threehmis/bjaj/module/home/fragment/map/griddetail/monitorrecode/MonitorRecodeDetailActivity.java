/*
 * Copyright (c) zcr 2018.
 */

package com.threehmis.bjaj.module.home.fragment.map.griddetail.monitorrecode;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.bean.respon.ProjectTaskCheckRsp;
import com.threehmis.bjaj.module.base.BaseActivity;
import com.threehmis.bjaj.widget.EmptyLayout;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.shts.android.library.TriangleLabelView;

/**
 * Created by llz on 2018/2/27.
 */

public class MonitorRecodeDetailActivity extends BaseActivity {

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
    @BindView(R.id.rb_01)
    RadioButton mRb01;
    @BindView(R.id.rb_02)
    RadioButton mRb02;
    @BindView(R.id.rb_03)
    RadioButton mRb03;
    @BindView(R.id.rg_basic)
    RadioGroup mRgBasic;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    @BindView(R.id.et_01)
    EditText mEt01;
    @BindView(R.id.tv_commit)
    TextView mTvCommit;
    @BindView(R.id.rb_04)
    RadioButton mRb04;
    @BindView(R.id.rb_05)
    RadioButton mRb05;
    @BindView(R.id.rb_06)
    RadioButton mRb06;
    @BindView(R.id.rb_07)
    RadioButton mRb07;
    @BindView(R.id.rg_basic02)
    RadioGroup mRgBasic02;
    @BindView(R.id.tv_05)
    TextView mTv05;
    @BindView(R.id.tv_06)
    TextView mTv06;
    @BindView(R.id.rb_08)
    RadioButton mRb08;
    @BindView(R.id.rb_09)
    RadioButton mRb09;
    @BindView(R.id.rb_10)
    RadioButton mRb10;
    @BindView(R.id.rg_basic03)
    RadioGroup mRgBasic03;
    @BindView(R.id.tv_07)
    TextView mTv07;
    private String rbBasic;

    private boolean flag;
    private ProjectTaskCheckRsp mProjectTaskCheckRsp;
    private BaseQuickAdapter mBaseQuickAdapter;
    private ArrayList<ProjectTaskCheckRsp.CheckDivisionVOSetBean> datas;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_monitor_recode_detail;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initTitle();
        flag = getIntent().getBooleanExtra(Const.FLAG, false);
        mProjectTaskCheckRsp = (ProjectTaskCheckRsp) getIntent().getSerializableExtra(Const.BEAN);
        mTv01.setText(mProjectTaskCheckRsp.getCheckNum());
        mTv02.setText(mProjectTaskCheckRsp.getCheckDate());
        mTv03.setText(mProjectTaskCheckRsp.getCheckMen());

        if(!TextUtils.isEmpty(mProjectTaskCheckRsp.getReformType())) {
            if (mProjectTaskCheckRsp.getReformType().equals("立即整改")) {
                mRb04.setChecked(true);
            } else if (mProjectTaskCheckRsp.getReformType().equals("期限整改")) {
                mRb05.setChecked(true);
            } else if (mProjectTaskCheckRsp.getReformType().equals("停工整改")) {
                mRb06.setChecked(true);
            } else  {
                mRb07.setChecked(true);
            }
        }
        mTv05.setText(mProjectTaskCheckRsp.getReformDate());

        if(mProjectTaskCheckRsp.getIsUnitJd().equals("1")){
            mRb08.setChecked(true);
        }
        if(mProjectTaskCheckRsp.getIsCityJd().equals("1")){
            mRb09.setChecked(true);
        }
        if(mProjectTaskCheckRsp.getIsCountyJd().equals("1")){
            mRb10.setChecked(true);
        }

        if (mProjectTaskCheckRsp.getCheckBasis().equals("计划")) {
            mRb01.setChecked(true);
        }
        if (mProjectTaskCheckRsp.getCheckBasis().equals("专项")) {
            mRb02.setChecked(true);
        }
        if (mProjectTaskCheckRsp.getCheckBasis().equals("其他")) {
            mRb03.setChecked(true);
        }
        mRgBasic.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_01) {
                    rbBasic = "计划";
                } else if (i == R.id.rb_02) {
                    rbBasic = "专项";
                } else {
                    rbBasic = "其他";
                }
            }
        });

        mRvContent.setLayoutManager(new LinearLayoutManager(this));
        datas = new ArrayList<ProjectTaskCheckRsp.CheckDivisionVOSetBean>();
        datas.addAll(mProjectTaskCheckRsp.getCheckDivisionVOSet());
        mBaseQuickAdapter = new BaseQuickAdapter<ProjectTaskCheckRsp.CheckDivisionVOSetBean, BaseViewHolder>(R.layout.item_monitor_recode_detail, datas) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, ProjectTaskCheckRsp.CheckDivisionVOSetBean rowsBean) {
                TriangleLabelView triangleLabelView =    baseViewHolder.getView(R.id.tlv_01);
                triangleLabelView.setPrimaryText(baseViewHolder.getAdapterPosition()+1+"");
                baseViewHolder.setText(R.id.tv_01, rowsBean.getCheckType());
                baseViewHolder.setText(R.id.tv_02, rowsBean.getCheckContent());
                baseViewHolder.setText(R.id.tv_03, rowsBean.getSingleProject());
                baseViewHolder.setText(R.id.tv_04, rowsBean.getCheckPart());
                final EditText editText = baseViewHolder.getView(R.id.et_issue);
                RadioGroup radioGroup = baseViewHolder.getView(R.id.rg_basic);
                RadioButton radioButton01 =baseViewHolder.getView(R.id.rb_01);
                RadioButton radioButton02=baseViewHolder.getView(R.id.rb_02);
                if(TextUtils.isEmpty(rowsBean.getCheckResult())){
                    radioButton01.setChecked(true);
                }else{
                    radioButton02.setChecked(true);
                    editText.setVisibility(View.VISIBLE);
                    editText.setText(rowsBean.getCheckResult());
                }

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        switch (checkedId) {
                            case R.id.rb_01:// 未见异常
                                editText.setText("");
                                editText.setVisibility(View.GONE);
                                break;
                            case R.id.rb_02: // 问题项
                                editText.setVisibility(View.VISIBLE);
                                break;
                        }


                    }
                });
            }
        };
        mRvContent.setAdapter(mBaseQuickAdapter);
        if (flag == true) {
            mTvCommit.setVisibility(View.GONE);
            mEt01.setVisibility(View.GONE);
        } else {
            mTvCommit.setVisibility(View.VISIBLE);
            mEt01.setVisibility(View.VISIBLE);
        }
        mTvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxToast.showToast("提交");
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    private void initTitle() {
        mTvTitle.setText("监督记录");
        mTvBack.setVisibility(View.VISIBLE);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
