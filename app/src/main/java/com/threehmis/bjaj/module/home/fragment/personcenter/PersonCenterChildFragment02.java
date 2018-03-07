package com.threehmis.bjaj.module.home.fragment.personcenter;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.threehmis.bjaj.R;
import com.threehmis.bjaj.module.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by llz on 2018/3/6.
 */

public class PersonCenterChildFragment02 extends BaseFragment {
    @BindView(R.id.tv_01)
    TextView mTv01;
    @BindView(R.id.tv_02)
    TextView mTv02;
    @BindView(R.id.tv_03)
    TextView mTv03;
    @BindView(R.id.tv_04)
    TextView mTv04;
    @BindView(R.id.tv_05)
    TextView mTv05;

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        mTv01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(mActivity,PersonCenterDetailActivity.class);
                    startActivity(intent);
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_person_center_child_02;
    }


}
