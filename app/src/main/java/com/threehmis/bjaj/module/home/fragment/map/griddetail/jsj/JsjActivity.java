package com.threehmis.bjaj.module.home.fragment.map.griddetail.jsj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.BaseObserver;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.RxSchedulers;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.JsjBeanReq;
import com.threehmis.bjaj.api.bean.respon.JsjBeanRsp;
import com.threehmis.bjaj.module.base.BaseActivity;
import com.threehmis.bjaj.widget.EmptyLayout;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * Created by llz on 2018/2/28.
 */

public class JsjActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.other)
    TextView mOther;
    @BindView(R.id.tv_back)
    TextView mTvBack;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    ArrayList<JsjBeanRsp> mQzjxBeanRsps = new ArrayList<JsjBeanRsp>();
    @BindView(R.id.tv_01)
    TextView mTv01;
    @BindView(R.id.et_01)
    EditText mEt01;
    @BindView(R.id.tv_search)
    ImageView mTvSearch;
    private String sgxkzh = "";
    private String cpmc = "";
    BaseQuickAdapter mBaseQuickAdapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_jsj;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initViews() {
        sgxkzh = getIntent().getStringExtra(Const.SGXKZH);
        initTitle();
        mRvContent.setLayoutManager(new LinearLayoutManager(this));
        mBaseQuickAdapter = new BaseQuickAdapter<JsjBeanRsp, BaseViewHolder>(R.layout.item_jsj, mQzjxBeanRsps) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, final JsjBeanRsp rowsBean) {
                baseViewHolder.setText(R.id.tv_01, rowsBean.getDtgcmc());
                baseViewHolder.setText(R.id.tv_02, rowsBean.getCpmc());
                baseViewHolder.setText(R.id.tv_03, rowsBean.getCpxh());
                baseViewHolder.setText(R.id.tv_04, rowsBean.getJcsj());
                baseViewHolder.setText(R.id.tv_05, rowsBean.getJcjl());
                baseViewHolder.setText(R.id.tv_06, rowsBean.getSfycc());
                baseViewHolder.getView(R.id.ll_jsj).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(JsjActivity.this,JsjDetailActivity.class);
                        intent.putExtra(Const.JSJ,rowsBean);
                        startActivity(intent);
                    }
                });
            }
        };
        mRvContent.setAdapter(mBaseQuickAdapter);
        getDate();
    }

    private void getDate() {
        showLoading();
        JsjBeanReq req = new JsjBeanReq();
        req.setSgxkzh(sgxkzh);
        req.setCpmc(cpmc);
    /*  req.setSgxkzh("2011-074");
        req.setCpmc("动轨");*/
        //req.setSgxkzh("[2013]施建字0330号");
        Observable<BaseBeanRsp<JsjBeanRsp>> observable = RetrofitFactory.getInstance().getJsj(req);
        observable.compose(RxSchedulers.<BaseBeanRsp<JsjBeanRsp>>compose(
        )).subscribe(new BaseObserver<JsjBeanRsp>() {
            @Override
            protected void onHandleSuccess(BaseBeanRsp<JsjBeanRsp> qzjxBean) {
                if (qzjxBean.getProjectList().size() > 0) {
                    mEmptyLayout.hide();
                    mQzjxBeanRsps.clear();
                    mQzjxBeanRsps.addAll(qzjxBean.getProjectList());
                    mBaseQuickAdapter.notifyDataSetChanged();
                } else {
                    showEmpty();
                }
            }

            @Override
            protected void onHandleEmpty(BaseBeanRsp<JsjBeanRsp> t) {
                RxToast.showToast(t.getResult());
                showEmpty();
            }
        });
        mTvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(mEt01.getText().toString())){
                    RxToast.showToast("请输入搜索内容");
                }else{
                    cpmc = mEt01.getText().toString();
                    getDate();
                }
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    private void initTitle() {
        mTvTitle.setText("起重机械");
        mTvBack.setVisibility(View.VISIBLE);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
