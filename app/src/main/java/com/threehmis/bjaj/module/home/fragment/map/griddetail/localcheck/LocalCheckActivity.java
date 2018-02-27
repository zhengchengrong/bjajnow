package com.threehmis.bjaj.module.home.fragment.map.griddetail.localcheck;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.module.base.BaseActivity;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.taskcheck.TaskCheckFragment01;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.taskcheck.TaskCheckFragment02;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by llz on 2018/2/27.
 */

public class LocalCheckActivity extends BaseActivity {
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
        Fragment fragment1 = new LocalCheckFragment01();
        Fragment fragment2 = new LocalCheckFragment02();
        mFragments2.add(fragment1);
        mFragments2.add(fragment2);
        SegmentTabLayout tabLayout_4 = (SegmentTabLayout) findViewById(R.id.tl_4);
        tabLayout_4.setTabData(mTitles_2, this, R.id.fl_change, mFragments2);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    private void initTitle() {
        mTvTitle.setText("现场检查");
        mTvBack.setVisibility(View.VISIBLE);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
