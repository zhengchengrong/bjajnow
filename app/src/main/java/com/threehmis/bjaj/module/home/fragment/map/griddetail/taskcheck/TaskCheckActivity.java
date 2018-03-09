package com.threehmis.bjaj.module.home.fragment.map.griddetail.taskcheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.module.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by llz on 2018/2/27.
 */

public class TaskCheckActivity extends BaseActivity {
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
    private String[] mTitles_2 = {"未完成", "已完成"};

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_task_check;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initTitle();
        Fragment fragment1 = new TaskCheckFragment01();
        Fragment fragment2 = new TaskCheckFragment02();
        mFragments2.add(fragment1);
        mFragments2.add(fragment2);
        SegmentTabLayout tabLayout_4 = (SegmentTabLayout) findViewById(R.id.tl_4);
        tabLayout_4.setTabData(mTitles_2, this, R.id.fl_change, mFragments2);


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
