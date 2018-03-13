/*
 * Copyright (c) zcr 2018.
 */

package com.threehmis.bjaj.module.home.fragment.personcenter;

import android.content.Intent;
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
import com.threehmis.bjaj.api.BaseObserver;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.RxSchedulers;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.CommonProjectIdReq;
import com.threehmis.bjaj.module.base.BaseActivity;
import com.threehmis.bjaj.widget.EmptyLayout;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * Created by llz on 2018/3/13.
 */

public class PersonCenterChildDetailActivity extends BaseActivity {

    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.tv_search)
    ImageView mTvSearch;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.other)
    TextView mOther;
    @BindView(R.id.tv_back)
    TextView mTvBack;
    private String type;
    private String search;
    BaseQuickAdapter mBaseQuickAdapter;
    ArrayList<BaseBeanRsp.JokerVOBean.MonitorListBean> mProjectStatusRsps;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_person_center_child_detail01;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initTitle();
        type = getIntent().getStringExtra(Const.TYPE);
        mRvContent.setLayoutManager(new LinearLayoutManager(this));
        mProjectStatusRsps = new ArrayList<BaseBeanRsp.JokerVOBean.MonitorListBean>();
        mBaseQuickAdapter = new BaseQuickAdapter<BaseBeanRsp.JokerVOBean.MonitorListBean, BaseViewHolder>(R.layout.item_person_center_child_detail_01, mProjectStatusRsps) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, final BaseBeanRsp.JokerVOBean.MonitorListBean rowsBean) {
                baseViewHolder.setText(R.id.tv_01, baseViewHolder.getAdapterPosition() + 1+"");
                baseViewHolder.setText(R.id.tv_02, rowsBean.getLawTitle()==null?"": rowsBean.getLawTitle());
                String isPhoto = rowsBean.getIsPhotoUpload()==null?"": rowsBean.getIsPhotoUpload();
                if(isPhoto.equals("0")){
                    baseViewHolder.setText(R.id.tv_03,"否");
                }else{
                    baseViewHolder.setText(R.id.tv_03,"是");
                }
                baseViewHolder.setText(R.id.tv_04, rowsBean.getLawDataScore()==null?"": rowsBean.getLawDataScore());
                baseViewHolder.getView(R.id.ll_01).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(PersonCenterChildDetailActivity.this,PersonCenterChildLowDetailActivity.class);
                        intent.putExtra(Const.PK,rowsBean.getPk());
                        startActivity(intent);
                    }
                });
            }
        };
        mRvContent.setAdapter(mBaseQuickAdapter);
        getData("");
        mTvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search = mEtSearch.getText().toString() == null ? "" : mEtSearch.getText().toString();
                getData(search);
            }
        });

    }

    private void getData(String search) {
        CommonProjectIdReq req = new CommonProjectIdReq();
        req.setLawType(type);
        req.setLawTitle(search);
        Observable<BaseBeanRsp<Object>> observable = RetrofitFactory.getInstance().getLawDataList(req);
        observable.compose(RxSchedulers.<BaseBeanRsp<Object>>compose(
        )).subscribe(new BaseObserver<Object>() {
            @Override
            protected void onHandleSuccess(BaseBeanRsp<Object> t) {
                if (t.getJokerVO() != null && t.getJokerVO().getJokerVOList() != null) {
                    mProjectStatusRsps.clear();
                    mProjectStatusRsps.addAll(t.getJokerVO().getJokerVOList());
                    mBaseQuickAdapter.notifyDataSetChanged();
                }
            }

            @Override
            protected void onHandleEmpty(BaseBeanRsp<Object> t) {
                RxToast.showToast(t.getResult());
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

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


}
