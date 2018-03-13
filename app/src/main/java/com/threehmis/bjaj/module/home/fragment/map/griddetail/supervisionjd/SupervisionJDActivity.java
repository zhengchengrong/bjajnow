package com.threehmis.bjaj.module.home.fragment.map.griddetail.supervisionjd;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.BaseObserver;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.RxSchedulers;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.SupervisionJDReq;
import com.threehmis.bjaj.api.bean.request.SupervisionPlanFirstReq;
import com.threehmis.bjaj.api.bean.request.SupervisionPlanReq;
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
    @BindView(R.id.tv_flag01)
    TextView mTvFlag01;
    @BindView(R.id.tv_flag02)
    TextView mTvFlag02;
    @BindView(R.id.tv_flag03)
    TextView mTvFlag03;
    @BindView(R.id.tv_flag04)
    TextView mTvFlag04;
    @BindView(R.id.tv_flag05)
    TextView mTvFlag05;
    @BindView(R.id.tv_flag06)
    TextView mTvFlag06;
    @BindView(R.id.tv_flag07)
    TextView mTvFlag07;
    @BindView(R.id.tv_flag08)
    TextView mTvFlag08;
    @BindView(R.id.tv_flag09)
    TextView mTvFlag09;
    @BindView(R.id.tv_flag10)
    TextView mTvFlag10;
    @BindView(R.id.tv_flag11)
    TextView mTvFlag11;
    @BindView(R.id.tv_flag12)
    TextView mTvFlag12;
    @BindView(R.id.tv_flag13)
    TextView mTvFlag13;
    @BindView(R.id.tv_flag14)
    TextView mTvFlag14;
    @BindView(R.id.tv_flag15)
    TextView mTvFlag15;
    @BindView(R.id.tv_flag16)
    TextView mTvFlag16;
    @BindView(R.id.tv_flag17)
    TextView mTvFlag17;
    @BindView(R.id.tv_flag18)
    TextView mTvFlag18;
    private String id = "";
    String code = "";
    String projectName="";
    String projectNum="";
    String registerDate="";
    String registerMan="";
    String updateDate="";
    String versionId="3HJD0035";


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
        code = RxSPUtils.getString(this, Const.PROJECTCODE);
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
        mTvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SupervisionJDReq supervisionPlanReq = new SupervisionJDReq();
                supervisionPlanReq.setProjectId(id);
                supervisionPlanReq.setProjectCode(code);
                supervisionPlanReq.setMonitorName("监督交底");
                supervisionPlanReq.setSspType("PROJECT");
                supervisionPlanReq.setMonitorPK("b1c3c335-f4ac-450e-9ffc-453bde23b9f3");
                supervisionPlanReq.setIsTrigger("$U_CHECKBOX_OFF$");
                supervisionPlanReq.setProjectNum(projectNum);
                supervisionPlanReq.setProjectName(projectName);
                supervisionPlanReq.setStatus("0");
                supervisionPlanReq.setRegisterDate(registerDate);
                supervisionPlanReq.setRegisterMan(registerMan);
                supervisionPlanReq.setVersionId("3HJD0035");
                supervisionPlanReq.setJdjdAQJDBH(projectNum);
                supervisionPlanReq.setJdjdGCMC(projectName);

                //建设单位
                supervisionPlanReq.setJdjdZDZZ(mTv02.getText().toString());
                supervisionPlanReq.setJdjdZDZY(mTv03.getText().toString());
                supervisionPlanReq.setJdjdJSDW(mTv04.getText().toString());
                supervisionPlanReq.setJdjdJSDWQYAQBMFZR(mTv05.getText().toString());
                supervisionPlanReq.setJdjdXMAQFZR(mTv07.getText().toString());
                supervisionPlanReq.setJdjdJSDWQYQTRY(mTv08.getText().toString());
                //监理单位
                supervisionPlanReq.setJdjdJLDWQTRY(mTv17.getText().toString());
                //施工总承包单位
                supervisionPlanReq.setJdjdSGZCBDW(mTv09.getText().toString());
                supervisionPlanReq.setJdjdSGZCBDWQYAQBMFZR(mTv10.getText().toString());
                supervisionPlanReq.setJdjdSGZCBDWXMJL(mTv12.getText().toString());
                supervisionPlanReq.setJdjdQTCJDWRY(mTv13.getText().toString());
                supervisionPlanReq.setJdjdZDZZ(mTv02.getText().toString());
                supervisionPlanReq.setJdjdZDZY(mTv03.getText().toString());
                supervisionPlanReq.setJdjdJDRQ(mTv01.getText().toString());
                supervisionPlanReq.setJdjdAQYQ(mEt01.getText().toString());
                Observable<BaseBeanRsp<SupervisionPlanFirstRsp>> observable = RetrofitFactory.getInstance().saveMonitorInfo(supervisionPlanReq);
                observable.compose(RxSchedulers.<BaseBeanRsp<SupervisionPlanFirstRsp>>compose(
                )).subscribe(new BaseObserver<SupervisionPlanFirstRsp>() {
                    @Override
                    protected void onHandleSuccess(BaseBeanRsp<SupervisionPlanFirstRsp> t) {
                        RxToast.showToast(t.getResult());
                        SupervisionJDActivity.this.finish();
                    }
                    @Override
                    protected void onHandleEmpty(BaseBeanRsp<SupervisionPlanFirstRsp> t) {
                        RxToast.showToast(t.getResult());
                    }
                });
            }
        });
    }

    private void getDatas() {
        SupervisionPlanFirstReq req = new SupervisionPlanFirstReq();
          req.setProjectId(id);
        req.setMonitorName(Const.SUPERVISIONPLANTEXT3);// 监督交底
        Observable<BaseBeanRsp<SupervisionJDRsp>> observable = RetrofitFactory.getInstance().getMonitorJDInfo(req);
        observable.compose(RxSchedulers.<BaseBeanRsp<SupervisionJDRsp>>compose(
        )).subscribe(new BaseObserver<SupervisionJDRsp>() {
            @Override
            protected void onHandleSuccess(BaseBeanRsp<SupervisionJDRsp> beanRsp) {
                if (beanRsp.getShowSubmit().equals("1")) {
                    //无记录
                    mTv02.setText(beanRsp.getJokerVO().getMonitorLeader());
                    mTv03.setText(beanRsp.getJokerVO().getMonitor());
                     projectName=beanRsp.getJokerVO().getProjectName();
                     projectNum=beanRsp.getJokerVO().getProjectNum();

                    for (int i = 0; i < beanRsp.getJokerVO().getPersonList().size(); i++) {
                        BaseBeanRsp<SupervisionJDRsp>.JokerVOBean.MonitorListBean supervisionJDRsp = beanRsp.getJokerVO().getPersonList().get(i);
                        if(supervisionJDRsp!=null) {
                            if (supervisionJDRsp.getUnitType().equals("建设单位")) {
                                mTv04.setText(supervisionJDRsp.getUnitName());
                                if (supervisionJDRsp.getPersonDuty().equals("企业法定代表人")) {
                                    mTv05.setText(supervisionJDRsp.getPersonName());
                                }
                                if (supervisionJDRsp.getPersonDuty().equals("项目负责人")) {
                                    mTv07.setText(supervisionJDRsp.getPersonName());
                                }
                            }
                        }
                    }
                    for (int i = 0; i < beanRsp.getJokerVO().getPersonList().size(); i++) {
                        BaseBeanRsp<SupervisionJDRsp>.JokerVOBean.MonitorListBean supervisionJDRsp = beanRsp.getJokerVO().getPersonList().get(i);
                        if(supervisionJDRsp!=null) {
                            if (supervisionJDRsp.getUnitType().equals("施工总承包单位")) {
                                mTv09.setText(supervisionJDRsp.getUnitName());
                                if (supervisionJDRsp.getPersonDuty().equals("企业法定代表人")) {
                                    mTv10.setText(supervisionJDRsp.getPersonName());
                                }
                                if (supervisionJDRsp.getPersonDuty().equals("项目安全负责人")) {
                                    mTv12.setText(supervisionJDRsp.getPersonName());
                                }
                            }
                        }
                    }
                }else{
                    mTvCommit.setVisibility(View.GONE);
                    mTv01.setText(beanRsp.getProjectList().get(0).getJdjdJDRQ());
                    mTv02.setText(beanRsp.getProjectList().get(0).getJdjdZDZZ());
                    mTv03.setText(beanRsp.getProjectList().get(0).getJdjdZDZY());

                    mTv04.setText(beanRsp.getProjectList().get(0).getJdjdJSDW());
                    mTv05.setText(beanRsp.getProjectList().get(0).getJdjdJSDWQYAQBMFZR());
                    mTv06.setText(beanRsp.getProjectList().get(0).getJdjdJSDWXMFZR());
                    mTv07.setText(beanRsp.getProjectList().get(0).getJdjdJSDWQYXMAQFZR());
                    mTv08.setText(beanRsp.getProjectList().get(0).getJdjdJSDWQYQTRY());

                    mTv09.setText(beanRsp.getProjectList().get(0).getJdjdSGZCBDW());
                    mTv10.setText(beanRsp.getProjectList().get(0).getJdjdSGZCBDWQYAQBMFZR());
                    mTv11.setText(beanRsp.getProjectList().get(0).getJdjdSGZCBDWXMJL());
                    mTv12.setText(beanRsp.getProjectList().get(0).getJdjdXMAQFZR());
                    mTv13.setText(beanRsp.getProjectList().get(0).getJdjdSGZCBDWQTRY());


                    mTv14.setText(beanRsp.getProjectList().get(0).getJdjdJLDW());
                    mTv15.setText(beanRsp.getProjectList().get(0).getJdjdJLDWXMZJLGCS());
                    mTv16.setText(beanRsp.getProjectList().get(0).getJdjdJLDWQYAQBMFZR());
                    mTv17.setText(beanRsp.getProjectList().get(0).getJdjdJLDWQTRY());
                    mEt01.setText(beanRsp.getProjectList().get(0).getJdjdAQYQ());

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
    String days = "";
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
