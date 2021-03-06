package com.threehmis.bjaj.module.home.fragment.map.griddetail.schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.threehmis.bjaj.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 形象进度
 */
public class ScheduleActivity extends AppCompatActivity {

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
    private String[] mTitles_2 = {"工程形象进度", "单体形象进度"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stop_work);
        ButterKnife.bind(this);

        initTitle();
        Fragment fragment1 = new ScheduleFragment01();
        Fragment fragment2 = new ScheduleFragment02();
        mFragments2.add(fragment1);
        mFragments2.add(fragment2);
        SegmentTabLayout tabLayout_4 = (SegmentTabLayout) findViewById(R.id.tl_4);
        tabLayout_4.setTabData(mTitles_2, this, R.id.fl_change, mFragments2);

    }

    private void initTitle() {
        mTvTitle.setText("形象进度");
        mTvBack.setVisibility(View.VISIBLE);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
