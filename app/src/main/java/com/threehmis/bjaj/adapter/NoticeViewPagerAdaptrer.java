package com.threehmis.bjaj.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.threehmis.bjaj.api.bean.respon.FragmentArrayBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 3hcd on 2016/12/12 0012.
 */

public class NoticeViewPagerAdaptrer extends FragmentStatePagerAdapter {


    List<FragmentArrayBean> fragmentArrayBeen = new ArrayList<>();

    public NoticeViewPagerAdaptrer(FragmentManager fm, List<FragmentArrayBean> fragmentArrayBeen) {
        super(fm);

        this.fragmentArrayBeen = fragmentArrayBeen;

    }


    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentArrayBeen.get(position).str;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayBeen.get(position).fragment;
    }

    @Override
    public int getCount() {
        return fragmentArrayBeen.size();
    }


}
