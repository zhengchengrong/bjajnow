/*
 * Copyright (c) zcr 2018.
 */

package com.threehmis.bjaj.module.home.fragment.map.griddetail.errormessage;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.bean.respon.ProjectTaskCheckRsp;
import com.threehmis.bjaj.module.base.BaseActivity;
import com.threehmis.bjaj.widget.EmptyLayout;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by llz on 2018/3/8.
 */

public class ErrorMessageActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.other)
    TextView mOther;
    @BindView(R.id.tv_back)
    TextView mTvBack;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    BaseQuickAdapter mBaseQuickAdapter;
    private ArrayList<ProjectTaskCheckRsp> mProjectStatusRsps;

    private String id="";
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_error_message;
    }

    @Override
    protected void initInjector() {

    }
    @Override
    protected void initViews() {
        initTitle();
        mRvContent.setLayoutManager(new LinearLayoutManager(this));
        mBaseQuickAdapter = new BaseQuickAdapter<ProjectTaskCheckRsp, BaseViewHolder>(R.layout.item_error_message_01, mProjectStatusRsps) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, ProjectTaskCheckRsp rowsBean) {
                baseViewHolder.setText(R.id.tv_01,baseViewHolder.getAdapterPosition()+1+"");
                baseViewHolder.setText(R.id.tv_02,rowsBean.getCheckNum());
            }
        };
        mRvContent.setAdapter(mBaseQuickAdapter);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
    private void initTitle() {
        mTvTitle.setText("处罚信息");
        mTvBack.setVisibility(View.VISIBLE);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
