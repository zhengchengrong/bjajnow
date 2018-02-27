package com.threehmis.bjaj.module.home.fragment.map.griddetail.localcheck;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by llz on 2018/2/27.
 */

public class LocalCheckDetailActivity extends BaseActivity {
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
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    ProjectTaskCheckRsp mProjectTaskCheckRsp;
    @BindView(R.id.rb_01)
    RadioButton mRb01;
    @BindView(R.id.rb_02)
    RadioButton mRb02;
    @BindView(R.id.rb_03)
    RadioButton mRb03;
    @BindView(R.id.rg_basic)
    RadioGroup mRgBasic;
    private String rbBasic;

    private BaseQuickAdapter mBaseQuickAdapter;
    private ArrayList<ProjectTaskCheckRsp.CheckDivisionVOSetBean> datas;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_local_check_detail;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initTitle();
        mProjectTaskCheckRsp = (ProjectTaskCheckRsp) getIntent().getSerializableExtra(Const.BEAN);
        mTv01.setText(mProjectTaskCheckRsp.getCheckNum());
        mTv02.setText(mProjectTaskCheckRsp.getCheckDate());
        mTv03.setText(mProjectTaskCheckRsp.getCheckMen());

        if(mProjectTaskCheckRsp.getCheckBasis().equals("计划")){
            mRb01.setChecked(true);
        }
        if(mProjectTaskCheckRsp.getCheckBasis().equals("专项")){
            mRb02.setChecked(true);
        }
        if(mProjectTaskCheckRsp.getCheckBasis().equals("其他")){
            mRb03.setChecked(true);
        }
        mRgBasic.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.rb_01){
                    rbBasic = "计划";
                }else if(i == R.id.rb_02){
                     rbBasic = "专项";
                }else{
                    rbBasic = "其他";
                }
            }
        });

        mRvContent.setLayoutManager(new LinearLayoutManager(this));
        datas = new ArrayList<ProjectTaskCheckRsp.CheckDivisionVOSetBean>();
        datas.addAll(mProjectTaskCheckRsp.getCheckDivisionVOSet());
        mBaseQuickAdapter = new BaseQuickAdapter<ProjectTaskCheckRsp.CheckDivisionVOSetBean, BaseViewHolder>(R.layout.item_local_check_detail, datas) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, ProjectTaskCheckRsp.CheckDivisionVOSetBean rowsBean) {

            }
        };
        mRvContent.setAdapter(mBaseQuickAdapter);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    private void initTitle() {
        mTvTitle.setText("现场检查");
        mTvBack.setVisibility(View.VISIBLE);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}