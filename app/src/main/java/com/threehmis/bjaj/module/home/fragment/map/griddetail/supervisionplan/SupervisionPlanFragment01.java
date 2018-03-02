package com.threehmis.bjaj.module.home.fragment.map.griddetail.supervisionplan;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.threehmis.bjaj.R;
import com.threehmis.bjaj.module.base.BaseFragment;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.rectificationnotify.RectificationNotifyActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by llz on 2018/3/2.
 */

public class SupervisionPlanFragment01 extends BaseFragment {
    @BindView(R.id.tv_01)
    TextView mTv01;
    @BindView(R.id.et_01)
    EditText mEt01;
    @BindView(R.id.tv_02)
    TextView mTv02;
    @BindView(R.id.tv_date_02)
    TextView mTvDate02;
    @BindView(R.id.tv_03)
    TextView mTv03;
    @BindView(R.id.et_03)
    EditText mEt03;
    @BindView(R.id.tv_04)
    TextView mTv04;
    @BindView(R.id.et_04)
    EditText mEt04;
    @BindView(R.id.tv_05)
    TextView mTv05;
    @BindView(R.id.et_05)
    EditText mEt05;
    @BindView(R.id.tv_06)
    TextView mTv06;
    @BindView(R.id.et_06)
    EditText mEt06;
    @BindView(R.id.tv_07)
    TextView mTv07;
    @BindView(R.id.tv_date_07)
    TextView mTvDate07;
    @BindView(R.id.tv_08)
    TextView mTv08;
    @BindView(R.id.tv_date_08)
    EditText mTvDate08;
    @BindView(R.id.tv_09)
    TextView mTv09;
    @BindView(R.id.et_09)
    EditText mEt09;
    @BindView(R.id.tv_10)
    TextView mTv10;
    @BindView(R.id.et_10)
    EditText mEt10;
    @BindView(R.id.tv_11)
    TextView mTv11;
    @BindView(R.id.et_11)
    EditText mEt11;
    @BindView(R.id.tv_12)
    TextView mTv12;
    @BindView(R.id.et_12)
    EditText mEt12;
    int mYear, mMonth, mDay;
    String days="";
    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        mTvDate02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(mActivity, onDateSetListener1, mYear, mMonth, mDay).show();
            }
        });
        mTvDate07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(mActivity, onDateSetListener2, mYear, mMonth, mDay).show();
            }
        });
        mTvDate08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(mActivity, onDateSetListener3, mYear, mMonth, mDay).show();
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_supervision_plan;
    }

    /**
     * 日期选择器对话框监听
     */
    private DatePickerDialog.OnDateSetListener onDateSetListener1 = new DatePickerDialog.OnDateSetListener() {




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
            mTvDate02.setText(days);
        }
    };
    /**
     * 日期选择器对话框监听
     */
    private DatePickerDialog.OnDateSetListener onDateSetListener2 = new DatePickerDialog.OnDateSetListener() {




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
            mTvDate07.setText(days);
        }
    };
    /**
     * 日期选择器对话框监听
     */
    private DatePickerDialog.OnDateSetListener onDateSetListener3 = new DatePickerDialog.OnDateSetListener() {




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
            mTvDate08.setText(days);
        }
    };
}
