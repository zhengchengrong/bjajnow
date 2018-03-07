package com.threehmis.bjaj.module.home.fragment.personcenter;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.bean.respon.ProjectStatusRsp;
import com.threehmis.bjaj.module.base.BaseActivity;
import com.threehmis.bjaj.widget.EmptyLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by llz on 2018/3/7.
 */

public class PersonCenterDetailActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.other)
    TextView mOther;
    @BindView(R.id.tv_back)
    TextView mTvBack;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.tv_search)
    ImageView mTvSearch;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;

    private ArrayList mProjectStatusRsps;
    private BaseQuickAdapter mBaseQuickAdapter;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_person_center_detail;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initTitle();
        mRvContent.setLayoutManager(new LinearLayoutManager(this));
        mProjectStatusRsps = new ArrayList<ProjectStatusRsp>();
        mBaseQuickAdapter = new BaseQuickAdapter<ProjectStatusRsp, BaseViewHolder>(R.layout.person_center_detail_01, mProjectStatusRsps) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, ProjectStatusRsp rowsBean) {
                baseViewHolder.setText(R.id.tv_01,baseViewHolder.getAdapterPosition()+1);
                baseViewHolder.setText(R.id.tv_02,rowsBean.getProjectName());
                baseViewHolder.setText(R.id.tv_03,rowsBean.getRegisterDate());
                baseViewHolder.setText(R.id.tv_04,rowsBean.getRegisterDate());
            }
        };
        mRvContent.setAdapter(mBaseQuickAdapter);
    }
    private void initTitle() {
        mTvTitle.setText("施工安全行为指标-安全管理");
        mTvBack.setVisibility(View.VISIBLE);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    protected void updateViews(boolean isRefresh) {

    }


}
