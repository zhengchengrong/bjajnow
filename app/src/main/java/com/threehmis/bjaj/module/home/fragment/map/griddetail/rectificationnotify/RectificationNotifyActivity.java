package com.threehmis.bjaj.module.home.fragment.map.griddetail.rectificationnotify;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.module.base.BaseActivity;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.localcheck.LocalCheckFragment01;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.localcheck.LocalCheckFragment02;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by llz on 2018/2/28.
 */

public class RectificationNotifyActivity extends BaseActivity {
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
    @BindView(R.id.tv_date)
    TextView mTvDate;
    @BindView(R.id.iv_date)
    ImageView mIvDate;
    @BindView(R.id.rl_date)
    RelativeLayout mRlDate;
    private ArrayList<Fragment> mFragments2 = new ArrayList<>();
    private String[] mTitles_2 = {"未回复", "已回复"};

    int mYear, mMonth, mDay;
    String days="";
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_rectifiction_notify;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initTitle();
        Fragment fragment1 = new RectificatioinNotifyFragment01();
        Bundle bundle = new Bundle();
        bundle.putString(Const.DATES,days);
        fragment1.setArguments(bundle);
        Fragment fragment2 = new RectificationNotityFragment02();
        fragment2.setArguments(bundle);
        mFragments2.add(fragment1);
        mFragments2.add(fragment2);
        SegmentTabLayout tabLayout_4 = (SegmentTabLayout) findViewById(R.id.tl_4);
        tabLayout_4.setTabData(mTitles_2, this, R.id.fl_change, mFragments2);
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        mRlDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RectificationNotifyActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
            }
        });
    }

    private void initTitle() {
        mTvTitle.setText("整改通知");
        mTvBack.setVisibility(View.VISIBLE);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    /**
     * 日期选择器对话框监听
     */
    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;

            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }

            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }

            }
            mTvDate.setText(days);
        }
    };


}
