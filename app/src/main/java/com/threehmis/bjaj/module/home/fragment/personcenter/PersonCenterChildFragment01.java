package com.threehmis.bjaj.module.home.fragment.personcenter;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.BaseObserver;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.RxSchedulers;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.GetLoginListReq;
import com.threehmis.bjaj.api.bean.respon.GetLoginListRsp;
import com.threehmis.bjaj.module.base.BaseFragment;
import com.vondear.rxtools.RxSPUtils;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;

/**
 * Created by llz on 2018/3/6.
 */

public class PersonCenterChildFragment01 extends BaseFragment {
    @BindView(R.id.tv_flag01)
    TextView mTvFlag01;
    @BindView(R.id.et_01)
    EditText mEt01;
    @BindView(R.id.tv_flag02)
    TextView mTvFlag02;
    @BindView(R.id.et_02)
    EditText mEt02;
    @BindView(R.id.tv_flag03)
    TextView mTvFlag03;
    @BindView(R.id.et_03)
    EditText mEt03;
    @BindView(R.id.tv_flag04)
    TextView mTvFlag04;
    @BindView(R.id.et_04)
    EditText mEt04;
    @BindView(R.id.tv_flag05)
    TextView mTvFlag05;
    @BindView(R.id.et_05)
    EditText mEt05;
    @BindView(R.id.tv_flag06)
    TextView mTvFlag06;
    @BindView(R.id.et_06)
    EditText mEt06;
    @BindView(R.id.tv_flag07)
    TextView mTvFlag07;
    @BindView(R.id.et_07)
    EditText mEt07;
    @BindView(R.id.tv_flag08)
    TextView mTvFlag08;
    @BindView(R.id.et_08)
    EditText mEt08;
    @BindView(R.id.tv_flag09)
    TextView mTvFlag09;
    @BindView(R.id.et_09)
    EditText mEt09;
    @BindView(R.id.tv_flag10)
    TextView mTvFlag10;
    @BindView(R.id.et_10)
    EditText mEt10;
    @BindView(R.id.tv_flag11)
    TextView mTvFlag11;
    @BindView(R.id.et_11)
    EditText mEt11;
    Unbinder unbinder;
    @BindView(R.id.tv_edit01)
    TextView mTvEdit01;
    @BindView(R.id.tv_edit02)
    TextView mTvEdit02;

    private String phoneNum = "";
    private String password = "";

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        phoneNum = RxSPUtils.getString(mActivity, Const.PHONENUM);
        password = RxSPUtils.getString(mActivity, Const.PASSWORD);
        mTvEdit01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //修改密码
                if(TextUtils.isEmpty(mEt02.getText().toString())){
                    RxToast.showToast("请输入要修改的密码");
                    return;
                }
                GetLoginListReq req = new GetLoginListReq();
                req.setUserId(AndroidApplication.getInstance().getuserId());
                req.setPassword(mEt02.getText().toString());

                Observable<BaseBeanRsp<GetLoginListRsp>> observable = RetrofitFactory.getInstance().modifyPassword(req);
                observable.compose(RxSchedulers.<BaseBeanRsp<GetLoginListRsp>>compose(
                )).subscribe(new BaseObserver<GetLoginListRsp>() {
                    @Override
                    protected void onHandleSuccess(BaseBeanRsp<GetLoginListRsp> userEntity) {
                        RxToast.showToast(userEntity.getResult());
                    }

                    @Override
                    protected void onHandleEmpty(BaseBeanRsp<GetLoginListRsp> userEntity) {
                        RxToast.showToast(userEntity.getResult());
                    }
                });
            }
        });
        mTvEdit02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //修改密码
                //修改密码
                if(TextUtils.isEmpty(mEt11.getText().toString())){
                    RxToast.showToast("请输入要修改的密码");
                    return;
                }
                GetLoginListReq req = new GetLoginListReq();
                req.setPersonId(AndroidApplication.getInstance().getpersonId());
                req.setMobilePhone(mEt11.getText().toString());

                Observable<BaseBeanRsp<GetLoginListRsp>> observable = RetrofitFactory.getInstance().modifyPhone(req);
                observable.compose(RxSchedulers.<BaseBeanRsp<GetLoginListRsp>>compose(
                )).subscribe(new BaseObserver<GetLoginListRsp>() {
                    @Override
                    protected void onHandleSuccess(BaseBeanRsp<GetLoginListRsp> userEntity) {
                        RxToast.showToast(userEntity.getResult());
                    }

                    @Override
                    protected void onHandleEmpty(BaseBeanRsp<GetLoginListRsp> userEntity) {
                        RxToast.showToast(userEntity.getResult());
                    }
                });
            }
        });
        GetLoginListReq req = new GetLoginListReq();
        req.setUsername(phoneNum);
        req.setPassword(password);
        Observable<BaseBeanRsp<GetLoginListRsp>> observable = RetrofitFactory.getInstance().getUserInfo(req);
        observable.compose(RxSchedulers.<BaseBeanRsp<GetLoginListRsp>>compose(
        )).subscribe(new BaseObserver<GetLoginListRsp>() {
            @Override
            protected void onHandleSuccess(BaseBeanRsp<GetLoginListRsp> userEntity) {
                if (userEntity != null) {
                    mEt01.setText(userEntity.getJokerVO().getUsername());
                    // mEt02.setText(userEntity.getJokerVO().getPassword());
                    mEt03.setText(userEntity.getJokerVO().getUpdateDate());

                    mEt04.setText(userEntity.getJokerVO().getPersonName());
                    mEt05.setText(userEntity.getJokerVO().getDeptName());
                    mEt06.setText(userEntity.getJokerVO().getTitleNo());
                    mEt07.setText(userEntity.getJokerVO().getProfession());
                    mEt08.setText(userEntity.getJokerVO().getDuty());
                    mEt09.setText(userEntity.getJokerVO().getGrade());
                    mEt11.setText(userEntity.getJokerVO().getMobilePhone());

                }
            }

            @Override
            protected void onHandleEmpty(BaseBeanRsp<GetLoginListRsp> t) {
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_person_center_child_01;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
