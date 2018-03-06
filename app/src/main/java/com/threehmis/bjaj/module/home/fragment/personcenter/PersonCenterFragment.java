package com.threehmis.bjaj.module.home.fragment.personcenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.module.base.BaseFragment;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.schedule.ScheduleFragment01;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.schedule.ScheduleFragment02;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by llz on 2018/3/6.
 */

public class PersonCenterFragment extends BaseFragment {
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
    private String[] mTitles_2 = {"个人资料", "安全指标资料"};
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_person_center;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initTitle();
        Fragment fragment1 = new PersonCenterChildFragment01();
        Fragment fragment2 = new PersonCenterChildFragment02();
        mFragments2.add(fragment1);
        mFragments2.add(fragment2);
        mTl4.setTabData(mTitles_2, getActivity(), R.id.fl_change, mFragments2);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
    private void initTitle() {
        mTvTitle.setText("资料中心");
    }

}
