package com.threehmis.bjaj.module.home.fragment.map.griddetail.supervisionplan;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.BaseObserver;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.RxSchedulers;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.ProjectInfoReq;
import com.threehmis.bjaj.api.bean.request.SupervisionPlanFirstReq;
import com.threehmis.bjaj.api.bean.respon.ProjectStatusRsp;
import com.threehmis.bjaj.api.bean.respon.SupervisionPlanFirstRsp;
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
 * Created by llz on 2018/3/2.
 */

public class SupervisionPlanFragment02 extends BaseFragment {
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.tv_search)
    ImageView mTvSearch;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    private String id="";
    BaseQuickAdapter mBaseQuickAdapter;
    private ArrayList<SupervisionPlanFirstRsp> mPlanFirstRsps;
    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        id = RxSPUtils.getString(mActivity, Const.PROJECTID);
        mPlanFirstRsps = new ArrayList<SupervisionPlanFirstRsp>();
        mRvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        mBaseQuickAdapter = new BaseQuickAdapter<SupervisionPlanFirstRsp, BaseViewHolder>(R.layout.item_supervision_plan_01, mPlanFirstRsps) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, SupervisionPlanFirstRsp rowsBean) {
                baseViewHolder.setText(R.id.tv_01,baseViewHolder.getAdapterPosition()+1+"");
                baseViewHolder.setText(R.id.tv_02,rowsBean.getJdjhBGRQ());
                baseViewHolder.setText(R.id.tv_03,rowsBean.getJdjhBGR());
                baseViewHolder.getView(R.id.tv_04).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RxToast.showToast("查看");
                    }
                });
            }
        };
        mRvContent.setAdapter(mBaseQuickAdapter);

        getDatas();
    }
    private void getDatas() {
        SupervisionPlanFirstReq req = new SupervisionPlanFirstReq();
       // req.setProjectId(id);
         req.setProjectId("5f82526c-ffae-4b4d-b63b-0d357c7db42d");
        req.setMonitorName(Const.SUPERVISIONPLANTEXT2);// 监督计划变更
        Observable<BaseBeanRsp<SupervisionPlanFirstRsp>> observable = RetrofitFactory.getInstance().getMonitorInfo(req);
        observable.compose(RxSchedulers.<BaseBeanRsp<SupervisionPlanFirstRsp>>compose(
        )).subscribe(new BaseObserver<SupervisionPlanFirstRsp>() {
            @Override
            protected void onHandleSuccess(BaseBeanRsp<SupervisionPlanFirstRsp> beanRsp) {
                if (beanRsp.getProjectList()!=null&&beanRsp.getProjectList().size() > 0) {
                    mPlanFirstRsps.addAll(beanRsp.getProjectList());
                    mBaseQuickAdapter.notifyDataSetChanged();

                }

            }

            @Override
            protected void onHandleEmpty(BaseBeanRsp<SupervisionPlanFirstRsp> t) {
                RxToast.showToast(t.getResult());
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_supervision_plan2;
    }


}
