package com.threehmis.bjaj.module.home.fragment.map.griddetail.supervisionplan;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.threehmis.bjaj.api.bean.request.SupervisionPlanReq;
import com.threehmis.bjaj.api.bean.respon.SupervisionPlanFirstRsp;
import com.threehmis.bjaj.module.base.BaseFragment;
import com.threehmis.bjaj.utils.SPUtils;
import com.vondear.rxtools.RxSPUtils;
import com.vondear.rxtools.view.RxToast;

import java.util.Calendar;

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


    @BindView(R.id.rb_01)
    RadioButton mRb01;
    @BindView(R.id.rb_02)
    RadioButton mRb02;
    @BindView(R.id.rg_01)
    RadioGroup mRg01;

    int mYear, mMonth, mDay;
    String days = "";

    String id = "";
    String code = "";

    @BindView(R.id.tv_bottom_01)
    TextView mTvBottom01;
    @BindView(R.id.et_bottom_01)
    EditText mEtBottom01;
    @BindView(R.id.tv_flag06)
    TextView mTvFlag06;
    @BindView(R.id.tv_flag02)
    TextView mTvFlag02;
    @BindView(R.id.et_bottom_02)
    EditText mEtBottom02;
    @BindView(R.id.tv_bottom_02)
    TextView mTvBottom02;
    @BindView(R.id.et_bottom_03)
    EditText mEtBottom03;
    @BindView(R.id.tv_commit)
    TextView mTvCommit;
    @BindView(R.id.ll_add_person)
    LinearLayout mLlAddPerson;

    private String pk = "";


    private StringBuffer jdzz = new StringBuffer();
    private StringBuffer jdzzno = new StringBuffer();

    private StringBuffer jdy = new StringBuffer();
    private StringBuffer jdyno = new StringBuffer();


    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        id = RxSPUtils.getString(mActivity, Const.PROJECTID);
        code = RxSPUtils.getString(mActivity, Const.PROJECTCODE);
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        mEt03.setText(RxSPUtils.getString(mActivity,Const.PHONENUM));
        mTvDate02.setText(mYear+"-"+(mMonth+1)+"-"+mDay);
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

        mTvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SupervisionPlanReq supervisionPlanReq = new SupervisionPlanReq();
                supervisionPlanReq.setPk(pk);
                supervisionPlanReq.setProjectId(id);
                supervisionPlanReq.setProjectCode(code);
                supervisionPlanReq.setJdjhJHBH(mEt01.getText().toString());//计划编号
                supervisionPlanReq.setJdjhBZRQ(mTvDate02.getText().toString());
                supervisionPlanReq.setJdjhBZR(mEt03.getText().toString());
                supervisionPlanReq.setJdjhAQJDBH(mEt04.getText().toString());
                supervisionPlanReq.setStatus("0");
                supervisionPlanReq.setJdjhGCMC(mEt05.getText().toString());
                supervisionPlanReq.setJdjhGCDZ(mEt06.getText().toString());
                supervisionPlanReq.setJdjhJHKGRQ(mTvDate07.getText().toString());
                supervisionPlanReq.setJdjhJHJGRQ(mTvDate08.getText().toString());
                supervisionPlanReq.setJdjhWDGC(mEtBottom01.getText().toString());
                supervisionPlanReq.setJdjhAQSG(mEtBottom02.getText().toString());
                supervisionPlanReq.setJdjhAQCC(mEtBottom03.getText().toString());
                supervisionPlanReq.setVersionId(Const.VERSIONID);
                supervisionPlanReq.setProjectNum(mEt04.getText().toString());
                supervisionPlanReq.setMonitorName("监督计划");
                supervisionPlanReq.setSspType("PROJECT");
                supervisionPlanReq.setProjectCode(mEt04.getText().toString());
                supervisionPlanReq.setProjectName(mEt05.getText().toString());
                supervisionPlanReq.setMonitorPK("b1c3c335-f4ac-450e-9ffc-453bde23b9f3");
                supervisionPlanReq.setIsTrigger("$U_CHECKBOX_OFF$");
                supervisionPlanReq.setJdjhJDZZ("监督组长"+jdzz.toString().substring(0,jdzz.length()-1));
                supervisionPlanReq.setJdjhJDZZZFZH(jdzzno.toString().substring(0,jdzz.length()-1));
                supervisionPlanReq.setJdjhJDY(jdy.toString().substring(0,jdzz.length()-1));
                supervisionPlanReq.setJdjhJDYZFZH(jdyno.toString().substring(0,jdzz.length()-1));
                supervisionPlanReq.setRegisterDate(mTvDate02.getText().toString());
                supervisionPlanReq.setRegisterMan(RxSPUtils.getContent(mActivity,Const.PHONENUM));
                if (mRb01.isChecked()) {
                    supervisionPlanReq.setJdjhWFS("$U_CHECKBOX_ON$");
                } else if (mRb02.isChecked()) {
                    supervisionPlanReq.setJdjhFSG("$U_CHECKBOX_ON$");
                }

                Observable<BaseBeanRsp<SupervisionPlanFirstRsp>> observable = RetrofitFactory.getInstance().saveMonitorInfo(supervisionPlanReq);
                observable.compose(RxSchedulers.<BaseBeanRsp<SupervisionPlanFirstRsp>>compose(
                )).subscribe(new BaseObserver<SupervisionPlanFirstRsp>() {
                    @Override
                    protected void onHandleSuccess(BaseBeanRsp<SupervisionPlanFirstRsp> t) {
                        RxToast.showToast(t.getResult());
                    }
                    @Override
                    protected void onHandleEmpty(BaseBeanRsp<SupervisionPlanFirstRsp> t) {
                        RxToast.showToast(t.getResult());
                    }
                });
            }
        });

        getDatas();
    }


    private void getDatas() {
        final SupervisionPlanFirstReq req = new SupervisionPlanFirstReq();
        req.setProjectId(id);
      /*  req.setProjectId("5f82526c-ffae-4b4d-b63b-0d357c7db42d");*/
        req.setMonitorName(Const.SUPERVISIONPLANTEXT);
        Observable<BaseBeanRsp<SupervisionPlanFirstRsp>> observable = RetrofitFactory.getInstance().getMonitorInfo(req);
        observable.compose(RxSchedulers.<BaseBeanRsp<SupervisionPlanFirstRsp>>compose(
        )).subscribe(new BaseObserver<SupervisionPlanFirstRsp>() {
            @Override
            protected void onHandleSuccess(BaseBeanRsp<SupervisionPlanFirstRsp> beanRsp) {

                if (beanRsp.getShowSubmit().equals("1")) {
                    mTvCommit.setVisibility(View.VISIBLE);
                    //未找到监督计划
                    BaseBeanRsp.JokerVOBean jokerVOBean = beanRsp.getJokerVO();
                    mEt04.setText(jokerVOBean.getProjectNum());
                    mEt05.setText(jokerVOBean.getProjectName());
                    mEt06.setText(jokerVOBean.getAddress());
                    mTvDate07.setText(jokerVOBean.getBeginDate());
                    mTvDate08.setText(jokerVOBean.getEndDate());
                    for(int i=0;i<jokerVOBean.getPersonList().size();i++){
                        BaseBeanRsp.JokerVOBean.MonitorListBean monitorListBean = beanRsp.getJokerVO().getPersonList().get(i);
                        if(monitorListBean.getMonitorType().equals("监督组长")){
                        View view = mActivity.getLayoutInflater().inflate(R.layout.supervision_plan_01,null);
                        ((EditText)view.findViewById(R.id.et_01)).setText(monitorListBean.getMonitorName());
                         ((TextView)view.findViewById(R.id.tv_01)).setText(monitorListBean.getMonitorType());
                        if(!TextUtils.isEmpty(monitorListBean.getMonitorTitleNo())){
                            ((EditText)view.findViewById(R.id.et_02)).setText(monitorListBean.getMonitorTitleNo());
                        }
                        jdzz.append(monitorListBean.getMonitorName()+",");
                        jdzzno.append(monitorListBean.getMonitorTitleNo()+",");
                            mLlAddPerson.addView(view);
                        }
                    }
                    for(int i=0;i<jokerVOBean.getPersonList().size();i++){
                        BaseBeanRsp.JokerVOBean.MonitorListBean monitorListBean = beanRsp.getJokerVO().getPersonList().get(i);
                        if(monitorListBean.getMonitorType().equals("监督组员")){
                            View view2 = mActivity.getLayoutInflater().inflate(R.layout.supervision_plan_01,null);
                            ((EditText)view2.findViewById(R.id.et_01)).setText(monitorListBean.getMonitorName());
                            ((TextView)view2.findViewById(R.id.tv_01)).setText(monitorListBean.getMonitorType());
                            if(!TextUtils.isEmpty(monitorListBean.getMonitorTitleNo())){
                                ((EditText)view2.findViewById(R.id.et_02)).setText(monitorListBean.getMonitorTitleNo());
                            }
                            jdy.append(monitorListBean.getMonitorName()+",");
                            jdyno.append(monitorListBean.getMonitorTitleNo()+",");
                            mLlAddPerson.addView(view2);
                        }
                    }
                }else{
                    mTvCommit.setVisibility(View.GONE);
                    if(beanRsp.getProjectList()!=null && beanRsp.getProjectList().size()>0){
                        SupervisionPlanFirstRsp supervisionPlanFirstRsp = beanRsp.getProjectList().get(0);
                        mEt01.setText(supervisionPlanFirstRsp.getJdjhJHBH()==null?"":supervisionPlanFirstRsp.getJdjhJHBH());
                        mTvDate02.setText(supervisionPlanFirstRsp.getJdjhBZRQ()==null?"":supervisionPlanFirstRsp.getJdjhBZRQ());
                        mEt03.setText(supervisionPlanFirstRsp.getJdjhBZR()==null?"":supervisionPlanFirstRsp.getJdjhBZR());
                        mEt04.setText(supervisionPlanFirstRsp.getJdjhAQJDBH()==null?"":supervisionPlanFirstRsp.getJdjhAQJDBH());
                        mEt05.setText(supervisionPlanFirstRsp.getJdjhGCMC()==null?"":supervisionPlanFirstRsp.getJdjhGCMC());
                        mEt06.setText(supervisionPlanFirstRsp.getJdjhGCDZ()==null?"":supervisionPlanFirstRsp.getJdjhGCDZ());
                        mTvDate07.setText(supervisionPlanFirstRsp.getJdjhJHKGRQ()==null?"":supervisionPlanFirstRsp.getJdjhJHKGRQ());
                        mTvDate08.setText(supervisionPlanFirstRsp.getJdjhJHJGRQ()==null?"":supervisionPlanFirstRsp.getJdjhJHJGRQ());
                        mEtBottom01.setText(supervisionPlanFirstRsp.getJdjhWDGC());
                        mEtBottom02.setText(supervisionPlanFirstRsp.getJdjhAQSG());
                        mEtBottom03.setText(supervisionPlanFirstRsp.getJdjhAQCC());
                        if(supervisionPlanFirstRsp.getJdjhWFS().equals("$U_CHECKBOX_ON$")){
                            mRb01.setChecked(true);
                        }else{
                            mRb02.setChecked(true);
                        }

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
