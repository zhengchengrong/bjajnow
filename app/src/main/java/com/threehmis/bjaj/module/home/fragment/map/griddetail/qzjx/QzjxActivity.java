package com.threehmis.bjaj.module.home.fragment.map.griddetail.qzjx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.threehmis.bjaj.api.bean.request.QzjxBeanReq;
import com.threehmis.bjaj.api.bean.respon.QzjxBeanRsp;
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

public class QzjxActivity extends BaseActivity {
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
    ArrayList<QzjxBeanRsp> mQzjxBeanRsps = new ArrayList<QzjxBeanRsp>();
    @BindView(R.id.tv_01)
    TextView mTv01;
    @BindView(R.id.et_01)
    EditText mEt01;
    @BindView(R.id.tv_search)
    ImageView mTvSearch;
    private String sgxkzh;
    BaseQuickAdapter mBaseQuickAdapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_qzjx;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initViews() {
        sgxkzh = getIntent().getStringExtra(Const.SGXKZH);
        initTitle();
        mRvContent.setLayoutManager(new LinearLayoutManager(this));
        mBaseQuickAdapter = new BaseQuickAdapter<QzjxBeanRsp, BaseViewHolder>(R.layout.item_qzjx, mQzjxBeanRsps) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, QzjxBeanRsp rowsBean) {
                baseViewHolder.setText(R.id.tv_01, rowsBean.getSbdjbh());
                baseViewHolder.setText(R.id.tv_02, rowsBean.getSblx());
                baseViewHolder.setText(R.id.tv_03, rowsBean.getSbxh());
                baseViewHolder.setText(R.id.tv_04, rowsBean.getSfyzx());
                baseViewHolder.getView(R.id.ll_qzjx).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            Intent intent = new Intent(QzjxActivity.this,QzjxDetailActivity.class);
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
        QzjxBeanReq req = new QzjxBeanReq();
        //req.setSgxkzh(sgxkzh);
        req.setSgxkzh("[2013]施建字0330号");
        Observable<BaseBeanRsp<QzjxBeanRsp>> observable = RetrofitFactory.getInstance().getQZJX(req);
        observable.compose(RxSchedulers.<BaseBeanRsp<QzjxBeanRsp>>compose(
        )).subscribe(new BaseObserver<QzjxBeanRsp>() {
            @Override
            protected void onHandleSuccess(BaseBeanRsp<QzjxBeanRsp> qzjxBean) {
                if (qzjxBean.getProjectList().size() > 0) {
                    mEmptyLayout.hide();
                    mQzjxBeanRsps.addAll(qzjxBean.getProjectList());
                    mBaseQuickAdapter.notifyDataSetChanged();
                } else {
                    showEmpty();
                }
            }

            @Override
            protected void onHandleEmpty(BaseBeanRsp<QzjxBeanRsp> t) {
                RxToast.showToast(t.getResult());
                showEmpty();
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
