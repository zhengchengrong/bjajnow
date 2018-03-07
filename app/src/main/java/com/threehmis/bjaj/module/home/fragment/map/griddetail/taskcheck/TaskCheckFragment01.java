package com.threehmis.bjaj.module.home.fragment.map.griddetail.taskcheck;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.BaseObserver;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.RxSchedulers;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.ProjectCheckTaskReq;
import com.threehmis.bjaj.api.bean.request.ProjectInfoReq;
import com.threehmis.bjaj.api.bean.respon.GetMyProjectInfoRsp;
import com.threehmis.bjaj.api.bean.respon.ProjectInfoOneRep;
import com.threehmis.bjaj.api.bean.respon.ProjectInfoTwoRep;
import com.threehmis.bjaj.api.bean.respon.ProjectStatusRsp;
import com.threehmis.bjaj.api.bean.respon.ProjectTaskCheckRsp;
import com.threehmis.bjaj.module.base.BaseFragment;
import com.threehmis.bjaj.utils.DateUtil;
import com.threehmis.bjaj.widget.EmptyLayout;
import com.vondear.rxtools.RxSPUtils;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.threehmis.bjaj.api.RxSchedulers.compose;

/**
 * Created by llz on 2018/2/27.
 */

public class TaskCheckFragment01 extends BaseFragment {
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    BaseQuickAdapter mBaseQuickAdapter;
    private ArrayList<ProjectTaskCheckRsp> mProjectStatusRsps;

    private String id="";

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        id = RxSPUtils.getString(mActivity, Const.PROJECTID);
        mProjectStatusRsps = new ArrayList<ProjectTaskCheckRsp>();
        mRvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        mBaseQuickAdapter = new BaseQuickAdapter<ProjectTaskCheckRsp, BaseViewHolder>(R.layout.item_task_check_01, mProjectStatusRsps) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, ProjectTaskCheckRsp rowsBean) {
                baseViewHolder.setText(R.id.tv_01,baseViewHolder.getAdapterPosition()+1+"");
                baseViewHolder.setText(R.id.tv_02,rowsBean.getCheckNum());
                baseViewHolder.setText(R.id.tv_03,rowsBean.getCheckMen());
                baseViewHolder.setText(R.id.tv_04,rowsBean.getCheckDate());
                baseViewHolder.getView(R.id.tv_05).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RxToast.showToast("修改");
                    }
                });
                baseViewHolder.getView(R.id.tv_06).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RxToast.showToast("删除");
                    }
                });
            }
        };
        mRvContent.setAdapter(mBaseQuickAdapter);

        getDatas();
    }
    private void getDatas() {
        ProjectCheckTaskReq req = new ProjectCheckTaskReq();
       // req.setProjectId(id);
        req.setProjectId(id);
        req.setCheckStatus(0+"");
        req.setSignDate(DateUtil.getStringDateShort());
/*       req.setProjectId("1bb69ede-b55f-46e8-b35b-1540ae7bd152");
        req.setCheckStatus(2+"");
        req.setSignDate("2018-01-29");*/
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

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_task_check01;
    }



}
