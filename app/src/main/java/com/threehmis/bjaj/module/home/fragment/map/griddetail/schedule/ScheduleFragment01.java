package com.threehmis.bjaj.module.home.fragment.map.griddetail.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.BaseObserver;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.RxSchedulers;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.ProjectInfoReq;
import com.threehmis.bjaj.api.bean.respon.ProjectStatusRsp;
import com.threehmis.bjaj.module.base.BaseFragment;
import com.threehmis.bjaj.widget.EmptyLayout;
import com.vondear.rxtools.RxSPUtils;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;

/**
 * Created by llz on 2018/2/27.
 */

public class ScheduleFragment01 extends BaseFragment {
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    BaseQuickAdapter mBaseQuickAdapter;
    private ArrayList<ProjectStatusRsp> mProjectStatusRsps;

    private String id="";
    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        id = RxSPUtils.getString(mActivity, Const.PROJECTID);
        mProjectStatusRsps = new ArrayList<ProjectStatusRsp>();
        mRvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        mBaseQuickAdapter = new BaseQuickAdapter<ProjectStatusRsp, BaseViewHolder>(R.layout.item_schedule_01, mProjectStatusRsps) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, ProjectStatusRsp rowsBean) {
                baseViewHolder.setText(R.id.tv_01,baseViewHolder.getAdapterPosition()+1+"");
                baseViewHolder.setText(R.id.tv_02,rowsBean.getProjectName());
                baseViewHolder.setText(R.id.tv_03,rowsBean.getRegisterDate());
            }
        };
        mRvContent.setAdapter(mBaseQuickAdapter);

        getDatas();
    }

    private void getDatas() {
        ProjectInfoReq req = new ProjectInfoReq();
        req.setProjectId(id);
        Observable<BaseBeanRsp<ProjectStatusRsp>> observable = RetrofitFactory.getInstance().getProjectStatus(req);
        observable.compose(RxSchedulers.<BaseBeanRsp<ProjectStatusRsp>>compose(
        )).subscribe(new BaseObserver<ProjectStatusRsp>() {
            @Override
            protected void onHandleSuccess(BaseBeanRsp<ProjectStatusRsp> projectStatusRspBaseBeanRsp) {
                mProjectStatusRsps.addAll(projectStatusRspBaseBeanRsp.getProjectList());
                mBaseQuickAdapter.notifyDataSetChanged();
            }
            @Override
            protected void onHandleEmpty(BaseBeanRsp<ProjectStatusRsp> t) {
                RxToast.showToast(t.getResult());
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_schedule01;
    }

}
