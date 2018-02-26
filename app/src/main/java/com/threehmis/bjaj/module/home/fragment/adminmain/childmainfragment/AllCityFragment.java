package com.threehmis.bjaj.module.home.fragment.adminmain.childmainfragment;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.threehmis.bjaj.R;
import com.threehmis.bjaj.module.base.BaseFragment;
import com.threehmis.bjaj.widget.WrapContentHeightViewPager;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by llz on 2018/2/22.
 */

public class AllCityFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.tv_all_city_01)
    TextView mTvAllCity01;
    @BindView(R.id.id_spinner_all)
    Spinner mIdSpinnerAll;
    @BindView(R.id.id_spinner_year)
    Spinner mIdSpinnerYear;
    private static final String[] year = {"年份", "2018", "2018", "2018", "2018"};
    private static final String[] all = {"全部", "第一季度", "第一季度", "第一季度", "第一季度"};
    @BindView(R.id.tv_all_num_01)
    TextView mTvAllNum01;
    @BindView(R.id.tv_all_num_01_a)
    TextView mTvAllNum01A;
    @BindView(R.id.tv_all_num_02)
    TextView mTvAllNum02;
    @BindView(R.id.tv_all_num_02_b)
    TextView mTvAllNum02B;
    @BindView(R.id.tv_all_num_03)
    TextView mTvAllNum03;
    @BindView(R.id.tv_all_num_03_c)
    TextView mTvAllNum03C;
    @BindView(R.id.tv_all_num_04)
    TextView mTvAllNum04;
    @BindView(R.id.tv_all_num_04_d)
    TextView mTvAllNum04D;
    @BindView(R.id.tv_all_num_05)
    TextView mTvAllNum05;
    @BindView(R.id.tv_all_num_05_e)
    TextView mTvAllNum05E;
    @BindView(R.id.tv_all_xuke_01)
    TextView mTvAllXuke01;
    @BindView(R.id.tv_all_xuke_01_a)
    TextView mTvAllXuke01A;
    @BindView(R.id.tv_all_xuke_02)
    TextView mTvAllXuke02;
    @BindView(R.id.tv_all_xuke_02_b)
    TextView mTvAllXuke02B;
    @BindView(R.id.tv_all_xuke_03)
    TextView mTvAllXuke03;
    @BindView(R.id.tv_all_xuke_03_c)
    TextView mTvAllXuke03C;
    @BindView(R.id.vp_city)
    WrapContentHeightViewPager mVpCity;
    @BindView(R.id.cv_01)
    CardView mCv01;
    @BindView(R.id.cv_02)
    CardView mCv02;
    @BindView(R.id.cv_03)
    CardView mCv03;
    private ArrayList<View> aList;
    private MyPagerAdapter mAdapter;
    private ArrayAdapter<String> adapterYear;
    private ArrayAdapter<String> adapterAll;

    private View view01;
    private View view02;
    private View view03;

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        //将可选内容与ArrayAdapter连接起来
        adapterYear = new ArrayAdapter<String>(mActivity, android.R.layout.simple_spinner_item, year);
        //设置下拉列表的风格
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        mIdSpinnerYear.setAdapter(adapterYear);
        //添加事件Spinner事件监听
        mIdSpinnerYear.setOnItemSelectedListener(new SpinnerSelectedListener());
        //设置默认值
        mIdSpinnerYear.setVisibility(View.VISIBLE);


        adapterAll = new ArrayAdapter<String>(mActivity, android.R.layout.simple_spinner_item, all);
        //设置下拉列表的风格
        adapterAll.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        mIdSpinnerAll.setAdapter(adapterAll);
        //添加事件Spinner事件监听
        mIdSpinnerAll.setOnItemSelectedListener(new SpinnerSelectedListener());
        //设置默认值
        mIdSpinnerAll.setVisibility(View.VISIBLE);
        aList = new ArrayList<View>();
        LayoutInflater li = mActivity.getLayoutInflater();
        view01 = li.inflate(R.layout.view_city_one, null, false);
        view02 = li.inflate(R.layout.view_city_one, null, false);
        view03 = li.inflate(R.layout.view_city_one, null, false);

       /* LinearLayout linearLayout = view01.findViewById(R.id.course_viewflipper);
        linearLayout.addView( new PieChart3D01View(mActivity));*/
        aList.add(view01);
        aList.add(view02);
        aList.add(view03);

        /*aList.add(li.inflate(R.layout.view_city_two,null,false));
        aList.add(li.inflate(R.layout.view_city_three,null,false));*/
        mAdapter = new MyPagerAdapter(aList);
        mVpCity.setAdapter(mAdapter);

        mCv01.setOnClickListener(this);
        mCv02.setOnClickListener(this);
        mCv03.setOnClickListener(this);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_all_city;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.cv_01:
                mCv01.setCardBackgroundColor(mActivity.getResources().getColor(R.color.main_color));
                mCv02.setCardBackgroundColor(mActivity.getResources().getColor(R.color.white));
                mCv03.setCardBackgroundColor(mActivity.getResources().getColor(R.color.white));
                mTvAllXuke01.setTextColor(mActivity.getResources().getColor(R.color.white));
                mTvAllXuke02.setTextColor(mActivity.getResources().getColor(R.color.main_text_color));
                mTvAllXuke03.setTextColor(mActivity.getResources().getColor(R.color.main_text_color));
                mTvAllXuke01A.setTextColor(mActivity.getResources().getColor(R.color.white));
                mTvAllXuke02B.setTextColor(mActivity.getResources().getColor(R.color.main_text_color));
                mTvAllXuke03C.setTextColor(mActivity.getResources().getColor(R.color.main_text_color));

                mVpCity.setCurrentItem(0);
                break;
            case R.id.cv_02:
                mCv01.setCardBackgroundColor(mActivity.getResources().getColor(R.color.white));
                mCv02.setCardBackgroundColor(mActivity.getResources().getColor(R.color.main_color));
                mCv03.setCardBackgroundColor(mActivity.getResources().getColor(R.color.white));
                mTvAllXuke01.setTextColor(mActivity.getResources().getColor(R.color.main_text_color));
                mTvAllXuke02.setTextColor(mActivity.getResources().getColor(R.color.white));
                mTvAllXuke03.setTextColor(mActivity.getResources().getColor(R.color.main_text_color));
                mTvAllXuke01A.setTextColor(mActivity.getResources().getColor(R.color.main_text_color));
                mTvAllXuke02B.setTextColor(mActivity.getResources().getColor(R.color.white));
                mTvAllXuke03C.setTextColor(mActivity.getResources().getColor(R.color.main_text_color));
                mVpCity.setCurrentItem(1);
                break;
            case R.id.cv_03:
                mCv01.setCardBackgroundColor(mActivity.getResources().getColor(R.color.white));
                mCv02.setCardBackgroundColor(mActivity.getResources().getColor(R.color.white));
                mCv03.setCardBackgroundColor(mActivity.getResources().getColor(R.color.main_color));
                mTvAllXuke01.setTextColor(mActivity.getResources().getColor(R.color.main_text_color));
                mTvAllXuke02.setTextColor(mActivity.getResources().getColor(R.color.main_text_color));
                mTvAllXuke03.setTextColor(mActivity.getResources().getColor(R.color.white));
                mTvAllXuke01A.setTextColor(mActivity.getResources().getColor(R.color.main_text_color));
                mTvAllXuke02B.setTextColor(mActivity.getResources().getColor(R.color.main_text_color));
                mTvAllXuke03C.setTextColor(mActivity.getResources().getColor(R.color.white));
                mVpCity.setCurrentItem(2);
                break;
        }

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


    public class MyPagerAdapter extends PagerAdapter {
        private ArrayList<View> viewLists;

        public MyPagerAdapter() {
        }

        public MyPagerAdapter(ArrayList<View> viewLists) {
            super();
            this.viewLists = viewLists;
        }

        @Override
        public int getCount() {
            return viewLists.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewLists.get(position));
            return viewLists.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewLists.get(position));
        }
    }


}
