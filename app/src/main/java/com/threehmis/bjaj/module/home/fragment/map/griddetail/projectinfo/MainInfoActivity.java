package com.threehmis.bjaj.module.home.fragment.map.griddetail.projectinfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.threehmis.bjaj.R;


public class MainInfoActivity extends AppCompatActivity {
    private Fragment fragmentGaikuang, fragmentTongji, fragmentBaogao;
    private RadioGroup radioGroup;
    private RadioButton baogao;
    private RadioButton tongji;
    private RadioButton gaikuang;
    private FrameLayout content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_info);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        baogao = (RadioButton) findViewById(R.id.baogao);
        tongji = (RadioButton) findViewById(R.id.tongji);
        gaikuang = (RadioButton) findViewById(R.id.gaikuang);
        content = (FrameLayout) findViewById(R.id.content);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                fragmentGaikuang = fm.findFragmentByTag(String.valueOf(gaikuang.getId()));

                fragmentTongji = fm.findFragmentByTag(String.valueOf(tongji.getId()));

                fragmentBaogao = fm.findFragmentByTag(String.valueOf(baogao.getId()));
                if (fragmentGaikuang != null) ft.hide(fragmentGaikuang);
                if (fragmentTongji != null) ft.hide(fragmentTongji);
                if (fragmentBaogao != null) ft.hide(fragmentBaogao);
                switch (checkedId) {
                    case R.id.gaikuang:

                        if (fragmentGaikuang == null) {
                            fragmentGaikuang = new GaiKuangFragment();
//                            fragmentMaps.setArguments(bundle);
                            ft.add(R.id.content, fragmentGaikuang, String.valueOf(gaikuang.getId()));
                        } else {
                            ft.show(fragmentGaikuang);
                        }
                        break;
                    case R.id.tongji:

                        if (fragmentTongji == null) {
                            fragmentTongji = new TongJiFragment();
                            ft.add(R.id.content, fragmentTongji, String.valueOf(tongji.getId()));
                        } else {
                            ft.show(fragmentTongji);
                        }
                        break;
                    case R.id.baogao:

                        if (fragmentBaogao == null) {
                            fragmentBaogao = new BaoGaoFragment();
                            ft.add(R.id.content, fragmentBaogao, String.valueOf(baogao.getId()));
                        } else {
                            ft.show(fragmentBaogao);
                        }
                        break;

                }
                ft.commit();
            }
        });
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = new GaiKuangFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.content, fragment, String.valueOf(gaikuang.getId())).commit();

        }
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton mTab = (RadioButton) radioGroup.getChildAt(i);
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentByTag(String.valueOf(mTab.getId()));
            FragmentTransaction ft = fm.beginTransaction();
            if (fragment != null) {
                if (!mTab.isChecked()) {
                    ft.hide(fragment);
                } else
                    ft.show(fragment);
            }
            ft.commit();
        }
    }
}
