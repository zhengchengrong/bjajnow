package com.threehmis.bjaj.module.home.fragment.adminmain.childmainfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.threehmis.bjaj.R;
import com.threehmis.bjaj.module.base.BaseFragment;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by llz on 2018/2/22.
 */

public class AllCityFragment extends BaseFragment {
    @BindView(R.id.tv_all_city_01)
    TextView mTvAllCity01;
    @BindView(R.id.id_spinner_all)
    Spinner mIdSpinnerAll;
    @BindView(R.id.id_spinner_year)
    Spinner mIdSpinnerYear;
    private static final String[] year={"年份","2018","2018","2018","2018"};
    private static final String[] all={"全部","第一季度","第一季度","第一季度","第一季度"};
    private ArrayAdapter<String> adapterYear;
    private ArrayAdapter<String> adapterAll;

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        //将可选内容与ArrayAdapter连接起来
        adapterYear = new ArrayAdapter<String>(mActivity,android.R.layout.simple_spinner_item,year);
        //设置下拉列表的风格
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        mIdSpinnerYear.setAdapter(adapterYear);
        //添加事件Spinner事件监听
        mIdSpinnerYear.setOnItemSelectedListener(new SpinnerSelectedListener());
        //设置默认值
        mIdSpinnerYear.setVisibility(View.VISIBLE);


        adapterAll = new ArrayAdapter<String>(mActivity,android.R.layout.simple_spinner_item,all);
        //设置下拉列表的风格
        adapterAll.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        mIdSpinnerAll.setAdapter(adapterAll);
        //添加事件Spinner事件监听
        mIdSpinnerAll.setOnItemSelectedListener(new SpinnerSelectedListener());
        //设置默认值
        mIdSpinnerAll.setVisibility(View.VISIBLE);

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_all_city;
    }
    //使用数组形式操作
    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            RxToast.showToast(year[arg2]);
        }
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

}
