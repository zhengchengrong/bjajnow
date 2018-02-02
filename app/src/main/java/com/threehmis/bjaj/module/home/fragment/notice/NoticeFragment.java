package com.threehmis.bjaj.module.home.fragment.notice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.adapter.NoticeViewPagerAdaptrer;
import com.threehmis.bjaj.api.bean.respon.FragmentArrayBean;

import java.util.ArrayList;
import java.util.List;


public class NoticeFragment extends Fragment {


    private ViewPager viewPager;
    private SlidingTabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notice, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tabLayout = (SlidingTabLayout) view.findViewById(R.id.tabLayout);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 设置ViewPager最大缓存的页面个数
        viewPager.setOffscreenPageLimit(1);

        List<FragmentArrayBean> fragmentArrayBeen = new ArrayList<>();

        FragmentArrayBean bean1 = new FragmentArrayBean();
        bean1.fragment = new WilldoFragment();
        bean1.str = "待办事项";
        fragmentArrayBeen.add(bean1);

        FragmentArrayBean bean2 = new FragmentArrayBean();
        bean2.fragment = new AnnounceFragment();
        bean2.str = "通知公告";
        fragmentArrayBeen.add(bean2);

        FragmentArrayBean bean3 = new FragmentArrayBean();
        bean3.fragment = new NewsFragment();
        bean3.str = "新闻动态";
        fragmentArrayBeen.add(bean3);

        NoticeViewPagerAdaptrer noticeViewPagerAdaptrer = new NoticeViewPagerAdaptrer(getChildFragmentManager(), fragmentArrayBeen);

        viewPager.setAdapter(noticeViewPagerAdaptrer);

        tabLayout.setViewPager(viewPager);

//        tabLayout.showDot(0);
        tabLayout.showMsg(0,5);
        tabLayout.setMsgMargin(0, 15, 10);

    }
}
