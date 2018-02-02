package com.threehmis.bjaj.module.home.fragment.map.griddetail.projectinfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.threehmis.bjaj.R;


import java.util.ArrayList;


public class BaoGaoFragment extends Fragment {


    private ArrayList<Fragment> mFragments2 = new ArrayList<>();
    private String[] mTitles_2 = {"不合格情况", "处理情况"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_baogao, container, false);

        TextView titleback = (TextView) view.findViewById(R.id.titleback);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText("检测报告");
        titleback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        Fragment fragment1 = new UnqualifiedFragment();
        Fragment fragment2 = new DealFragment();

        mFragments2.add(fragment1);
        mFragments2.add(fragment2);

        SegmentTabLayout tabLayout_4= (SegmentTabLayout) view.findViewById(R.id.tl_4);

        tabLayout_4.setTabData(mTitles_2,getActivity(),R.id.fl_change,mFragments2);

        return view;
    }


}
