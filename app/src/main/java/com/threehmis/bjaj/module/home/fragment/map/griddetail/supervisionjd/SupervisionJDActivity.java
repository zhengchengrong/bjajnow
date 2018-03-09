package com.threehmis.bjaj.module.home.fragment.map.griddetail.supervisionjd;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.BaseObserver;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.RxSchedulers;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.SupervisionPlanFirstReq;
import com.threehmis.bjaj.api.bean.respon.SupervisionJDRsp;
import com.threehmis.bjaj.api.bean.respon.SupervisionPlanFirstRsp;
import com.threehmis.bjaj.module.base.BaseActivity;
import com.vondear.rxtools.RxSPUtils;
import com.vondear.rxtools.view.RxToast;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * Created by llz on 2018/3/6.
 */

public class SupervisionJDActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.other)
    TextView mOther;
    @BindView(R.id.tv_back)
    TextView mTvBack;
    @BindView(R.id.tv_01)
    TextView mTv01;
    @BindView(R.id.tv_02)
    EditText mTv02;
    @BindView(R.id.tv_03)
    EditText mTv03;
    @BindView(R.id.tv_04)
    EditText mTv04;
    @BindView(R.id.tv_05)
    EditText mTv05;
    @BindView(R.id.tv_06)
    EditText mTv06;
    @BindView(R.id.tv_07)
    EditText mTv07;
    @BindView(R.id.tv_08)
    EditText mTv08;
    @BindView(R.id.tv_09)
    EditText mTv09;
    @BindView(R.id.tv_10)
    EditText mTv10;
    @BindView(R.id.tv_11)
    EditText mTv11;
    @BindView(R.id.tv_12)
    EditText mTv12;
    @BindView(R.id.tv_13)
    EditText mTv13;
    @BindView(R.id.tv_14)
    EditText mTv14;
    @BindView(R.id.tv_15)
    EditText mTv15;
    @BindView(R.id.tv_16)
    EditText mTv16;
    @BindView(R.id.tv_17)
    EditText mTv17;
    @BindView(R.id.et_01)
    EditText mEt01;
    @BindView(R.id.tv_commit)
    TextView mTvCommit;

    private String id="";
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_supervison_jd;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        id = RxSPUtils.getString(this, Const.PROJECTID);
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        mTv01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(SupervisionJDActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
            }
        });
        initTitle();
        getDatas();
    }
    private void getDatas() {

        SupervisionPlanFirstReq req = new SupervisionPlanFirstReq();
    /*   req.setProjectId(id);*/
        req.setProjectId("5f82526c-ffae-4b4d-b63b-0d357c7db42d");
        req.setMonitorName(Const.SUPERVISIONPLANTEXT3);// 监督交底
        Observable<BaseBeanRsp<SupervisionJDRsp>> observable = RetrofitFactory.getInstance().getMonitorJDInfo(req);
        observable.compose(RxSchedulers.<BaseBeanRsp<SupervisionJDRsp>>compose(
        )).subscribe(new BaseObserver<SupervisionJDRsp>() {
            @Override
            protected void onHandleSuccess(BaseBeanRsp<SupervisionJDRsp> beanRsp) {
              if(beanRsp.getShowSubmit().equals("1")){
                  //无记录


              }

            }
            @Override
            protected void onHandleEmpty(BaseBeanRsp<SupervisionJDRsp> t) {
                RxToast.showToast(t.getResult());
            }
        });
    }
    @Override
    protected void updateViews(boolean isRefresh) {

    }

    private void initTitle() {
        mTvTitle.setText("施工安全监督交底记录");
        mTvBack.setVisibility(View.VISIBLE);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    int mYear, mMonth, mDay;
    String days="";
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
            mTv01.setText(days);
        }
    };
}
