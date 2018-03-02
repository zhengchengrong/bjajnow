package com.threehmis.bjaj.module.home.fragment.map.griddetail.supervisionplan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.module.base.BaseActivity;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.localcheck.LocalCheckFragment01;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.localcheck.LocalCheckFragment02;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by llz on 2018/3/2.
 */

public class SupervisionPlanActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.other)
    TextView mOther;
    @BindView(R.id.tv_back)
    TextView mTvBack;
    @BindView(R.id.tl_4)
    SegmentTabLayout mTl4;
    @BindView(R.id.fl_change)
    FrameLayout mFlChange;
    private ArrayList<Fragment> mFragments2 = new ArrayList<>();
    private String[] mTitles_2 = {"监督计划", "变更记录"};

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_supervision_plan;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initTitle();
        Fragment fragment1 = new SupervisionPlanFragment01();
        Fragment fragment2 = new SupervisionPlanFragment02();
        mFragments2.add(fragment1);
        mFragments2.add(fragment2);
        SegmentTabLayout tabLayout_4 = (SegmentTabLayout) findViewById(R.id.tl_4);
        tabLayout_4.setTabData(mTitles_2, this, R.id.fl_change, mFragments2);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    private void initTitle() {
        mTvTitle.setText("监督计划");
        mTvBack.setVisibility(View.VISIBLE);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
