package com.threehmis.bjaj.module.home.fragment.notice.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.threehmis.bjaj.R;
import com.threehmis.bjaj.module.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by llz on 2018/3/7.
 */

public class WillDoSafeJDSXSQActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.other)
    TextView mOther;
    @BindView(R.id.tv_back)
    TextView mTvBack;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_will_do_safe_jdsxsq;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initTitle();
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    private void initTitle() {
        mTvTitle.setText("安全监督手续申请审核");
        mTvBack.setVisibility(View.VISIBLE);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
