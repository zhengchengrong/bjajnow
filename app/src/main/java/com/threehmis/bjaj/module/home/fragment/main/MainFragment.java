package com.threehmis.bjaj.module.home.fragment.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.adapter.MainZhuTuViewPagerAdaper;
import com.threehmis.bjaj.api.bean.respon.ChangeAddressResponBean;
import com.threehmis.bjaj.module.base.BaseFragment;
import com.threehmis.bjaj.utils.DensityUtils;
import com.threehmis.bjaj.widget.NoScrollViewPager;
import com.threehmis.bjaj.widget.SpinerPopWindow;
import com.threehmis.bjaj.widget.supertextview.SuperTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by llz on 2018/1/11.
 */

public class MainFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.other)
    TextView mOther;
    @BindView(R.id.titleback)
    TextView mTitleback;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    List<ChangeAddressResponBean> data;
    List<ChangeAddressResponBean> data2;
    private SpinerPopWindow<String> mSpinerPopWindow;

    BaseQuickAdapter mBaseQuickAdapter;
    TextView mTvCity;
    private List<String> cityList;

    // 圆饼列表
    RecyclerView mRvList2;
    BaseQuickAdapter mBaseQuickAdapter2;

    private View header01,footer01,footer02;
    // 柱状图
    SegmentTabLayout mSlidingTabLayout;
    NoScrollViewPager viewPager;
    private String[] mTitles_3 = {"工程总数", "危大工程", "执法人员", "检查量"};
    //柱状图的views
    private List<View> views=new ArrayList<View>();

    public final static String[] months = new String[]{"区城区安全监督站", "区城区安全监督站", "区城区安全监督站", "区城区安全监督站"};

    public final static String[] days = new String[]{"Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun",};

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initViews() {

        mTitle.setText("首页");
        mTitleback.setVisibility(View.GONE);
        cityList = new ArrayList<String>();
        cityList.add("番禺区");
        cityList.add("白云区");
        cityList.add("天河区");
        mSpinerPopWindow = new SpinerPopWindow<String>(mActivity, cityList,itemClickListener);


        data = new ArrayList<ChangeAddressResponBean>();
        ChangeAddressResponBean changeAddressResponBean = new ChangeAddressResponBean();
        changeAddressResponBean.setUnitName("测试");
        changeAddressResponBean.setInterfaceUrl("测试123");
        data.add(changeAddressResponBean);
        ChangeAddressResponBean changeAddressResponBean2 = new ChangeAddressResponBean();
        changeAddressResponBean2.setUnitName("测试2");
        changeAddressResponBean2.setInterfaceUrl("测试123");
        data.add(changeAddressResponBean2);
        ChangeAddressResponBean changeAddressResponBean3 = new ChangeAddressResponBean();
        changeAddressResponBean3.setUnitName("测试2");
        changeAddressResponBean3.setInterfaceUrl("测试123");
        data.add(changeAddressResponBean3);
//        mRvList.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvList.setLayoutManager(new LinearLayoutManager(mActivity));
    //    mRvList.setScrollContainer(false);
        mBaseQuickAdapter = new BaseQuickAdapter<ChangeAddressResponBean, BaseViewHolder>(R.layout.first_card_item, data) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, ChangeAddressResponBean changeAddressResponBean) {
                baseViewHolder.setText(R.id.tv_title, "北京安监");
                baseViewHolder.setText(R.id.tv_num01, "2,865");
                baseViewHolder.setText(R.id.tv_num02, "2,865");
                baseViewHolder.setText(R.id.tv_num03, "2,865");
                baseViewHolder.setText(R.id.tv_num04, "2,865");
                baseViewHolder.setText(R.id.tv_num05, "2,865");
            }

        };
        mRvList.setAdapter(mBaseQuickAdapter);

        // 添加头部01
        header01 = View.inflate(mActivity,R.layout.fragment_main_header01,null);
        mTvCity =   header01.findViewById(R.id.tv_main_city);
        mTvCity.setOnClickListener(this);
        mBaseQuickAdapter.addHeaderView(header01);
        // 添加尾部01
        footer01 = View.inflate(mActivity,R.layout.fragment_main_footer01,null);
        // 添加圆饼View为rvList的footer。
        mBaseQuickAdapter.addFooterView(footer01);
        data2 = new ArrayList<ChangeAddressResponBean>();
        ChangeAddressResponBean changeAddressResponBean21 = new ChangeAddressResponBean();
        changeAddressResponBean21.setUnitName("测试");
        changeAddressResponBean21.setInterfaceUrl("测试123");
        data2.add(changeAddressResponBean21);
        ChangeAddressResponBean changeAddressResponBean22 = new ChangeAddressResponBean();
        changeAddressResponBean22.setUnitName("测试2");
        changeAddressResponBean22.setInterfaceUrl("测试123");
        data2.add(changeAddressResponBean22);
        ChangeAddressResponBean changeAddressResponBean23 = new ChangeAddressResponBean();
        changeAddressResponBean23.setUnitName("测试2");
        changeAddressResponBean23.setInterfaceUrl("测试123");
        data2.add(changeAddressResponBean23);

        mRvList2 = (RecyclerView)footer01.findViewById(R.id.rv_list2);
        mRvList2.setFocusableInTouchMode(false); // 一定要加，让其失去焦点，防止加载页面自动移动到第二个RecyclerView
        mRvList2.requestFocus();
        mRvList2.setLayoutManager(new LinearLayoutManager(mActivity));
        mBaseQuickAdapter2 = new BaseQuickAdapter<ChangeAddressResponBean, BaseViewHolder>(R.layout.first_main_pie_item, data2) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, ChangeAddressResponBean changeAddressResponBean) {
                TextView stv_title = baseViewHolder.getView(R.id.stv_title);
                stv_title.setText("北京安监");
                SuperTextView stv_content1 = baseViewHolder.getView(R.id.stv_content1);
                stv_content1.setLeftTopString("市政1719项目，总面积5251万m，总造价21512亿元");
                SuperTextView stv_content2 = baseViewHolder.getView(R.id.stv_content2);
                stv_content2.setLeftTopString("市政1719项目，总面积5251万m，总造价21512亿元");
            }

        };
        mRvList2.setAdapter(mBaseQuickAdapter2);

        // 添加头部02
        footer02 = View.inflate(mActivity,R.layout.fragment_main_footer02,null);
        mSlidingTabLayout = (SegmentTabLayout) footer02.findViewById(R.id.tabLayout);
         viewPager = (NoScrollViewPager)footer02.findViewById(R.id.viewPager);
         initViewPager();// 初始化各个视图
        MainZhuTuViewPagerAdaper mainZhuTuViewPagerAdaper = new MainZhuTuViewPagerAdaper(views,mActivity);
         viewPager.setAdapter(mainZhuTuViewPagerAdaper);
        mSlidingTabLayout.setTabData(mTitles_3);
        mSlidingTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        viewPager.setOnPageChangeListener(new NoScrollViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mSlidingTabLayout.setCurrentTab(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // 添加柱状图View为rvList的footer。
        mBaseQuickAdapter.addFooterView(footer02);
    }
    //初始化view
    private void initViewPager() {
        //创建view布局
        View view1=View.inflate(mActivity, R.layout.main_zhutu_01, null);
        generateHorizontalBarData();
        View view2=View.inflate(mActivity, R.layout.main_zhutu_02, null);
        View view3=View.inflate(mActivity, R.layout.main_zhutu_03, null);
        View view4=View.inflate(mActivity, R.layout.main_zhutu_04, null);
        //把view布局添加到集合
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
    }

    private void generateHorizontalBarData() {
    }


    @Override
    protected void updateViews(boolean isRefresh) {
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_main;
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_main_city:
                mSpinerPopWindow.setWidth(mTvCity.getWidth());
                mSpinerPopWindow.showAsDropDown(mTvCity,0, DensityUtils.dp2px(mActivity,4));
                break;
        }
    }

    /**
     * 城市选择点击事件
     */
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            mTvCity.setText(cityList.get(i));
            mSpinerPopWindow.dismiss();
        }
    };
}
