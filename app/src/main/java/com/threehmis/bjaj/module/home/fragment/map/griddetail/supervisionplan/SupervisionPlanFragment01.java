package com.threehmis.bjaj.module.home.fragment.map.griddetail.supervisionplan;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.BaseObserver;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.RxSchedulers;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.SupervisionPlanFirstReq;
import com.threehmis.bjaj.api.bean.respon.SupervisionPlanFirstRsp;
import com.threehmis.bjaj.module.base.BaseFragment;
import com.vondear.rxtools.RxSPUtils;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;

/**
 * Created by llz on 2018/3/2.
 */

public class SupervisionPlanFragment01 extends BaseFragment {
    @BindView(R.id.tv_01)
    TextView mTv01;
    @BindView(R.id.et_01)
    EditText mEt01;
    @BindView(R.id.tv_02)
    TextView mTv02;
    @BindView(R.id.tv_date_02)
    TextView mTvDate02;
    @BindView(R.id.tv_03)
    TextView mTv03;
    @BindView(R.id.et_03)
    EditText mEt03;
    @BindView(R.id.tv_04)
    TextView mTv04;
    @BindView(R.id.et_04)
    EditText mEt04;
    @BindView(R.id.tv_05)
    TextView mTv05;
    @BindView(R.id.et_05)
    EditText mEt05;
    @BindView(R.id.tv_06)
    TextView mTv06;
    @BindView(R.id.et_06)
    EditText mEt06;
    @BindView(R.id.tv_07)
    TextView mTv07;
    @BindView(R.id.tv_date_07)
    TextView mTvDate07;
    @BindView(R.id.tv_08)
    TextView mTv08;
    @BindView(R.id.tv_date_08)
    TextView mTvDate08;
    @BindView(R.id.tv_09)
    TextView mTv09;
    @BindView(R.id.et_09)
    EditText mEt09;
    @BindView(R.id.tv_10)
    TextView mTv10;
    @BindView(R.id.et_10)
    EditText mEt10;
    @BindView(R.id.tv_11)
    TextView mTv11;
    @BindView(R.id.et_11)
    EditText mEt11;
    @BindView(R.id.tv_12)
    TextView mTv12;
    @BindView(R.id.et_12)
    EditText mEt12;
    @BindView(R.id.tv_bottom_01)
    TextView mTvBottom01;
    @BindView(R.id.rb_01)
    RadioButton mRb01;
    @BindView(R.id.rb_02)
    RadioButton mRb02;
    @BindView(R.id.rg_01)
    RadioGroup mRg01;
    @BindView(R.id.tv_bottom_02)
    TextView mTvBottom02;
    int mYear, mMonth, mDay;
    String days = "";

    String id = "";


    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        id = RxSPUtils.getString(mActivity, Const.PROJECTID);

        mTvDate02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(mActivity, onDateSetListener1, mYear, mMonth, mDay).show();
            }
        });
        mTvDate07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(mActivity, onDateSetListener2, mYear, mMonth, mDay).show();
            }
        });
        mTvDate08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(mActivity, onDateSetListener3, mYear, mMonth, mDay).show();
            }
        });

        getDatas();
    }

    private void getDatas() {
        SupervisionPlanFirstReq req = new SupervisionPlanFirstReq();
         req.setProjectId(id);
      //  req.setProjectId("5f82526c-ffae-4b4d-b63b-0d357c7db42d");
        req.setMonitorName(Const.SUPERVISIONPLANTEXT);
        Observable<BaseBeanRsp<SupervisionPlanFirstRsp>> observable = RetrofitFactory.getInstance().getMonitorInfo(req);
        observable.compose(RxSchedulers.<BaseBeanRsp<SupervisionPlanFirstRsp>>compose(
        )).subscribe(new BaseObserver<SupervisionPlanFirstRsp>() {
            @Override
            protected void onHandleSuccess(BaseBeanRsp<SupervisionPlanFirstRsp> beanRsp) {

                if (beanRsp.getProjectList().size() > 0 && beanRsp.getProjectList().get(0) != null) {
                    SupervisionPlanFirstRsp supervisionPlanFirstRsp = beanRsp.getProjectList().get(0);
                    mEt01.setText(supervisionPlanFirstRsp.getJdjhJHBH());
                    mTvDate02.setText(supervisionPlanFirstRsp.getJdjhBZRQ());
                    mEt03.setText(supervisionPlanFirstRsp.getJdjhBZR());
                    mEt04.setText(supervisionPlanFirstRsp.getJdjhAQJDBH());
                    mEt05.setText(supervisionPlanFirstRsp.getJdjhGCMC());
                    mEt06.setText(supervisionPlanFirstRsp.getJdjhGCDZ());
                    mTvDate07.setText(supervisionPlanFirstRsp.getJdjhJHKGRQ());
                    mTvDate08.setText(supervisionPlanFirstRsp.getJdjhJHJGRQ());
                    mEt09.setText(supervisionPlanFirstRsp.getJdjhJDZZ());
                    mEt10.setText(supervisionPlanFirstRsp.getJdjhJDZZZFZH());
                    mEt11.setText(supervisionPlanFirstRsp.getJdjhJDY());
                    mEt12.setText(supervisionPlanFirstRsp.getJdjhJDYZFZH());
                    mTvBottom01.setText("含有超过一定规模的危险性较大的分部分项工程情况：共有"+supervisionPlanFirstRsp.getJdjhWDGC()+"项。具体包括:");
                    mTvBottom02.setText("(共"+supervisionPlanFirstRsp.getJdjhAQSG()+"起)在施工安全监督周期内，计划对该工程进行施工安全抽查"+supervisionPlanFirstRsp.getJdjhAQCC()+"次");
                    if(supervisionPlanFirstRsp.getJdjhWFS().equals("$U_CHECKBOX_ON$")){
                        mRb01.setChecked(true);
                    }else{
                        mRb02.setChecked(false);
                    }

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
        return R.layout.fragment_supervision_plan;
    }

    /**
     * 日期选择器对话框监听
     */
    private DatePickerDialog.OnDateSetListener onDateSetListener1 = new DatePickerDialog.OnDateSetListener() {


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
            mTvDate02.setText(days);
        }
    };
    /**
     * 日期选择器对话框监听
     */
    private DatePickerDialog.OnDateSetListener onDateSetListener2 = new DatePickerDialog.OnDateSetListener() {


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
            mTvDate07.setText(days);
        }
    };
    /**
     * 日期选择器对话框监听
     */
    private DatePickerDialog.OnDateSetListener onDateSetListener3 = new DatePickerDialog.OnDateSetListener() {


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
            mTvDate08.setText(days);
        }
    };


}
