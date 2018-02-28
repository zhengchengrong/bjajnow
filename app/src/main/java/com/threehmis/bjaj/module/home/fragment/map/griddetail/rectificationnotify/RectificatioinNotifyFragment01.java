package com.threehmis.bjaj.module.home.fragment.map.griddetail.rectificationnotify;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.threehmis.bjaj.module.base.BaseFragment;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.localcheck.LocalCheckDetailActivity;
import com.threehmis.bjaj.widget.EmptyLayout;
import com.vondear.rxtools.RxSPUtils;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;

/**
 * Created by llz on 2018/2/27.
 */

public class RectificatioinNotifyFragment01 extends BaseFragment {
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    BaseQuickAdapter mBaseQuickAdapter;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    @BindView(R.id.iv_date)
    ImageView mIvDate;
    @BindView(R.id.rl_date)
    RelativeLayout mRlDate;
    private ArrayList<ProjectTaskCheckRsp> mProjectStatusRsps;

    int mYear, mMonth, mDay;
    String days="";

    private String id = "";

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

        id = RxSPUtils.getString(mActivity, Const.PROJECTID);
        mProjectStatusRsps = new ArrayList<ProjectTaskCheckRsp>();
        mRvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        mBaseQuickAdapter = new BaseQuickAdapter<ProjectTaskCheckRsp, BaseViewHolder>(R.layout.item_local_check_01, mProjectStatusRsps) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, final ProjectTaskCheckRsp rowsBean) {
                baseViewHolder.setText(R.id.tv_01, baseViewHolder.getAdapterPosition() + 1 + "");
                baseViewHolder.setText(R.id.tv_02, rowsBean.getCheckNum());
                baseViewHolder.setText(R.id.tv_03, rowsBean.getCheckMen());
                baseViewHolder.setText(R.id.tv_04, rowsBean.getCheckDate());
                baseViewHolder.getView(R.id.tv_05).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mActivity, LocalCheckDetailActivity.class);
                        intent.putExtra(Const.BEAN, rowsBean);
                        startActivity(intent);

                    }
                });
            }
        };
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        mRlDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(mActivity, onDateSetListener, mYear, mMonth, mDay).show();
            }
        });
        mRvContent.setAdapter(mBaseQuickAdapter);

        getDatas();
    }

    private void getDatas() {
        showLoading();
        ProjectCheckTaskReq req = new ProjectCheckTaskReq();
        // req.setProjectId(id);
     /*   req.setProjectId(id);
        req.setCheckStatus(0+"");
        req.setSignDate(DateUtil.getStringDateShort());*/
        req.setProjectId("1bb69ede-b55f-46e8-b35b-1540ae7bd152");
        req.setCheckStatus(2 + "");
        req.setSignDate(days);
        Observable<BaseBeanRsp<ProjectTaskCheckRsp>> observable = RetrofitFactory.getInstance().getCheckTask(req);
        observable.compose(RxSchedulers.<BaseBeanRsp<ProjectTaskCheckRsp>>compose(
        )).subscribe(new BaseObserver<ProjectTaskCheckRsp>() {
            @Override
            protected void onHandleSuccess(BaseBeanRsp<ProjectTaskCheckRsp> projectTaskCheckRspBaseBeanRsp) {
                mEmptyLayout.hide();
                mProjectStatusRsps.clear();
                mProjectStatusRsps.addAll(projectTaskCheckRspBaseBeanRsp.getProjectList());
                mBaseQuickAdapter.notifyDataSetChanged();
            }

            @Override
            protected void onHandleEmpty(BaseBeanRsp<ProjectTaskCheckRsp> t) {
                mEmptyLayout.hide();
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


    /**
     * 日期选择器对话框监听
     */
    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;

            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }

            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }

            }
            mTvDate.setText(days);
            getDatas();
        }
    };
}
