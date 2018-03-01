package com.threehmis.bjaj.module.home.fragment.map.griddetail.projectinfo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.RxSchedulers;
import com.threehmis.bjaj.api.bean.request.ProjectInfoReq;
import com.threehmis.bjaj.api.bean.respon.AccessoryBeanRes;
import com.threehmis.bjaj.api.bean.respon.GetMyProjectInfoRsp;
import com.threehmis.bjaj.api.bean.respon.ProjectInfoOneRep;
import com.threehmis.bjaj.api.bean.respon.ProjectInfoTwoRep;
import com.threehmis.bjaj.module.base.BaseActivity;
import com.threehmis.bjaj.widget.NoScrollViewPager;
import com.threehmis.bjaj.widget.flowlayout.DLFlowLayout;
import com.threehmis.bjaj.widget.flowlayout.Flow;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by llz on 2018/2/24.
 */

public class ProjectInfoActivity extends BaseActivity implements View.OnClickListener {

    String projectId;
    String projectName;
    @BindView(R.id.tv_project_01)
    TextView mTvProject01;
    @BindView(R.id.tv_project_02)
    TextView mTvProject02;
    @BindView(R.id.tv_project_03)
    TextView mTvProject03;
    @BindView(R.id.tv_project_04)
    TextView mTvProject04;
    @BindView(R.id.vp_project_info)
    NoScrollViewPager mVpProjectInfo;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.other)
    TextView mOther;
    @BindView(R.id.tv_back)
    TextView mTvBack;
    private MyPagerAdapter mAdapter;
    private ArrayList<View> aList;
    private View view01;
    private TextView tv_01,tv_02,tv_03,tv_04,tv_05,tv_06,tv_07,tv_08,tv_09,tv_10,tv_11,tv_12,tv_13,tv_14;
    private View view02;
    private DLFlowLayout mContainer;
    private TextView tv_view02_01,tv_view02_02,tv_view02_03,tv_view02_04,tv_view02_05,tv_view02_06,tv_view02_07,tv_view02_08,tv_view02_09,tv_view02_10,tv_project_code01;
    private RelativeLayout rl_info_10,rl_info_05,rl_info_06,rl_info_07,rl_info_08;
    private View view_info_05,view_info_06,view_info_07,view_info_08;

    private ArrayList<Flow> view02MList;
    private ArrayList<ProjectInfoTwoRep> mProjectInfoTwoReps;
    private LinearLayout course_viewflipper;

    private View view03;
    private RecyclerView view03MRecyclerView;
    private BaseQuickAdapter mBaseQuickAdapter;
    ArrayList<String> threeDatas;

    private View view04;
    private RecyclerView view04MRecyclerView;
    private BaseQuickAdapter mBase04QuickAdapter;

    ArrayList<ProjectInfoTwoRep> twoReps;
    ArrayList<AccessoryBeanRes> fourReps;
    ArrayList<AccessoryBeanRes> fourRepsDatas;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_project_info;
    }

    @Override
    protected void initInjector() {

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initViews() {

        initTitle();
        initVpage();
        mTvProject01.setOnClickListener(this);
        mTvProject02.setOnClickListener(this);
        mTvProject03.setOnClickListener(this);
        mTvProject04.setOnClickListener(this);

        getDatas();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initVpage() {
        aList = new ArrayList<View>();
        LayoutInflater li = this.getLayoutInflater();
        view01 = li.inflate(R.layout.view_project_info_one, null, false);
        initView01();
        view02 = li.inflate(R.layout.view_project_info_two, null, false);
        initView02();
        view03 = li.inflate(R.layout.view_project_info_three, null, false);
        initView03();
        view04 = li.inflate(R.layout.view_project_info_four, null, false);
        initView04();
        aList.add(view01);
        aList.add(view02);
        aList.add(view03);
        aList.add(view04);

        mAdapter = new MyPagerAdapter(aList);
        mVpProjectInfo.setAdapter(mAdapter);
    }

    private void initView04() {
        view04MRecyclerView = view04.findViewById(R.id.rv_content_four);
        view04MRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBase04QuickAdapter = new BaseQuickAdapter<AccessoryBeanRes, BaseViewHolder>(R.layout.item_project_info, fourRepsDatas) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, final AccessoryBeanRes beanRes) {
                baseViewHolder.setText(R.id.tv_project_item01, beanRes.getFileName());
                baseViewHolder.getView(R.id.tv_project_item02).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ProjectInfoActivity.this,ProjectInfoImageVPActivity.class);
                        intent.putExtra(Const.IMAGEURL,beanRes.getFileUrl());
                        startActivity(intent);
                    }
                });
            }
        };
        view04MRecyclerView.setAdapter(mBase04QuickAdapter);
    }

    private void initView03() {
        view03MRecyclerView = view03.findViewById(R.id.rv_content);
        view03MRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        threeDatas = new ArrayList<String>();
        mBaseQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_project_info, threeDatas) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder,  String str) {
                baseViewHolder.setText(R.id.tv_project_item01, str);
            }
        };
        view03MRecyclerView.setAdapter(mBaseQuickAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initView02() {
        mContainer =  view02.findViewById(R.id.flowlayout);
        tv_view02_01 = view02.findViewById(R.id.tv_project_name02);
        tv_view02_02 = view02.findViewById(R.id.tv_project_code02);
        tv_view02_03 = view02.findViewById(R.id.tv_project_grade02);
        tv_view02_04 = view02.findViewById(R.id.tv_project_personname02);
        tv_view02_05 = view02.findViewById(R.id.tv_project_duty02);
        tv_view02_06 = view02.findViewById(R.id.tv_project_title02);
        tv_view02_07 = view02.findViewById(R.id.tv_project_regis_qualification02);
        tv_view02_08 = view02.findViewById(R.id.tv_project_title_no02);
        tv_view02_09 = view02.findViewById(R.id.tv_project_tel02);
        tv_view02_10 = view02.findViewById(R.id.tv_safe_produce_num02);
        rl_info_10 = view02.findViewById(R.id.rl_info_10);
        tv_project_code01 = view02.findViewById(R.id.tv_project_code01);
        rl_info_05 = view02.findViewById(R.id.rl_info_05);
        rl_info_06 = view02.findViewById(R.id.rl_info_06);
        rl_info_07 = view02.findViewById(R.id.rl_info_07);
        rl_info_08 = view02.findViewById(R.id.rl_info_08);
        view_info_05= view02.findViewById(R.id.view_info_05);
        view_info_06= view02.findViewById(R.id.view_info_06);
        view_info_07= view02.findViewById(R.id.view_info_07);
        view_info_08= view02.findViewById(R.id.view_info_08);
        course_viewflipper = view02.findViewById(R.id.course_viewflipper);
        mProjectInfoTwoReps = new ArrayList<ProjectInfoTwoRep>();
        fourReps = new ArrayList<AccessoryBeanRes>();
        fourRepsDatas = new ArrayList<AccessoryBeanRes>();
        view02MList = new ArrayList<Flow>();
        Flow flow = new Flow();
        flow.setFlowId("1");
        flow.setFlowName("建设单位");
        view02MList.add(flow);
        Flow flow2 = new Flow();
        flow2.setFlowId("2");
        flow2.setFlowName("施工总承包单位");
        view02MList.add(flow2);
        Flow flow3 = new Flow();
        flow3.setFlowId("3");
        flow3.setFlowName("监理单位");
        view02MList.add(flow3);
        Flow flow4 = new Flow();
        flow4.setFlowId("4");
        flow4.setFlowName("勘察单位");
        view02MList.add(flow4);
        Flow flow5 = new Flow();
        flow5.setFlowId("5");
        flow5.setFlowName("设计单位");
        view02MList.add(flow5);
        Flow flow6 = new Flow();
        flow6.setFlowId("6");
        flow6.setFlowName("劳务分包单位");
        view02MList.add(flow6);
        Flow flow7 = new Flow();
        flow7.setFlowId("7");
        flow7.setFlowName("专业分包单位");
        view02MList.add(flow7);
        mContainer.setFlowData(view02MList);
        mContainer.setDefaultSelects(new int[]{0});

        mContainer.setOnSelectListener(new DLFlowLayout.OnSelectListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onSelect(int i) {
                if(i!=-1) {
                 mContainer.setDefaultSelects(new int[]{i});
                    mContainer.setAllEnable();
                    for(int j=0;j<mContainer.getChildCount();j++){
                        CheckBox chk = (CheckBox)mContainer.getChildAt(j).findViewById(R.id.single_select_chk);
                        chk.setBackground(getResources().getDrawable(R.drawable.shape_admin_flow_color2));
                        chk.setTextColor(getResources().getColor(R.color.admin_main_select_text));
                    }
                    CheckBox chk = (CheckBox)mContainer.getChildAt(i).findViewById(R.id.single_select_chk);
                    chk.setBackground(getResources().getDrawable(R.drawable.shape_admin_flow_color));
                    chk.setTextColor(getResources().getColor(R.color.white));
                    Flow flow =  view02MList.get(i);
                    String type = flow.getFlowName();
                    rl_info_05.setVisibility(View.VISIBLE);
                    rl_info_06.setVisibility(View.VISIBLE);
                    rl_info_07.setVisibility(View.VISIBLE);
                    rl_info_08.setVisibility(View.VISIBLE);
                    view_info_05.setVisibility(View.VISIBLE);
                    view_info_06.setVisibility(View.VISIBLE);
                    view_info_07.setVisibility(View.VISIBLE);
                    view_info_08.setVisibility(View.VISIBLE);
                    course_viewflipper.setVisibility(View.GONE);

                    if(type.equals("建设单位")){
                        for(int j=0;j<mProjectInfoTwoReps.size();j++){
                            ProjectInfoTwoRep projectInfoTwoRep = mProjectInfoTwoReps.get(j);
                            if(projectInfoTwoRep.getUnitTypeNote().equals(type)){
                                course_viewflipper.setVisibility(View.VISIBLE);
                                tv_view02_01.setText(projectInfoTwoRep.getUnitName()==null?"":projectInfoTwoRep.getUnitName());
                                tv_view02_02.setText(projectInfoTwoRep.getGradeNo()==null?"":projectInfoTwoRep.getGradeNo());
                                tv_view02_03.setText(projectInfoTwoRep.getGrade()==null?"":projectInfoTwoRep.getGrade());
                                tv_view02_04.setText(projectInfoTwoRep.getPersonname()==null?"":projectInfoTwoRep.getPersonname());
                                tv_view02_05.setText(projectInfoTwoRep.getDuty()==null?"":projectInfoTwoRep.getDuty());
                                tv_view02_06.setText(projectInfoTwoRep.getTitle()==null?"":projectInfoTwoRep.getTitle());
                                tv_view02_07.setText(projectInfoTwoRep.getRegisQualification()==null?"":projectInfoTwoRep.getRegisQualification());
                                tv_view02_08.setText(projectInfoTwoRep.getTitleNo()==null?"":projectInfoTwoRep.getTitleNo());
                                tv_view02_09.setText(projectInfoTwoRep.getTel()==null?"":projectInfoTwoRep.getTel());
                                rl_info_10.setVisibility(View.GONE);
                            }
                        }
                    }
                    if(type.equals("施工总承包单位")){
                        for(int j=0;j<mProjectInfoTwoReps.size();j++){
                            ProjectInfoTwoRep projectInfoTwoRep = mProjectInfoTwoReps.get(j);
                            if(projectInfoTwoRep.getUnitTypeNote().equals(type)){
                                course_viewflipper.setVisibility(View.VISIBLE);
                                tv_view02_01.setText(projectInfoTwoRep.getUnitName()==null?"":projectInfoTwoRep.getUnitName());
                                tv_project_code01.setText("建筑施工企业资质证书编号");
                                tv_view02_02.setText(projectInfoTwoRep.getGradeNo()==null?"":projectInfoTwoRep.getGradeNo());
                                tv_view02_03.setText(projectInfoTwoRep.getGrade()==null?"":projectInfoTwoRep.getGrade());
                                tv_view02_04.setText(projectInfoTwoRep.getPersonname()==null?"":projectInfoTwoRep.getPersonname());
                                tv_view02_05.setText(projectInfoTwoRep.getDuty()==null?"":projectInfoTwoRep.getDuty());
                                tv_view02_06.setText(projectInfoTwoRep.getTitle()==null?"":projectInfoTwoRep.getTitle());
                                tv_view02_07.setText(projectInfoTwoRep.getRegisQualification()==null?"":projectInfoTwoRep.getRegisQualification());
                                tv_view02_08.setText(projectInfoTwoRep.getTitleNo()==null?"":projectInfoTwoRep.getTitleNo());
                                tv_view02_09.setText(projectInfoTwoRep.getTel()==null?"":projectInfoTwoRep.getTel());
                                rl_info_10.setVisibility(View.VISIBLE);
                                tv_view02_10.setText(projectInfoTwoRep.getSafeProduceNum()==null?"":projectInfoTwoRep.getSafeProduceNum());
                            }
                        }
                    }
                    if(type.equals("监理单位")){
                        for(int j=0;j<mProjectInfoTwoReps.size();j++){
                            ProjectInfoTwoRep projectInfoTwoRep = mProjectInfoTwoReps.get(j);
                            if(projectInfoTwoRep.getUnitTypeNote().equals(type)){
                                course_viewflipper.setVisibility(View.VISIBLE);
                                tv_view02_01.setText(projectInfoTwoRep.getUnitName()==null?"":projectInfoTwoRep.getUnitName());
                                tv_project_code01.setText("工程监理企业资质证书");
                                tv_view02_02.setText(projectInfoTwoRep.getGradeNo()==null?"":projectInfoTwoRep.getGradeNo());
                                tv_view02_03.setText(projectInfoTwoRep.getGrade()==null?"":projectInfoTwoRep.getGrade());
                                tv_view02_04.setText(projectInfoTwoRep.getPersonname()==null?"":projectInfoTwoRep.getPersonname());
                                tv_view02_05.setText(projectInfoTwoRep.getDuty()==null?"":projectInfoTwoRep.getDuty());
                                tv_view02_06.setText(projectInfoTwoRep.getTitle()==null?"":projectInfoTwoRep.getTitle());
                                tv_view02_07.setText(projectInfoTwoRep.getRegisQualification()==null?"":projectInfoTwoRep.getRegisQualification());
                                tv_view02_08.setText(projectInfoTwoRep.getTitleNo()==null?"":projectInfoTwoRep.getTitleNo());
                                tv_view02_09.setText(projectInfoTwoRep.getTel()==null?"":projectInfoTwoRep.getTel());
                                rl_info_10.setVisibility(View.GONE);
                            }
                        }
                    }
                    if(type.equals("劳务分包单位")||type.equals("专业分包单位") ){
                        for(int j=0;j<mProjectInfoTwoReps.size();j++){
                            ProjectInfoTwoRep projectInfoTwoRep = mProjectInfoTwoReps.get(j);
                            if(projectInfoTwoRep.getUnitTypeNote().equals(type)){
                                course_viewflipper.setVisibility(View.VISIBLE);
                                tv_view02_01.setText(projectInfoTwoRep.getUnitName()==null?"":projectInfoTwoRep.getUnitName());
                                tv_project_code01.setText("建筑施工企业资质证书编号");
                                tv_view02_02.setText(projectInfoTwoRep.getGradeNo()==null?"":projectInfoTwoRep.getGradeNo());
                                tv_view02_03.setText(projectInfoTwoRep.getGrade()==null?"":projectInfoTwoRep.getGrade());
                                tv_view02_04.setText(projectInfoTwoRep.getPersonname()==null?"":projectInfoTwoRep.getPersonname());
                                tv_view02_05.setText(projectInfoTwoRep.getDuty()==null?"":projectInfoTwoRep.getDuty());
                                tv_view02_06.setText(projectInfoTwoRep.getTitle()==null?"":projectInfoTwoRep.getTitle());
                                tv_view02_07.setText(projectInfoTwoRep.getRegisQualification()==null?"":projectInfoTwoRep.getRegisQualification());
                                tv_view02_08.setText(projectInfoTwoRep.getTitleNo()==null?"":projectInfoTwoRep.getTitleNo());
                                tv_view02_09.setText(projectInfoTwoRep.getTel()==null?"":projectInfoTwoRep.getTel());
                                rl_info_10.setVisibility(View.GONE);
                            }
                        }
                    }
                    if(type.equals("勘察单位")|| type.equals("设计单位")){
                        rl_info_05.setVisibility(View.GONE);
                        rl_info_06.setVisibility(View.GONE);
                        rl_info_07.setVisibility(View.GONE);
                        rl_info_08.setVisibility(View.GONE);
                        view_info_05.setVisibility(View.GONE);
                        view_info_06.setVisibility(View.GONE);
                        view_info_07.setVisibility(View.GONE);
                        view_info_08.setVisibility(View.GONE);
                        for(int j=0;j<mProjectInfoTwoReps.size();j++){
                            ProjectInfoTwoRep projectInfoTwoRep = mProjectInfoTwoReps.get(j);
                            if(projectInfoTwoRep.getUnitTypeNote().equals(type)){
                                course_viewflipper.setVisibility(View.VISIBLE);
                                tv_view02_01.setText(projectInfoTwoRep.getUnitName()==null?"":projectInfoTwoRep.getUnitName());
                                tv_project_code01.setText("建筑施工企业资质证书编号");
                                tv_view02_02.setText(projectInfoTwoRep.getGradeNo()==null?"":projectInfoTwoRep.getGradeNo());
                                tv_view02_03.setText(projectInfoTwoRep.getGrade()==null?"":projectInfoTwoRep.getGrade());
                                tv_view02_04.setText(projectInfoTwoRep.getPersonname()==null?"":projectInfoTwoRep.getPersonname());
                                tv_view02_05.setText(projectInfoTwoRep.getDuty()==null?"":projectInfoTwoRep.getDuty());
                                tv_view02_06.setText(projectInfoTwoRep.getTitle()==null?"":projectInfoTwoRep.getTitle());
                                tv_view02_07.setText(projectInfoTwoRep.getRegisQualification()==null?"":projectInfoTwoRep.getRegisQualification());
                                tv_view02_08.setText(projectInfoTwoRep.getTitleNo()==null?"":projectInfoTwoRep.getTitleNo());
                                tv_view02_09.setText(projectInfoTwoRep.getTel()==null?"":projectInfoTwoRep.getTel());
                                rl_info_10.setVisibility(View.GONE);
                            }
                        }
                    }
                }

}

            @Override
            public void onOutLimit() {

            }
        });


    }



    private void initView01() {
        tv_01 = view01.findViewById(R.id.tv_project_code);
        tv_02 = view01.findViewById(R.id.tv_project_name);
        tv_03 = view01.findViewById(R.id.tv_project_num);
        tv_04 = view01.findViewById(R.id.tv_sgxkzh);
        tv_05 = view01.findViewById(R.id.tv_bj_date);
        tv_06 = view01.findViewById(R.id.tv_region);
        tv_07 = view01.findViewById(R.id.tv_address);
        tv_08 = view01.findViewById(R.id.tv_project_type);
        tv_09 = view01.findViewById(R.id.tv_area);
        tv_10 = view01.findViewById(R.id.tv_project_cost);
        tv_11 = view01.findViewById(R.id.tv_high);
        tv_12 = view01.findViewById(R.id.tv_defined_note);
        tv_13 = view01.findViewById(R.id.tv_tfzy_date);
        tv_14 = view01.findViewById(R.id.tv_defined_note_10);


    }

    private void initTitle() {
        mTvTitle.setText(R.string.project_info_title);
        mTvBack.setVisibility(View.VISIBLE);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void getDatas() {
        Bundle bundle = this.getIntent().getExtras();// 获取传递过来的封装了数据的Bundle
        projectId = bundle.getString(Const.PROJECTID);// 获取name_Key对应的Value
        projectName = bundle.getString(Const.PROJECTNAME);// 获取name_Key对应的Value
        final ProjectInfoReq req = new ProjectInfoReq();
        req.setProjectId(projectId);

        Observable<GetMyProjectInfoRsp> observable = RetrofitFactory.getInstance().getProjectProfile(req);
        observable.compose(RxSchedulers.<GetMyProjectInfoRsp>compose(
        )).subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onNext(Object o) {
                GetMyProjectInfoRsp getMyProjectInfoRsp = (GetMyProjectInfoRsp) o;
                String str = new Gson().toJson(getMyProjectInfoRsp.getProjectList().get(0));
                ArrayList<ProjectInfoOneRep> res = new Gson().fromJson( new Gson().toJson(getMyProjectInfoRsp.getProjectList().get(0)),ArrayList.class);
                ProjectInfoOneRep projectInfoOneRep = new Gson().fromJson(new Gson().toJson(res.get(0)),ProjectInfoOneRep.class);
                tv_01.setText(projectInfoOneRep.getProjectCode()==null?"":projectInfoOneRep.getProjectCode());
                tv_02.setText(projectInfoOneRep.getProjectName()==null?"":projectInfoOneRep.getProjectName());
                tv_03.setText(projectInfoOneRep.getProjectNum()==null?"":projectInfoOneRep.getProjectNum());
                tv_04.setText(projectInfoOneRep.getSgxkzh()==null?"":projectInfoOneRep.getSgxkzh());
                tv_05.setText(projectInfoOneRep.getBjDate()==null?"":projectInfoOneRep.getBjDate());
                tv_06.setText(projectInfoOneRep.getRegion()==null?"":projectInfoOneRep.getRegion());
                tv_07.setText(projectInfoOneRep.getAddress()==null?"":projectInfoOneRep.getAddress());
                tv_08.setText(projectInfoOneRep.getProjectType()==null?"":projectInfoOneRep.getProjectType());
                tv_09.setText(projectInfoOneRep.getArea()+"");
                tv_10.setText(projectInfoOneRep.getProjectCost()+"");
                tv_11.setText(projectInfoOneRep.getHigh()+"");
                tv_12.setText(projectInfoOneRep.getContractPeriod()==null?"":projectInfoOneRep.getContractPeriod());
                tv_13.setText(projectInfoOneRep.getTfzyDate()==null?"":projectInfoOneRep.getTfzyDate());
                tv_14.setText(projectInfoOneRep.getSingleProject()==null?"":projectInfoOneRep.getSingleProject());

                twoReps = (ArrayList<ProjectInfoTwoRep>) getMyProjectInfoRsp.getProjectList().get(1);


                tv_view02_10.setVisibility(View.GONE);
                for (int i = 0; i < twoReps.size(); i++) {
                    ProjectInfoTwoRep rep = new Gson().fromJson(new Gson().toJson(twoReps.get(i)), ProjectInfoTwoRep.class);
                        if(rep.getUnitTypeNote().equals("建设单位")){
                            tv_view02_01.setText(rep.getUnitName()==null?"":rep.getUnitName());
                            tv_view02_02.setText(rep.getGradeNo()==null?"":rep.getGradeNo());
                            tv_view02_03.setText(rep.getGrade()==null?"":rep.getGrade());
                            tv_view02_04.setText(rep.getPersonname()==null?"":rep.getPersonname());
                            tv_view02_05.setText(rep.getDuty()==null?"":rep.getDuty());
                            tv_view02_06.setText(rep.getTitle()==null?"":rep.getTitle());
                            tv_view02_07.setText(rep.getRegisQualification()==null?"":rep.getRegisQualification());
                            tv_view02_08.setText(rep.getTitleNo()==null?"":rep.getTitleNo());
                            tv_view02_09.setText(rep.getTel()==null?"":rep.getTel());
                            tv_view02_10.setVisibility(View.GONE);
                    }
                    mProjectInfoTwoReps.add(rep);
                }
                threeDatas.addAll((ArrayList<String>) getMyProjectInfoRsp.getProjectList().get(2));
                // AccessoryBeanRes fourReps
                fourReps = (ArrayList<AccessoryBeanRes>) getMyProjectInfoRsp.getProjectList().get(3);
                for (int i = 0; i < fourReps.size(); i++) {
                    AccessoryBeanRes rep = new Gson().fromJson(new Gson().toJson(fourReps.get(i)), AccessoryBeanRes.class);
                    fourRepsDatas.add(rep);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_project_01:
                mTvProject01.setBackground(this.getResources().getDrawable(R.drawable.shape_admin_main_half_color));
                mTvProject01.setTextColor(this.getResources().getColor(R.color.white));
                mTvProject02.setBackground(this.getResources().getDrawable(R.drawable.shape_admin_white_half_color_no_stroke));
                mTvProject02.setTextColor(this.getResources().getColor(R.color.main_color));
                mTvProject03.setBackground(this.getResources().getDrawable(R.drawable.shape_admin_white_half_color_no_stroke));
                mTvProject03.setTextColor(this.getResources().getColor(R.color.main_color));
                mTvProject04.setBackground(this.getResources().getDrawable(R.drawable.shape_admin_white_half_color));
                mTvProject04.setTextColor(this.getResources().getColor(R.color.main_color));
                mVpProjectInfo.setCurrentItem(0);
                break;
            case R.id.tv_project_02:
                mTvProject01.setBackground(this.getResources().getDrawable(R.drawable.shape_admin_white_half_color2));
                mTvProject01.setTextColor(this.getResources().getColor(R.color.main_color));
                mTvProject02.setBackground(this.getResources().getDrawable(R.drawable.shape_admin_main_half_color_no_stroke));
                mTvProject02.setTextColor(this.getResources().getColor(R.color.white));
                mTvProject03.setBackground(this.getResources().getDrawable(R.drawable.shape_admin_white_half_color_no_stroke));
                mTvProject03.setTextColor(this.getResources().getColor(R.color.main_color));
                mTvProject04.setBackground(this.getResources().getDrawable(R.drawable.shape_admin_white_half_color));
                mTvProject04.setTextColor(this.getResources().getColor(R.color.main_color));
                mVpProjectInfo.setCurrentItem(1);
                break;
            case R.id.tv_project_03:
                mTvProject01.setBackground(this.getResources().getDrawable(R.drawable.shape_admin_white_half_color2));
                mTvProject01.setTextColor(this.getResources().getColor(R.color.main_color));
                mTvProject02.setBackground(this.getResources().getDrawable(R.drawable.shape_admin_white_half_color_no_stroke));
                mTvProject02.setTextColor(this.getResources().getColor(R.color.main_color));
                mTvProject03.setBackground(this.getResources().getDrawable(R.drawable.shape_admin_main_half_color_no_stroke));
                mTvProject03.setTextColor(this.getResources().getColor(R.color.white));
                mTvProject04.setBackground(this.getResources().getDrawable(R.drawable.shape_admin_white_half_color));
                mTvProject04.setTextColor(this.getResources().getColor(R.color.main_color));
                mVpProjectInfo.setCurrentItem(2);
                break;
            case R.id.tv_project_04:
                mTvProject01.setBackground(this.getResources().getDrawable(R.drawable.shape_admin_white_half_color2));
                mTvProject01.setTextColor(this.getResources().getColor(R.color.main_color));
                mTvProject02.setBackground(this.getResources().getDrawable(R.drawable.shape_admin_white_half_color_no_stroke));
                mTvProject02.setTextColor(this.getResources().getColor(R.color.main_color));
                mTvProject03.setBackground(this.getResources().getDrawable(R.drawable.shape_admin_white_half_color_no_stroke));
                mTvProject03.setTextColor(this.getResources().getColor(R.color.main_color));
                mTvProject04.setBackground(this.getResources().getDrawable(R.drawable.shape_admin_main_half_right_color));
                mTvProject04.setTextColor(this.getResources().getColor(R.color.white));
                mVpProjectInfo.setCurrentItem(3);
                break;
        }
    }
    public class MyPagerAdapter extends PagerAdapter {
        private ArrayList<View> viewLists;
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
