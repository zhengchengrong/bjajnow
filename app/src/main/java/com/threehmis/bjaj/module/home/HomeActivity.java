package com.threehmis.bjaj.module.home;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.utils.UnreadMsgUtils;
import com.flyco.tablayout.widget.MsgView;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.adapter.HomeFragmentPagerAdapter;
import com.threehmis.bjaj.api.bean.TabEntity;
import com.threehmis.bjaj.dialog.MainMoreDialog;
import com.threehmis.bjaj.injector.components.DaggerHomeComponent;
import com.threehmis.bjaj.injector.modules.HomeModule;
import com.threehmis.bjaj.module.base.BaseActivity;
import com.threehmis.bjaj.module.home.fragment.main.MainFragment;
import com.threehmis.bjaj.module.home.fragment.map.MainMapFragment;
import com.threehmis.bjaj.module.home.fragment.notice.NoticeFragment;
import com.threehmis.bjaj.module.logins.LoginActivity;
import com.threehmis.bjaj.utils.DensityUtils;
import com.threehmis.bjaj.widget.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.namee.permissiongen.PermissionGen;

/**
 * Created by zhengchengrong on 2017/9/4.
 */

public class HomeActivity extends BaseActivity<HomePresenter> implements IHomeView {


    @BindView(R.id.viewPager)
    NoScrollViewPager mViewPager;

    @BindView(R.id.tl_home)
    CommonTabLayout mTlHome;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {"首页", "工程分布","待办"};
    private int[] mIconUnselectIds = {
            R.drawable.homeg, R.drawable.mapg,
            R.drawable.daibang};
    private int[] mIconSelectIds = {
            R.drawable.home, R.drawable.map,
            R.drawable.daiban};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    protected void initInjector() {
        // Activity与presenter仅仅耦合在了一起，当需要改变presenter的构造方式时，需要修改这里的代码,所以用依赖注入
        DaggerHomeComponent.builder()
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }
    @Override
    protected void initViews() {
        mFragments.add(new MainFragment());
        mFragments.add(new MainMapFragment());
        mFragments.add(new NoticeFragment());
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tl_2();
        mTlHome.showMsg(2,17);
            // 缺少权限时, 进入权限配置页面
            //存储授权
            PermissionGen.with(HomeActivity.this)
                    .addRequestCode(100)
                    .permissions(
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA,
                            Manifest.permission.RECORD_AUDIO)
                    .request();

    }
    @Override public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                                     int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
    private void tl_2() {
        mTlHome.setTabData(mTabEntities);
        mTlHome.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        mViewPager.setAdapter(new HomeFragmentPagerAdapter(getSupportFragmentManager(),mFragments));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                mTlHome.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(2);
    }


    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
