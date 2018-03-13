package com.threehmis.bjaj.module.home.fragment.notice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.bean.respon.ProjectStatusRsp;
import com.threehmis.bjaj.api.bean.respon.WillDoRsp;
import com.threehmis.bjaj.module.base.BaseFragment;
import com.threehmis.bjaj.module.home.fragment.notice.activity.WillDoSafeJDGZSHActivity;
import com.threehmis.bjaj.module.home.fragment.notice.activity.WillDoSafeJDJHSHActivity;
import com.threehmis.bjaj.module.home.fragment.notice.activity.WillDoSafeJDSXSQActivity;
import com.threehmis.bjaj.module.home.fragment.notice.activity.WillDoSafeZZSGJDActivity;
import com.threehmis.bjaj.widget.EmptyLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class WilldoFragment extends BaseFragment {

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

    ArrayList<WillDoRsp> mWillDoRsps;
    BaseQuickAdapter mBaseQuickAdapter;
    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initTitle();
        mRvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        mWillDoRsps = new ArrayList<WillDoRsp>();
        WillDoRsp willDoRsp = new WillDoRsp();
        willDoRsp.setDate("2018-09-12");
        willDoRsp.setTitle("安全监督手续申请审核");
        willDoRsp.setType("0");
        mWillDoRsps.add(willDoRsp);
        WillDoRsp willDoRsp2 = new WillDoRsp();
        willDoRsp2.setDate("2018-09-12");
        willDoRsp2.setTitle("中止施工安全监督告知审核");
        willDoRsp2.setType("1");
        mWillDoRsps.add(willDoRsp2);
        WillDoRsp willDoRsp3 = new WillDoRsp();
        willDoRsp3.setDate("2018-09-12");
        willDoRsp3.setTitle("恢复施工安全监督告知书审核");
        willDoRsp3.setType("2");
        mWillDoRsps.add(willDoRsp3);
        WillDoRsp willDoRsp4 = new WillDoRsp();
        willDoRsp4.setDate("2018-09-12");
        willDoRsp4.setTitle("恢复施工安全监督告知书审核");
        willDoRsp4.setType("3");
        mWillDoRsps.add(willDoRsp4);
        WillDoRsp willDoRsp5 = new WillDoRsp();
        willDoRsp5.setDate("2018-09-12");
        willDoRsp5.setTitle("监督计划审核");
        willDoRsp5.setType("4");
        mWillDoRsps.add(willDoRsp4);
        mBaseQuickAdapter = new BaseQuickAdapter<WillDoRsp, BaseViewHolder>(R.layout.woill_do_item_01, mWillDoRsps) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, final WillDoRsp rowsBean) {
                baseViewHolder.setText(R.id.title,rowsBean.getTitle());
                baseViewHolder.setText(R.id.type,rowsBean.getType());
                baseViewHolder.setText(R.id.time,rowsBean.getDate());
                baseViewHolder.getView(R.id.ll_woill).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(rowsBean.getType().equals("0")) {
                            //安全监督手续申请审核
                            Intent intent = new Intent(mActivity, WillDoSafeJDSXSQActivity.class);
                            startActivity(intent);
                        }else if(rowsBean.getType().equals("1")){
                            //中止施工安全监督告知审核
                            Intent intent = new Intent(mActivity, WillDoSafeJDGZSHActivity.class);
                            startActivity(intent);
                        }else if(rowsBean.getType().equals("2")){
                            //中止施工安全监督告知审核
                            Intent intent = new Intent(mActivity, WillDoSafeJDGZSHActivity.class);
                            startActivity(intent);
                        }else if(rowsBean.getType().equals("3")){
                            //中止施工安全监督告知审核
                            Intent intent = new Intent(mActivity, WillDoSafeZZSGJDActivity.class);
                            startActivity(intent);
                        }else if(rowsBean.getType().equals("4")){
                            //中止施工安全监督告知审核
                            Intent intent = new Intent(mActivity, WillDoSafeJDJHSHActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        };
        mRvContent.setAdapter(mBaseQuickAdapter);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_will_do;
    }

    private void initTitle() {
        mTvTitle.setText("待办事项");
    }
}
