package com.threehmis.bjaj.module.home.fragment.map.griddetail.schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.threehmis.bjaj.R;

import java.util.ArrayList;

/**
 * 形象进度
 */
public class ScheduleActivity extends AppCompatActivity {

    private ArrayList<Fragment> mFragments2 = new ArrayList<>();
    private String[] mTitles_2 = {"进度登记", "进度记录"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stop_work);

        TextView titleback = (TextView) findViewById(R.id.titleback);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText("形象进度");
        titleback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Fragment fragment1 = new ScheduleFragment();
        Fragment fragment2 = new ScheduleRecordFragment();

        mFragments2.add(fragment1);
        mFragments2.add(fragment2);

        SegmentTabLayout tabLayout_4= (SegmentTabLayout) findViewById(R.id.tl_4);

        tabLayout_4.setTabData(mTitles_2,this,R.id.fl_change,mFragments2);

    }
}
