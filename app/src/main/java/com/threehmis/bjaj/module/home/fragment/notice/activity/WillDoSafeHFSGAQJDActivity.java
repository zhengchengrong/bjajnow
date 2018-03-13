/*
 * Copyright (c) zcr 2018.
 */

package com.threehmis.bjaj.module.home.fragment.notice.activity;

import android.app.DatePickerDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.threehmis.bjaj.R;
import com.threehmis.bjaj.module.base.BaseActivity;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;

/**
 * Created by llz on 2018/3/7.
 */

public class WillDoSafeHFSGAQJDActivity extends BaseActivity {
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
    @BindView(R.id.spinner01)
    Spinner mSpinner01;
    @BindView(R.id.tv_flag05)
    TextView mTvFlag05;
    @BindView(R.id.et_04)
    EditText mEt04;
    @BindView(R.id.et_05)
    EditText mEt05;
    @BindView(R.id.tv_flag06)
    TextView mTvFlag06;
    @BindView(R.id.et_06)
    EditText mEt06;
    @BindView(R.id.tv_flag07)
    TextView mTvFlag07;
    @BindView(R.id.et_07)
    EditText mEt07;
    @BindView(R.id.tv_flag08)
    TextView mTvFlag08;
    @BindView(R.id.et_08)
    EditText mEt08;
    @BindView(R.id.tv_flag09)
    TextView mTvFlag09;
    @BindView(R.id.et_09)
    EditText mEt09;
    @BindView(R.id.tv_flag10)
    TextView mTvFlag10;
    @BindView(R.id.et_10)
    EditText mEt10;
    @BindView(R.id.tv_flag11)
    TextView mTvFlag11;
    @BindView(R.id.et_11)
    EditText mEt11;
    @BindView(R.id.tv_flag12)
    TextView mTvFlag12;
    @BindView(R.id.et_12)
    EditText mEt12;
    @BindView(R.id.tv_flag13)
    TextView mTvFlag13;
    @BindView(R.id.et_13)
    EditText mEt13;
    @BindView(R.id.tv_flag14)
    TextView mTvFlag14;
    @BindView(R.id.et_14)
    EditText mEt14;
    @BindView(R.id.tv_flag15)
    TextView mTvFlag15;
    @BindView(R.id.et_15)
    EditText mEt15;
    @BindView(R.id.tv_flag16)
    TextView mTvFlag16;
    @BindView(R.id.et_16)
    EditText mEt16;
    @BindView(R.id.tv_flag17)
    TextView mTvFlag17;
    @BindView(R.id.et_17)
    EditText mEt17;
    @BindView(R.id.tv_flag18)
    TextView mTvFlag18;
    @BindView(R.id.et_18)
    TextView mEt18;
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
    private ArrayAdapter<String> adapter;
    private static final String[] data = {"房建工程"};

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_will_do_safe_hfsgaqjd;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initTitle();
        initSpinner();
        mTvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });
        mEt18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(WillDoSafeHFSGAQJDActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
            }
        });
    }

    private void check() {
        if(TextUtils.isEmpty(mEt01.getText().toString())){
            RxToast.showToast("安全监督编号");
            return;
        }
        if(TextUtils.isEmpty(mEt02.getText().toString())){
            RxToast.showToast("工程名称");
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
            RxToast.showToast("请输入中止安全监督告知书编号");
            return;
        }
        if(TextUtils.isEmpty(mEt06.getText().toString())){
            RxToast.showToast("请输入恢复安全监督原因");
            return;
        }
        if(TextUtils.isEmpty(mEt07.getText().toString())){
            RxToast.showToast("请输入监督员及执法证号");
            return;
        }
        if(TextUtils.isEmpty(mEt08.getText().toString())){
            RxToast.showToast("请输入恢复安全监督告知书编号");
            return;
        }
        if(TextUtils.isEmpty(mEt09.getText().toString())){
            RxToast.showToast("请输入告知日期");
            return;
        }
    }

    private void initSpinner() {
        //将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        mSpinner01.setAdapter(adapter);
        //添加事件Spinner事件监听
        mSpinner01.setOnItemSelectedListener(new SpinnerSelectedListener());
        //设置默认值
        mSpinner01.setVisibility(View.VISIBLE);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    private void initTitle() {
        mTvTitle.setText("安全监督手续申请审核");
        mTvBack.setVisibility(View.VISIBLE);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    //使用数组形式操作
    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            //RxToast.showToast(data[arg2]);
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
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
            mEt18.setText(days);
        }
    };

}
