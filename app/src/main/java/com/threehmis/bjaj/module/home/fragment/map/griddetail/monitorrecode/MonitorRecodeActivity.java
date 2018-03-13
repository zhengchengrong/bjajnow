package com.threehmis.bjaj.module.home.fragment.map.griddetail.monitorrecode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.BaseObserver;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.RxSchedulers;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.ProjectCheckTaskReq;
import com.threehmis.bjaj.api.bean.respon.ProjectTaskCheckRsp;
import com.threehmis.bjaj.module.base.BaseActivity;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.localcheck.LocalCheckDetailActivity;
import com.threehmis.bjaj.utils.DateUtil;
import com.threehmis.bjaj.widget.EmptyLayout;
import com.vondear.rxtools.RxSPUtils;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * Created by llz on 2018/2/28.
 */

public class MonitorRecodeActivity extends BaseActivity {

    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    BaseQuickAdapter mBaseQuickAdapter;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.other)
    TextView mOther;
    @BindView(R.id.tv_back)
    TextView mTvBack;
    private ArrayList<ProjectTaskCheckRsp> mProjectStatusRsps;

    private String id = "";

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_monitor_recode;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initTitle();
        id = RxSPUtils.getString(this, Const.PROJECTID);
        mProjectStatusRsps = new ArrayList<ProjectTaskCheckRsp>();
        mRvContent.setLayoutManager(new LinearLayoutManager(this));
        mBaseQuickAdapter = new BaseQuickAdapter<ProjectTaskCheckRsp, BaseViewHolder>(R.layout.item_monitor_recode, mProjectStatusRsps) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, final ProjectTaskCheckRsp rowsBean) {
                baseViewHolder.setText(R.id.tv_01, baseViewHolder.getAdapterPosition() + 1 + "");
                baseViewHolder.setText(R.id.tv_02, rowsBean.getCheckNum());
                baseViewHolder.setText(R.id.tv_03, rowsBean.getCheckMen());
                baseViewHolder.setText(R.id.tv_04, rowsBean.getCheckDate());
                baseViewHolder.getView(R.id.ll_01).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MonitorRecodeActivity.this,MonitorRecodeDetailActivity.class);
                        intent.putExtra(Const.BEAN, rowsBean);
                        intent.putExtra(Const.FLAG,true);//查看
                        startActivity(intent);
                    }
                });
            }
        };
        mRvContent.setAdapter(mBaseQuickAdapter);

        getDatas();
    }

    private void getDatas() {
        ProjectCheckTaskReq req = new ProjectCheckTaskReq();
        req.setProjectId(id);
        req.setCheckStatus(-4 + "");
        Observable<BaseBeanRsp<ProjectTaskCheckRsp>> observable = RetrofitFactory.getInstance().getCheckTask(req);
        observable.compose(RxSchedulers.<BaseBeanRsp<ProjectTaskCheckRsp>>compose(
        )).subscribe(new BaseObserver<ProjectTaskCheckRsp>() {
            @Override
            protected void onHandleSuccess(BaseBeanRsp<ProjectTaskCheckRsp> projectTaskCheckRspBaseBeanRsp) {
                projectTaskCheckRspBaseBeanRsp.getProjectList();
                mProjectStatusRsps.addAll(projectTaskCheckRspBaseBeanRsp.getProjectList());
                mBaseQuickAdapter.notifyDataSetChanged();
            }

            @Override
            protected void onHandleEmpty(BaseBeanRsp<ProjectTaskCheckRsp> t) {
                RxToast.showToast(t.getResult());
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
