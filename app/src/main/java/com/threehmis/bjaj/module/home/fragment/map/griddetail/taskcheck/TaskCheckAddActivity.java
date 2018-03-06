package com.threehmis.bjaj.module.home.fragment.map.griddetail.taskcheck;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.bean.request.TaskCheckAddBeanReq;
import com.threehmis.bjaj.api.bean.respon.ProjectStatusRsp;
import com.threehmis.bjaj.module.base.BaseActivity;
import com.threehmis.bjaj.widget.EmptyLayout;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by llz on 2018/2/27.
 */

public class TaskCheckAddActivity extends BaseActivity {


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
    @BindView(R.id.rb_01)
    RadioButton mRb01;
    @BindView(R.id.rb_02)
    RadioButton mRb02;
    @BindView(R.id.rb_03)
    RadioButton mRb03;
    @BindView(R.id.rg_basic)
    RadioGroup mRgBasic;
    @BindView(R.id.tv_03)
    TextView mTv03;
    @BindView(R.id.tv_add)
    TextView mTvAdd;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;

    private BaseQuickAdapter mBaseQuickAdapter;
    private ArrayList<TaskCheckAddBeanReq> mProjectStatusRsps;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_task_check_add;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initTitle();
        mTvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxToast.showToast("添加一行");
            }
        });
        mRvContent.setLayoutManager(new LinearLayoutManager(this));
        mProjectStatusRsps = new ArrayList<TaskCheckAddBeanReq>();
        mBaseQuickAdapter = new BaseQuickAdapter<TaskCheckAddBeanReq, BaseViewHolder>(R.layout.item_task_check_add_01, mProjectStatusRsps) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, TaskCheckAddBeanReq rowsBean) {
                baseViewHolder.setText(R.id.tv_01,baseViewHolder.getAdapterPosition()+1);
            }
        };
        mRvContent.setAdapter(mBaseQuickAdapter);

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    private void initTitle() {
        mTvTitle.setText("任务指派");
        mTvBack.setVisibility(View.VISIBLE);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
