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
import com.threehmis.bjaj.api.bean.respon.MessageBeanRsp;
import com.threehmis.bjaj.module.base.BaseFragment;
import com.threehmis.bjaj.widget.EmptyLayout;

import java.util.ArrayList;

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
    private ArrayList<MessageBeanRsp> mMessageBeanRsps = new ArrayList<MessageBeanRsp>();
    @Override
    protected void initInjector() {

    }
    @Override
    protected void initViews() {
        mTvTitle.setText(R.string.fragment_message_title);
       mRvContent.setLayoutManager(new LinearLayoutManager(mActivity));
       MessageBeanRsp messageBeanRsp = new MessageBeanRsp();
        messageBeanRsp.setContent("213");
        messageBeanRsp.setMydate("2018-07-21");
        messageBeanRsp.setUrl("");
        mMessageBeanRsps.add(messageBeanRsp);

        MessageBeanRsp messageBeanRsp2 = new MessageBeanRsp();
        messageBeanRsp2.setContent("213");
        messageBeanRsp2.setMydate("2018-07-21");
        messageBeanRsp2.setUrl("");
        mMessageBeanRsps.add(messageBeanRsp2);
        mBaseQuickAdapter = new BaseQuickAdapter<MessageBeanRsp, BaseViewHolder>(R.layout.fragment_message_item, mMessageBeanRsps) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder,  MessageBeanRsp rowsBean) {
                baseViewHolder.setText(R.id.tv_01,rowsBean.getMydate());
                baseViewHolder.setText(R.id.tv_02,rowsBean.getContent());
            }
        };
        mRvContent.setAdapter(mBaseQuickAdapter);
        mBaseQuickAdapter.setEnableLoadMore(true);
    }
    @Override
    protected void updateViews(boolean isRefresh) {

    }
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_message;
    }

}
