package com.threehmis.bjaj.module.home.fragment.personcenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.threehmis.bjaj.R;
import com.threehmis.bjaj.module.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by llz on 2018/3/6.
 */

public class PersonCenterChildFragment01 extends BaseFragment {
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
    EditText mEt07;
    @BindView(R.id.tv_flag08)
    TextView mTvFlag08;
    @BindView(R.id.et_08)
    EditText mEt08;
    @BindView(R.id.tv_flag09)
    TextView mTvFlag09;
    @BindView(R.id.et_09)
    EditText mEt09;

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_person_center_child_01;
    }


}
