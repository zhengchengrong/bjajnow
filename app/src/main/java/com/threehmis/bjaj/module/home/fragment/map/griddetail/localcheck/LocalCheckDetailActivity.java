package com.threehmis.bjaj.module.home.fragment.map.griddetail.localcheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.BaseObserver;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.RxSchedulers;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.TaskCheckAddReq;
import com.threehmis.bjaj.api.bean.respon.ProjectTaskCheckRsp;
import com.threehmis.bjaj.api.bean.respon.SupervisionPlanFirstRsp;
import com.threehmis.bjaj.module.base.BaseActivity;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.taskcheck.TaskCheckAddActivity;
import com.threehmis.bjaj.module.home.fragment.personcenter.PersonCenterChildDetailActivity;
import com.threehmis.bjaj.module.home.fragment.personcenter.PersonCenterChildLowDetailActivity;
import com.threehmis.bjaj.widget.EmptyLayout;
import com.vondear.rxtools.RxSPUtils;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import jp.shts.android.library.TriangleLabelView;

/**
 * Created by llz on 2018/2/27.
 */

public class LocalCheckDetailActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.other)
    TextView mOther;
    @BindView(R.id.tv_back)
    TextView mTvBack;
    @BindView(R.id.tv_01)
    TextView mTv01;
    @BindView(R.id.tv_02)
    TextView mTv02;
    @BindView(R.id.tv_03)
    TextView mTv03;
    @BindView(R.id.rb_01)
    RadioButton mRb01;
    @BindView(R.id.rb_02)
    RadioButton mRb02;
    @BindView(R.id.rb_03)
    RadioButton mRb03;
    @BindView(R.id.rg_basic)
    RadioGroup mRgBasic;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    @BindView(R.id.et_01)
    EditText mEt01;
    @BindView(R.id.tv_commit)
    TextView mTvCommit;
    private String rbBasic;

    private boolean flag;
    private ProjectTaskCheckRsp mProjectTaskCheckRsp;
    private BaseQuickAdapter mBaseQuickAdapter;
    private ArrayList<ProjectTaskCheckRsp.CheckDivisionVOSetBean> datas;
    private List<String> listId;
    private List<String> listCheckTaskId;
    private List<String> listSingleProject;
    private List<String> listCheckManId;
    private List<String> listCheckMan;
    private List<String> listCheckType;
    private List<String> listCheckContent;
    private List<String> listCheckStatus;
    private List<String> listCheckResult;
    private boolean isCommit;
    private String projectId;
    private String projectName;
    private String projectNum;
    int position = 0;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_local_check_detail;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        listId = new ArrayList<String>();
        listCheckTaskId = new ArrayList<String>();
        listSingleProject = new ArrayList<String>();
        listCheckManId = new ArrayList<String>();
        listCheckMan = new ArrayList<String>();
        listCheckType = new ArrayList<String>();
        listCheckContent = new ArrayList<String>();
        listCheckStatus = new ArrayList<String>();
        listCheckResult= new ArrayList<String>();
        projectId = RxSPUtils.getString(this, Const.PROJECTID);
        projectName = RxSPUtils.getString(this, Const.PROJECTNAME);
        projectNum = RxSPUtils.getString(this,Const.PROJECTNUM);
        initTitle();
        flag = getIntent().getBooleanExtra(Const.FLAG,false);
        mProjectTaskCheckRsp = (ProjectTaskCheckRsp) getIntent().getSerializableExtra(Const.BEAN);
        mTv01.setText(mProjectTaskCheckRsp.getCheckNum());
        mTv02.setText(mProjectTaskCheckRsp.getCheckDate());
        mTv03.setText(mProjectTaskCheckRsp.getCheckMen());
        if (mProjectTaskCheckRsp.getCheckBasis().equals("计划")) {
            mRb01.setChecked(true);
        }
        if (mProjectTaskCheckRsp.getCheckBasis().equals("专项")) {
            mRb02.setChecked(true);
        }
        if (mProjectTaskCheckRsp.getCheckBasis().equals("其他")) {
            mRb03.setChecked(true);
        }
        mRgBasic.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_01) {
                    rbBasic = "计划";
                } else if (i == R.id.rb_02) {
                    rbBasic = "专项";
                } else {
                    rbBasic = "其他";
                }
            }
        });
        mRvContent.setLayoutManager(new LinearLayoutManager(this));
        datas = new ArrayList<ProjectTaskCheckRsp.CheckDivisionVOSetBean>();
        datas.addAll(mProjectTaskCheckRsp.getCheckDivisionVOSet());

        mEt01.setText(mProjectTaskCheckRsp.getOtherProblem()==null?"":mProjectTaskCheckRsp.getOtherProblem());

        mBaseQuickAdapter = new BaseQuickAdapter<ProjectTaskCheckRsp.CheckDivisionVOSetBean, BaseViewHolder>(R.layout.item_local_check_detail, datas) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, final ProjectTaskCheckRsp.CheckDivisionVOSetBean rowsBean) {
                TriangleLabelView triangleLabelView =    baseViewHolder.getView(R.id.tlv_01);
                triangleLabelView.setPrimaryText(baseViewHolder.getAdapterPosition()+1+"");
                baseViewHolder.setText(R.id.tv_01, rowsBean.getCheckType());
                baseViewHolder.setText(R.id.tv_02,TextUtils.isEmpty( rowsBean.getCheckContent())?"空":rowsBean.getCheckContent());
                baseViewHolder.getView(R.id.tv_02).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(LocalCheckDetailActivity.this,PersonCenterChildLowDetailActivity.class);
                        intent.putExtra(Const.PK,rowsBean.getCheckTaskID());
                        startActivity(intent);
                    }
                });
                baseViewHolder.setText(R.id.tv_03, rowsBean.getSingleProject());
                baseViewHolder.setText(R.id.tv_04, rowsBean.getCheckPart());
                final EditText editText = baseViewHolder.getView(R.id.et_issue);
                RadioButton radioButton2 =  baseViewHolder.getView(R.id.rb_02);
                if(!TextUtils.isEmpty(rowsBean.getCheckResult())&&isCommit==false){
                    radioButton2.setChecked(true);
                    editText.setText(rowsBean.getCheckResult());
                    editText.setVisibility(View.VISIBLE);
                }
                RadioGroup radioGroup = baseViewHolder.getView(R.id.rg_basic);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        switch (checkedId) {
                            case R.id.rb_01:// 未见异常
                                editText.setText("");
                                editText.setVisibility(View.GONE);
                                break;
                            case R.id.rb_02: // 问题项
                                editText.setVisibility(View.VISIBLE);
                                break;
                        }
                    }
                });
                if(isCommit==true) {
                    listCheckResult.add(editText.getText().toString());
                    listId.add(rowsBean.getId());
                    listCheckTaskId.add(rowsBean.getCheckTaskID());
                    listSingleProject.add(rowsBean.getSingleProject());
                    listCheckManId.add(rowsBean.getCheckManId());
                    listCheckMan.add(rowsBean.getCheckMan());
                    listCheckType.add(rowsBean.getCheckType());
                    listCheckContent.add(rowsBean.getCheckContent());
                    listCheckStatus.add("1");
                }

                position++;
                if(position == datas.size()&&isCommit==true){
                    position = 0;
                    TaskCheckAddReq req = new TaskCheckAddReq();
                    req.setCheckNum(mProjectTaskCheckRsp.getCheckNum());
                    req.setListCheckResult(listCheckResult);
                    req.setCheckDate(mProjectTaskCheckRsp.getCheckDate());
                    req.setProjectId(mProjectTaskCheckRsp.getProjectId());
                    req.setProjectName(mProjectTaskCheckRsp.getProjectName());
                    req.setOtherProblem(mEt01.getText().toString()==null?"":mEt01.getText().toString());
                    req.setListId(listId);
                    req.setId(mProjectTaskCheckRsp.getId());
                    req.setListCheckTaskId(listCheckTaskId);
                    req.setListSingleProject(listSingleProject);
                    req.setListCheckManId(listCheckManId);
                    req.setListCheckMan(listCheckMan);
                    req.setListCheckType(listCheckType);
                    req.setListCheckContent(listCheckContent);
                    req.setListCheckStatus(listCheckStatus);
                    req.setVersionId("3HJD0035");
                    req.setCheckStatus("1");
                    req.setProjectNum(mProjectTaskCheckRsp.getProjectNum());
                    req.setUpdateDate(mProjectTaskCheckRsp.getUpdateDate());
                    req.setCreateDate(mProjectTaskCheckRsp.getCreateDate());
                    req.setCheckMen(mProjectTaskCheckRsp.getCheckMen());
                    req.setCreateMan(mProjectTaskCheckRsp.getCreateMan());
                    if(mProjectTaskCheckRsp.getCheckBasis().equals("计划")){
                        req.setCheckBasis("计划");
                    }else if(mProjectTaskCheckRsp.getCheckBasis().equals("专项")){
                        req.setCheckBasis("专项");
                    }else{
                        req.setCheckBasis("其他");
                    }
                    Observable<BaseBeanRsp<SupervisionPlanFirstRsp>> observable = RetrofitFactory.getInstance().editCheckTask(req);
                    observable.compose(RxSchedulers.<BaseBeanRsp<SupervisionPlanFirstRsp>>compose(
                    )).subscribe(new BaseObserver<SupervisionPlanFirstRsp>() {
                        @Override
                        protected void onHandleSuccess(BaseBeanRsp<SupervisionPlanFirstRsp> beanRsp) {
                            RxToast.showToast(beanRsp.getResult());
                            LocalCheckDetailActivity.this.finish();
                        }
                        @Override
                        protected void onHandleEmpty(BaseBeanRsp<SupervisionPlanFirstRsp> beanRsp) {
                            RxToast.showToast(beanRsp.getResult());
                        }
                    });
                }

            }
        };
        mRvContent.setAdapter(mBaseQuickAdapter);
        if(flag == true){
            mTvCommit.setVisibility(View.GONE);
            mEt01.setVisibility(View.GONE);
        }else{
            mTvCommit.setVisibility(View.VISIBLE);
            mEt01.setVisibility(View.VISIBLE);
        }
        mTvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCommit = true;
                position = 0;
                mBaseQuickAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    private void initTitle() {
        mTvTitle.setText("现场检查");
        mTvBack.setVisibility(View.VISIBLE);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
