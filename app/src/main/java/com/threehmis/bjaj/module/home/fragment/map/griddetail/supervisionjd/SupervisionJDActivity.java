package com.threehmis.bjaj.module.home.fragment.map.griddetail.supervisionjd;

import android.os.Bundle;
import android.view.View;
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
import com.threehmis.bjaj.api.bean.respon.SupervisionPlanFirstRsp;
import com.threehmis.bjaj.module.base.BaseActivity;
import com.vondear.rxtools.RxSPUtils;
import com.vondear.rxtools.view.RxToast;

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
    EditText mTv01;
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
    @BindView(R.id.iv_photo)
    ImageView mIvPhoto;
    @BindView(R.id.iv_add)
    ImageView mIvAdd;
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
        initTitle();
        getDatas();
    }
    private void getDatas() {

        SupervisionPlanFirstReq req = new SupervisionPlanFirstReq();
       req.setProjectId(id);
      /*  req.setProjectId("5f82526c-ffae-4b4d-b63b-0d357c7db42d");*/
        req.setMonitorName(Const.SUPERVISIONPLANTEXT3);// 监督交底
        Observable<BaseBeanRsp<SupervisionPlanFirstRsp>> observable = RetrofitFactory.getInstance().getMonitorInfo(req);
        observable.compose(RxSchedulers.<BaseBeanRsp<SupervisionPlanFirstRsp>>compose(
        )).subscribe(new BaseObserver<SupervisionPlanFirstRsp>() {
            @Override
            protected void onHandleSuccess(BaseBeanRsp<SupervisionPlanFirstRsp> beanRsp) {
                if (beanRsp.getProjectList()!=null&&beanRsp.getProjectList().size() > 0) {
                    SupervisionPlanFirstRsp rsp = beanRsp.getProjectList().get(0);
                        mTv01.setText(rsp.getJdjdJDRQ());
                        mTv02.setText(rsp.getJdjhJDZZ());
                        mTv03.setText(rsp.getJdjhJDY());

                        mTv04.setText(rsp.getJdjdJSDW());
                        mTv05.setText(rsp.getJdjdJSDWQYAQBMFZR());
                        mTv06.setText(rsp.getJdjdJSDWXMFZR());
                        mTv07.setText(rsp.getJdjdJSDWQYXMAQFZR());
                        mTv08.setText(rsp.getJdjdJSDWQYQTRY());

                        mTv09.setText(rsp.getJdjdSGZCBDW());
                        mTv10.setText(rsp.getJdjdSGZCBDWQYAQBMFZR());
                        mTv11.setText(rsp.getJdjdSGZCBDWXMJL());
                        mTv12.setText(rsp.getJdjdXMAQFZR());
                        mTv13.setText(rsp.getJdjdSGZCBDWQTRY());

                        mTv14.setText(rsp.getJdjdJLDW());
                        mTv15.setText(rsp.getJdjdJLDWXMZJLGCS());
                        mTv16.setText(rsp.getJdjdJLDWXMAQJLGCS());
                        mTv17.setText(rsp.getJdjdJLDWQTRY());


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
}
