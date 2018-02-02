package com.threehmis.bjaj.module.home.fragment.map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.adapter.HomeFragmentPagerAdapter;
import com.threehmis.bjaj.api.bean.TabEntity;
import com.threehmis.bjaj.module.base.BaseFragment;
import com.threehmis.bjaj.module.base.IBaseView;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.projectinfo.ProjectListFragment;
import com.threehmis.bjaj.widget.NoScrollViewPager;

import java.util.ArrayList;

/**
 * Created by llz on 2018/1/9.
 */

public class MainMapFragment extends BaseFragment implements IBaseView {

    SegmentTabLayout mTl4;
    TextView mMoreOther;
    NoScrollViewPager mViewPager;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"地理分布", "工程列表"};
    private Fragment fragment1;
    private Fragment fragment2;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_main_map;
    }
    @Override
    protected void initInjector() {
    }
    @Override
    protected void initViews() {
        mMoreOther = fragmentView.findViewById(R.id.more_other);
        mViewPager = fragmentView.findViewById(R.id.vp_page);

        mMoreOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(),OtherProjectActivity.class));
            }
        });
        fragment1 = new MapFragment();
        fragment2 = new ProjectListFragment();
        mFragments.clear();
        mFragments.add(fragment1);
        mFragments.add(fragment2);
        mTl4 = (SegmentTabLayout) fragmentView.findViewById(R.id.tl_4);
        tl_2();
    }
    private void tl_2() {
        mTl4.setTabData(mTitles);
        mTl4.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        mViewPager.setAdapter(new HomeFragmentPagerAdapter(getActivity().getSupportFragmentManager(),mFragments));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                mTl4.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(0);
    }
    @Override
    protected void updateViews(boolean isRefresh) {

    }



}
