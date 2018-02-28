package com.threehmis.bjaj.module.home.fragment.map.griddetail.safecomment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.BaseObserver;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.RxSchedulers;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.SafeCommentBeanReq;
import com.threehmis.bjaj.api.bean.respon.SafeCommentRsp;
import com.threehmis.bjaj.module.base.BaseActivity;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * Created by llz on 2018/2/28.
 */

public class SafeCommentActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.other)
    TextView mOther;
    @BindView(R.id.tv_back)
    TextView mTvBack;
    @BindView(R.id.tv_01)
    TextView mTv01;
    @BindView(R.id.tv_02)
    TextView mTv02;
    @BindView(R.id.tv_03)
    TextView mTv03;
    @BindView(R.id.tv_04)
    TextView mTv04;
    @BindView(R.id.tv_05)
    TextView mTv05;
    @BindView(R.id.tv_06)
    TextView mTv06;

    private String projectCode="";

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_save_comment;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
      projectCode = getIntent().getStringExtra(Const.PROJECTCODE);
      initTitle();
      getData();

    }

    private void getData() {
        SafeCommentBeanReq req = new SafeCommentBeanReq();
        req.setProjectCode(projectCode);
        Observable<BaseBeanRsp<SafeCommentRsp>> observable = RetrofitFactory.getInstance().getAP(req);
        observable.compose(RxSchedulers.<BaseBeanRsp<SafeCommentRsp>>compose(
        )).subscribe(new BaseObserver<SafeCommentRsp>() {
            @Override
            protected void onHandleSuccess(BaseBeanRsp<SafeCommentRsp> rspBaseBeanRsp) {
                if(rspBaseBeanRsp.getProjectList()!=null&& rspBaseBeanRsp.getProjectList().size()>0) {
                    mTv01.setText(rspBaseBeanRsp.getProjectList().get(0).getSgxkz() == null ? "" : rspBaseBeanRsp.getProjectList().get(0).getSgxkz());
                    mTv02.setText(rspBaseBeanRsp.getProjectList().get(0).getSgxkznew() == null ? "" : rspBaseBeanRsp.getProjectList().get(0).getSgxkznew());
                    mTv03.setText(rspBaseBeanRsp.getProjectList().get(0).getProjectName() == null ? "" : rspBaseBeanRsp.getProjectList().get(0).getProjectName());
                    mTv04.setText(rspBaseBeanRsp.getProjectList().get(0).getSgdw() == null ? "" : rspBaseBeanRsp.getProjectList().get(0).getSgdw());
                    mTv05.setText(rspBaseBeanRsp.getProjectList().get(0).getEvaResult() == null ? "" : rspBaseBeanRsp.getProjectList().get(0).getEvaResult());
                    mTv06.setText(rspBaseBeanRsp.getProjectList().get(0).getEvaTime() == null ? "" : rspBaseBeanRsp.getProjectList().get(0).getEvaTime());
                }
            }
            @Override
            protected void onHandleEmpty(BaseBeanRsp<SafeCommentRsp> t) {
                RxToast.showToast(t.getResult());
            }
        });
    }

    private void initTitle() {
        mTvTitle.setText("安评信息");
        mTvBack.setVisibility(View.VISIBLE);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }


}
