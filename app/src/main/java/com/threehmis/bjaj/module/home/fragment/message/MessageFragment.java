package com.threehmis.bjaj.module.home.fragment.message;

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
import com.threehmis.bjaj.module.base.BaseFragment;
import com.threehmis.bjaj.widget.EmptyLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by llz on 2018/2/24.
 */

public class MessageFragment extends BaseFragment {
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
    BaseQuickAdapter mBaseQuickAdapter;
    @Override
    protected void initInjector() {

    }
    @Override
    protected void initViews() {
        mTvTitle.setText(R.string.fragment_message_title);
       /* mRvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        mBaseQuickAdapter = new BaseQuickAdapter<KcListBean.RowsBean, BaseViewHolder>(R.layout.rv_observer_item, list) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder,  KcListBean.RowsBean rowsBean) {

            }
        };
        mRvContent.setAdapter(mBaseQuickAdapter);
        mBaseQuickAdapter.setEnableLoadMore(true);*/
    }
    @Override
    protected void updateViews(boolean isRefresh) {

    }
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_message;
    }

}
