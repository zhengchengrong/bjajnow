package com.threehmis.bjaj.module.home.fragment.map.griddetail.localcheck;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
import com.threehmis.bjaj.module.base.BaseFragment;
import com.threehmis.bjaj.utils.DateUtil;
import com.threehmis.bjaj.widget.EmptyLayout;
import com.vondear.rxtools.RxSPUtils;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.Observable;
import jp.shts.android.library.TriangleLabelView;

/**
 * Created by llz on 2018/2/27.
 */

public class LocalCheckFragment02 extends BaseFragment {
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
        mBaseQuickAdapter = new BaseQuickAdapter<ProjectTaskCheckRsp, BaseViewHolder>(R.layout.item_local_check_02, mProjectStatusRsps) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, final ProjectTaskCheckRsp rowsBean) {
                baseViewHolder.setText(R.id.tv_01,baseViewHolder.getAdapterPosition()+1+"");
                baseViewHolder.setText(R.id.tv_02,rowsBean.getCheckNum());
                baseViewHolder.setText(R.id.tv_03,rowsBean.getCheckMen());
                baseViewHolder.setText(R.id.tv_04,rowsBean.getCheckDate());
                baseViewHolder.getView(R.id.ll_01).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mActivity,LocalCheckDetail2Activity.class);
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
       // req.setProjectId(id);
  /*      req.setProjectId("1bb69ede-b55f-46e8-b35b-1540ae7bd152");
        req.setCheckStatus(-2+"");
        req.setSignDate("2018-01-29");*/
        req.setProjectId(id);
        req.setCheckStatus(-2+"");
        //req.setCheckStatus(1+"");
        //  req.setSignDate(DateUtil.getStringDateShort());
        Observable<BaseBeanRsp<ProjectTaskCheckRsp>> observable = RetrofitFactory.getInstance().getCheckTask(req);
        observable.compose(RxSchedulers.<BaseBeanRsp<ProjectTaskCheckRsp>>compose(
        )).subscribe(new BaseObserver<ProjectTaskCheckRsp>() {
            @Override
            protected void onHandleSuccess(BaseBeanRsp<ProjectTaskCheckRsp> projectTaskCheckRspBaseBeanRsp) {
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
        return R.layout.fragment_local_check02;
    }


}
