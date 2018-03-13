/*
 * Copyright (c) zcr 2018.
 */

package com.threehmis.bjaj.module.home.fragment.notice.activity;

import android.app.DatePickerDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.threehmis.bjaj.R;
import com.threehmis.bjaj.module.base.BaseActivity;
import com.vondear.rxtools.view.RxToast;

import java.util.Calendar;

import butterknife.BindView;

/**
 * Created by llz on 2018/3/8.
 */

public class WillDoSafeZZSGJDActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.other)
    TextView mOther;
    @BindView(R.id.tv_back)
    TextView mTvBack;
    @BindView(R.id.tv_flag01)
    TextView mTvFlag01;
    @BindView(R.id.et_01)
    EditText mEt01;
    @BindView(R.id.tv_flag02)
    TextView mTvFlag02;
    @BindView(R.id.et_02)
    EditText mEt02;
    @BindView(R.id.tv_flag03)
    TextView mTvFlag03;
    @BindView(R.id.et_03)
    EditText mEt03;
    @BindView(R.id.tv_flag04)
    TextView mTvFlag04;
    @BindView(R.id.et_04)
    EditText mEt04;
    @BindView(R.id.tv_flag05)
    TextView mTvFlag05;
    @BindView(R.id.et_05)
    EditText mEt05;
    @BindView(R.id.tv_flag06)
    TextView mTvFlag06;
    @BindView(R.id.et_06)
    EditText mEt06;
    @BindView(R.id.tv_flag07)
    TextView mTvFlag07;
    @BindView(R.id.et_07)
    TextView mEt07;
    @BindView(R.id.tv_flag08)
    TextView mTvFlag08;
    @BindView(R.id.et_08)
    EditText mEt08;
    @BindView(R.id.rb_01)
    RadioButton mRb01;
    @BindView(R.id.rb_02)
    RadioButton mRb02;
    @BindView(R.id.rg_basic)
    RadioGroup mRgBasic;
    @BindView(R.id.et_19)
    EditText mEt19;
    @BindView(R.id.tv_commit)
    TextView mTvCommit;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_will_do_safe_zzsgjd;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initTitle();
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        mEt07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(WillDoSafeZZSGJDActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
            }
        });
        mTvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();

            }
        });
    }

    private void check() {
        if(TextUtils.isEmpty(mEt01.getText().toString())){
            RxToast.showToast("请输入安全监督编号");
            return;
        }
        if(TextUtils.isEmpty(mEt02.getText().toString())){
            RxToast.showToast("请输入工程名称");
            return;
        }
        if(TextUtils.isEmpty(mEt03.getText().toString())){
            RxToast.showToast("请输入施工许可证");
            return;
        }
        if(TextUtils.isEmpty(mEt04.getText().toString())){
            RxToast.showToast("请输入建设单位");
            return;
        }
        if(TextUtils.isEmpty(mEt05.getText().toString())){
            RxToast.showToast("终止安全监督原因");
            return;
        }
        if(TextUtils.isEmpty(mEt06.getText().toString())){
            RxToast.showToast("终止安全监督告知书编号");
            return;
        }
        if(TextUtils.isEmpty(mEt07.getText().toString())){
            RxToast.showToast("请输入签发日期");
            return;
        }
        if(TextUtils.isEmpty(mEt08.getText().toString())){
            RxToast.showToast("请输入监督员");
            return;
        }

        if(TextUtils.isEmpty(mEt19.getText().toString())){
            RxToast.showToast("请输入审核意见");
            return;
        }

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    private void initTitle() {
        mTvTitle.setText("中止施工安全监督告知审核");
        mTvBack.setVisibility(View.VISIBLE);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    int mYear, mMonth, mDay;
    String days="";
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
            mEt07.setText(days);
        }
    };
}
