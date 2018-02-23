package com.threehmis.bjaj.module.home.fragment.adminmain;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.module.base.BaseFragment;
import com.threehmis.bjaj.module.home.fragment.adminmain.childmainfragment.AllCityFragment;
import com.threehmis.bjaj.module.home.fragment.adminmain.childmainfragment.AllTownFragment;
import com.threehmis.bjaj.module.home.fragment.map.MapFragment;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.projectinfo.ProjectListFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by llz on 2018/2/22.
 */

public class AdminMainFragment extends BaseFragment implements OnTabSelectListener {
    @BindView(R.id.tl_admin_main)
    SlidingTabLayout mTlAdminMain;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private Fragment fragment1;
    private Fragment fragment2;
    private MyPagerAdapter mAdapter;
    String[] mTitles;
    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        mTitles= new String[]{
                "全市数据统计", "各区县数据统计"
        };
        fragment1 = new AllCityFragment();
        fragment2 = new AllTownFragment();
        mFragments.clear();
        mFragments.add(fragment1);
        mFragments.add(fragment2);
        mAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        mVp.setAdapter(mAdapter);
        mTlAdminMain.setViewPager(mVp);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_admin_main;
    }


    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public void onTabReselect(int position) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
