/*
 * Copyright (c) zcr 2018.
 */

package com.threehmis.bjaj.module.home.fragment.map.griddetail.taskcheck;

import android.app.DatePickerDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.BaseObserver;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.RxSchedulers;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.CommonProjectIdReq;
import com.threehmis.bjaj.api.bean.request.TaskCheckAddBeanReq;
import com.threehmis.bjaj.api.bean.request.TaskCheckAddReq;
import com.threehmis.bjaj.api.bean.respon.ProjectTaskCheckRsp;
import com.threehmis.bjaj.api.bean.respon.SupervisionPlanFirstRsp;
import com.threehmis.bjaj.module.base.BaseActivity;
import com.threehmis.bjaj.utils.CDUtil;
import com.threehmis.bjaj.widget.EmptyLayout;
import com.vondear.rxtools.RxLogUtils;
import com.vondear.rxtools.RxSPUtils;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * Created by llz on 2018/2/27.
 */

public class TaskCheckEditActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.other)
    TextView mOther;
    @BindView(R.id.tv_back)
    TextView mTvBack;

    @BindView(R.id.rb_01)
    RadioButton mRb01;
    @BindView(R.id.rb_02)
    RadioButton mRb02;
    @BindView(R.id.rb_03)
    RadioButton mRb03;
    @BindView(R.id.rg_basic)
    RadioGroup mRgBasic;

    @BindView(R.id.tv_add)
    TextView mTvAdd;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    @BindView(R.id.tv_commit)
    TextView mTvCommit;
    @BindView(R.id.et_01)
    EditText mEt01;
    @BindView(R.id.tv_01)
    TextView mTv01;
    @BindView(R.id.tv_02)
    TextView mTv02;

    private ItemDragAdapter mItemDragAdapter;
    private ArrayList<TaskCheckAddBeanReq> mProjectStatusRsps;
    boolean wrapInScrollView = true;
    private String projectId;
    private String projectName;
    private String projectNum;

    private List<String> listId;
    private List<String> listCheckTaskId;
    private List<String> listSingleProject;
    private List<String> listCheckManId;
    private List<String> listCheckMan;
    private List<String> listCheckType;
    private List<String> listCheckContent;
    private List<String> listCheckStatus;
    // 修改
    List<ProjectTaskCheckRsp.CheckDivisionVOSetBean> checkDivisionVOSet ;
    private StringBuffer mStringBuffer = new StringBuffer();
    Set<String> set = new HashSet<String>();
    Set<String> set01 = new HashSet<String>();

    private boolean isCommit;
    private boolean isAdd;


    private String checkNum="";
    private String checkDate="";
    private String checkBase="";
    private String id="";
    private boolean flag;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_task_check_edit;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        checkBase = getIntent().getStringExtra(Const.CHECKBASIS);
        checkNum = getIntent().getStringExtra(Const.CEHCKNUM);
        checkDate = getIntent().getStringExtra(Const.DATES);
        id = getIntent().getStringExtra(Const.ID);
        flag = getIntent().getBooleanExtra(Const.FLAG,false);
        mEt01.setText(checkNum);
        mTv01.setText(checkDate);
        if(checkBase.equals("计划")){
            mRb01.setChecked(true);
        }else if(checkBase.equals("专项")){
            mRb02.setChecked(true);
        }else{
            mRb03.setChecked(true);
        }

        initTitle();
        getData01();
        getData02();
        getData03("");
        listId = new ArrayList<String>();
        listCheckTaskId = new ArrayList<String>();
        listSingleProject = new ArrayList<String>();
        listCheckManId = new ArrayList<String>();
        listCheckMan = new ArrayList<String>();
        listCheckType = new ArrayList<String>();
        listCheckContent = new ArrayList<String>();
        listCheckStatus = new ArrayList<String>();
        projectId = RxSPUtils.getString(this, Const.PROJECTID);
        projectName = RxSPUtils.getString(this, Const.PROJECTNAME);
        projectNum = RxSPUtils.getString(this,Const.PROJECTNUM);
        mProjectStatusRsps = new ArrayList<TaskCheckAddBeanReq>();
        mItemDragAdapter = new ItemDragAdapter(mProjectStatusRsps);
        mTvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskCheckAddBeanReq taskCheckAddBeanReq = new TaskCheckAddBeanReq();
                mProjectStatusRsps.add(taskCheckAddBeanReq);
                isAdd = true;
                mItemDragAdapter.notifyDataSetChanged();
            }
        });
        mRvContent.setLayoutManager(new LinearLayoutManager(this));
        mRvContent.setAdapter(mItemDragAdapter);
    /*    ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mItemDragAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(mRvContent);
        mItemDragAdapter.enableSwipeItem();*/
        mTvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCommit = true;
             /*   listId.clear();
                listCheckTaskId.clear();*/
                listSingleProject.clear();
                listCheckManId.clear();
                listCheckMan.clear();
                listCheckType.clear();
                listCheckContent.clear();
                listCheckStatus.clear();
                RxToast.showToast("提交");
            if(TextUtils.isEmpty(mEt01.getText().toString())){
                    RxToast.showToast("请输入任务编号!");
                    return;
                }
                if(TextUtils.isEmpty(mTv01.getText().toString())){
                    RxToast.showToast("请输入检查日期!");
                    return;
                }
             //   mTv02.setText(mStringBuffer.substring(0, mStringBuffer.length() - 1));
                mItemDragAdapter.notifyDataSetChanged();

            }
        });

        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        // 检查日期
        mTv01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(TaskCheckEditActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
            }
        });
        //修改初始化
            checkDivisionVOSet = (ArrayList<ProjectTaskCheckRsp.CheckDivisionVOSetBean>) CDUtil
                    .readObject(Const.ROWSBEAN);
            if (checkDivisionVOSet != null && checkDivisionVOSet.size() > 0) {
                for (int i = 0; i < checkDivisionVOSet.size(); i++) {
                    TaskCheckAddBeanReq taskTemp = new TaskCheckAddBeanReq();
                    taskTemp.setCreateMan(checkDivisionVOSet.get(i).getCheckMan());
                    taskTemp.setCreateManId(checkDivisionVOSet.get(i).getCheckManId());
                    taskTemp.setListSingleProject(checkDivisionVOSet.get(i).getSingleProject()); //问题
                    taskTemp.setListSingleProjectId(checkDivisionVOSet.get(i).getSingleProject());
                    taskTemp.setCheckType(checkDivisionVOSet.get(i).getCheckType());
                    taskTemp.setCheckContetnt(checkDivisionVOSet.get(i).getCheckContent());
                    taskTemp.setCheckStatus(checkDivisionVOSet.get(i).getCheckStatus());
                    mProjectStatusRsps.add(taskTemp);
                    mItemDragAdapter.notifyDataSetChanged();
                    listId.add(checkDivisionVOSet.get(i).getId());
                    listCheckTaskId.add(checkDivisionVOSet.get(i).getCheckTaskID());

                }
        }
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    private void initTitle() {
        mTvTitle.setText("任务指派");
        mTvBack.setVisibility(View.VISIBLE);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    MaterialDialog dialog01;
    MaterialDialog dialog02;
    MaterialDialog dialog03;
    BaseQuickAdapter mBaseQuickAdapter2;
    public class ItemDragAdapter extends BaseItemDraggableAdapter<TaskCheckAddBeanReq, BaseViewHolder> {
        public ItemDragAdapter(ArrayList<TaskCheckAddBeanReq> data) {
            super(R.layout.item_task_check_edit_01, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, TaskCheckAddBeanReq item) {
            final TextView tv_01 = baseViewHolder.getView(R.id.et_01);
            final TextView tv_02 = baseViewHolder.getView(R.id.et_02);
            final TextView tv_03 = baseViewHolder.getView(R.id.et_03);
            final TextView list_siginle_id = baseViewHolder.getView(R.id.list_siginle_id);
            final  TextView list_check_man_id = baseViewHolder.getView(R.id.list_check_man_id);
            final  TextView list_check_type = baseViewHolder.getView(R.id.list_check_type);

            //修改
            if(flag == true&&isCommit==false&&isAdd==false) {
                tv_01.setText(item.getCreateMan());
                tv_02.setText(item.getListSingleProject());
                tv_03.setText(item.getCheckContetnt());
                list_siginle_id.setText(item.getListSingleProjectId());
                list_check_man_id.setText(item.getCreateManId());
                list_check_type.setText(item.getCheckType());
            }
            RxLogUtils.d(tv_01.getText().toString() + ":" + tv_02.getText().toString() + ":" + tv_03.getText().toString());
     /*       if(!TextUtils.isEmpty(tv_01.getText().toString())) {
                set.add(tv_01.getText().toString() );
                Iterator<String> it = set.iterator();
                mStringBuffer.setLength(0);
                while (it.hasNext()) {
                    String str = it.next();
                    mStringBuffer.append(str+ ",");
                }

            }*/
            if(isCommit==true) { //只有提交的通知才加数据
           /*     listId.add("");
                listCheckTaskId.add("");*/
                listSingleProject.add(list_siginle_id.getText().toString());
                listCheckManId.add(list_check_man_id.getText().toString());
                listCheckMan.add(tv_01.getText().toString());
                listCheckType.add(list_check_type.getText().toString());
                listCheckContent.add(tv_03.getText().toString());
                listCheckStatus.add("0");

                // 遍历旧集合,获取得到每一个元素
                Iterator it = listCheckMan.iterator();
                String str="";
                while (it.hasNext()) {
                    String s = (String) it.next();
                    // 拿这个元素到新集合去找，看有没有
                    set01.add(s);
                }
                Iterator<String> it2 = set01.iterator();
                while (it2.hasNext()) {
                     str += it2.next()+",";


                }
                mTv02.setText(str.substring(0,str.length()-1));
            }
            if(baseViewHolder.getAdapterPosition() == mProjectStatusRsps.size()-1&&isCommit==true){
                TaskCheckAddReq req = new TaskCheckAddReq();
                req.setCheckNum(mEt01.getText().toString());
                req.setCheckDate(mTv01.getText().toString());
                req.setId(id);
                req.setProjectId(projectId);
                req.setProjectName(projectName);
                req.setListId(listId);
                req.setListCheckTaskId(listCheckTaskId);
                req.setListSingleProject(listSingleProject);
                req.setListCheckManId(listCheckManId);
                req.setListCheckMan(listCheckMan);
                req.setListCheckType(listCheckType);
                req.setListCheckContent(listCheckContent);
                req.setListCheckStatus(listCheckStatus);
                req.setVersionId("3HJD0035");
                req.setCheckStatus("0");
                req.setProjectNum(projectNum);
                req.setUpdateDate(mTv01.getText().toString());
                req.setCreateDate(mTv01.getText().toString());
                req.setCheckMen(mTv02.getText().toString());
                req.setCreateMan(RxSPUtils.getString(TaskCheckEditActivity.this,Const.PHONENUM));
                if(mRb01.isChecked()){
                    req.setCheckBasis("计划");
                }else if(mRb02.isChecked()){
                    req.setCheckBasis("专项");
                }else{
                    req.setCheckBasis("其他");
                }
                Observable<BaseBeanRsp<SupervisionPlanFirstRsp>> observable = RetrofitFactory.getInstance().editCheckTask(req);
                observable.compose(RxSchedulers.<BaseBeanRsp<SupervisionPlanFirstRsp>>compose(
                )).subscribe(new BaseObserver<SupervisionPlanFirstRsp>() {
                    @Override
                    protected void onHandleSuccess(BaseBeanRsp<SupervisionPlanFirstRsp> beanRsp) {
                        // 登陆成功 保存用户信息等操作
                        RxToast.showToast(beanRsp.getResult());
                        TaskCheckEditActivity.this.finish();
                    }
                    @Override
                    protected void onHandleEmpty(BaseBeanRsp<SupervisionPlanFirstRsp> beanRsp) {
                        RxToast.showToast(beanRsp.getResult());
                    }
                });
            }

            tv_01.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog01 = new MaterialDialog.Builder(TaskCheckEditActivity.this)
                            .title("检查联系人").titleColor(TaskCheckEditActivity.this.getResources().getColor(R.color.main_color))
                            .customView(R.layout.dialog_task_check_add01, wrapInScrollView)
                            .positiveText("取消")
                            .show();
                    View view1 = dialog01.getCustomView();
                    RecyclerView mRvContent2 = view1.findViewById(R.id.rv_content);
                    mRvContent2.setLayoutManager(new LinearLayoutManager(TaskCheckEditActivity.this));
                    BaseQuickAdapter mBaseQuickAdapter2 = new BaseQuickAdapter<BaseBeanRsp.JokerVOBean.MonitorListBean, BaseViewHolder>(R.layout.item_task_check_add_item_01, personList) {
                        @Override
                        protected void convert(final BaseViewHolder baseViewHolder, final BaseBeanRsp.JokerVOBean.MonitorListBean rowsBean) {
                            baseViewHolder.setText(R.id.tv_01, rowsBean.getMonitorName());
                            baseViewHolder.setText(R.id.tv_02, rowsBean.getMonitorType());
                            baseViewHolder.getView(R.id.ll_01).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    tv_01.setText(rowsBean.getMonitorName());
                                    list_check_man_id.setText(rowsBean.getPersonId());
                                    dialog01.hide();
                                }
                            });
                        }
                    };
                    mRvContent2.setAdapter(mBaseQuickAdapter2);
                    mBaseQuickAdapter2.notifyDataSetChanged();
                }
            });
            tv_02.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     dialog02 = new MaterialDialog.Builder(TaskCheckEditActivity.this)
                            .title("选择工程单体").titleColor(TaskCheckEditActivity.this.getResources().getColor(R.color.main_color))
                            .customView(R.layout.dialog_task_check_add01, wrapInScrollView)
                            .positiveText("取消")
                            .show();
                    View view1 = dialog02.getCustomView();
                    RecyclerView mRvContent2 = view1.findViewById(R.id.rv_content);
                    mRvContent2.setLayoutManager(new LinearLayoutManager(TaskCheckEditActivity.this));
                    BaseQuickAdapter mBaseQuickAdapter2 = new BaseQuickAdapter<BaseBeanRsp.JokerVOBean.MonitorListBean, BaseViewHolder>(R.layout.item_task_check_add_item_02,jokerVOList ) {
                        @Override
                        protected void convert(BaseViewHolder baseViewHolder, final BaseBeanRsp.JokerVOBean.MonitorListBean rowsBean) {
                            baseViewHolder.setText(R.id.tv_01,rowsBean.getBranchName());
                            baseViewHolder.getView(R.id.ll_01).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    tv_02.setText(rowsBean.getBranchName());
                                    list_siginle_id.setText(rowsBean.getPk());
                                    dialog02.hide();
                                }
                            });
                        }
                    };
                    mRvContent2.setAdapter(mBaseQuickAdapter2);
                }
            });
            tv_03.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     dialog03 = new MaterialDialog.Builder(TaskCheckEditActivity.this)
                            .title("选择检查内容").titleColor(TaskCheckEditActivity.this.getResources().getColor(R.color.main_color))
                            .customView(R.layout.dialog_task_check_add03, wrapInScrollView)
                            .positiveText("取消")
                            .show();
                    View view1 = dialog03.getCustomView();
                    RecyclerView mRvContent2 = view1.findViewById(R.id.rv_content);
                    final EditText editText =view1.findViewById(R.id.et_search);
                    view1.findViewById(R.id.tv_search).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(TextUtils.isEmpty(editText.getText().toString())){
                                RxToast.showToast("请输入内容");
                                return;
                            }
                            getData03(editText.getText().toString());
                        }
                    });
                    mRvContent2.setLayoutManager(new LinearLayoutManager(TaskCheckEditActivity.this));
                     mBaseQuickAdapter2 = new BaseQuickAdapter<BaseBeanRsp.JokerVOBean.MonitorListBean, BaseViewHolder>(R.layout.item_task_check_add_item_03, jokerVOList2) {
                        @Override
                        protected void convert(BaseViewHolder baseViewHolder, final BaseBeanRsp.JokerVOBean.MonitorListBean rowsBean) {
                            baseViewHolder.setText(R.id.tv_01, rowsBean.getLawContent());
                            baseViewHolder.setText(R.id.tv_02, rowsBean.getLawType());
                            baseViewHolder.getView(R.id.ll_01).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    tv_03.setText(rowsBean.getLawContent());
                                    list_check_type.setText(rowsBean.getLawType());
                                    dialog03.hide();
                                }
                            });
                        }
                    };
                    mRvContent2.setAdapter(mBaseQuickAdapter2);
                }
            });
        }
    }
    private List<BaseBeanRsp.JokerVOBean.MonitorListBean> personList = new ArrayList<BaseBeanRsp.JokerVOBean.MonitorListBean>();
    private List<BaseBeanRsp.JokerVOBean.MonitorListBean> jokerVOList = new ArrayList<BaseBeanRsp.JokerVOBean.MonitorListBean>();
    private List<BaseBeanRsp.JokerVOBean.MonitorListBean> jokerVOList2 = new ArrayList<BaseBeanRsp.JokerVOBean.MonitorListBean>();

    private void getData01() {
        String id = RxSPUtils.getString(TaskCheckEditActivity.this,Const.PROJECTID);
        CommonProjectIdReq req = new CommonProjectIdReq();
         req.setProjectId(id);
        Observable<BaseBeanRsp<CommonProjectIdReq>> observable = RetrofitFactory.getInstance().getMonitor(req);
        observable.compose(RxSchedulers.<BaseBeanRsp<CommonProjectIdReq>>compose(
        )).subscribe(new BaseObserver<CommonProjectIdReq>() {
            @Override
            protected void onHandleSuccess(BaseBeanRsp<CommonProjectIdReq> t) {
                personList.addAll(t.getJokerVO().getPersonList());

            }
            @Override
            protected void onHandleEmpty(BaseBeanRsp<CommonProjectIdReq> t) {
                RxToast.showToast(t.getResult());
            }
        });
    }
    private void getData02() {
        String id = RxSPUtils.getString(TaskCheckEditActivity.this,Const.PROJECTID);
        CommonProjectIdReq req = new CommonProjectIdReq();
        req.setProjectId(id);
        Observable<BaseBeanRsp<CommonProjectIdReq>> observable = RetrofitFactory.getInstance().getSingleProject(req);
        observable.compose(RxSchedulers.<BaseBeanRsp<CommonProjectIdReq>>compose(
        )).subscribe(new BaseObserver<CommonProjectIdReq>() {
            @Override
            protected void onHandleSuccess(BaseBeanRsp<CommonProjectIdReq> t) {
                jokerVOList.addAll(t.getJokerVO().getJokerVOList());
            }
            @Override
            protected void onHandleEmpty(BaseBeanRsp<CommonProjectIdReq> t) {
                RxToast.showToast(t.getResult());
            }
        });
    }
    private void getData03(String search) {
        String id = RxSPUtils.getString(TaskCheckEditActivity.this,Const.PROJECTID);
        CommonProjectIdReq req = new CommonProjectIdReq();
        req.setProjectId(id);
        req.setLawContent(search);
        Observable<BaseBeanRsp<CommonProjectIdReq>> observable = RetrofitFactory.getInstance().getLawData(req);
        observable.compose(RxSchedulers.<BaseBeanRsp<CommonProjectIdReq>>compose(
        )).subscribe(new BaseObserver<CommonProjectIdReq>() {
            @Override
            protected void onHandleSuccess(BaseBeanRsp<CommonProjectIdReq> t) {
                jokerVOList2.clear();
                jokerVOList2.addAll(t.getJokerVO().getJokerVOList());
                if(mBaseQuickAdapter2!=null)
                mBaseQuickAdapter2.notifyDataSetChanged();
            }
            @Override
            protected void onHandleEmpty(BaseBeanRsp<CommonProjectIdReq> t) {
                RxToast.showToast(t.getResult());
            }
        });
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
            mTv01.setText(days);
        }
    };
}
