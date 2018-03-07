package com.threehmis.bjaj.module.home.fragment.map.griddetail.taskcheck;

import android.app.DatePickerDialog;
import android.graphics.Canvas;
import android.os.Bundle;
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
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.BaseObserver;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.RxSchedulers;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.TaskCheckAddBeanReq;
import com.threehmis.bjaj.api.bean.request.TaskCheckAddReq;
import com.threehmis.bjaj.api.bean.respon.QzjxBeanRsp;
import com.threehmis.bjaj.api.bean.respon.SupervisionPlanFirstRsp;
import com.threehmis.bjaj.module.base.BaseActivity;
import com.threehmis.bjaj.widget.EmptyLayout;
import com.vondear.rxtools.RxLogUtils;
import com.vondear.rxtools.RxSPUtils;
import com.vondear.rxtools.view.RxToast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * Created by llz on 2018/2/27.
 */

public class TaskCheckAddActivity extends BaseActivity {


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

    private List<String> listId;
    private List<String> listCheckTaskId;
    private List<String> listSingleProject;
    private List<String> listCheckManId;
    private List<String> listCheckMan;
    private List<String> listCheckType;
    private List<String> listCheckContent;
    private List<String> listCheckStatus;

    private StringBuffer mStringBuffer = new StringBuffer();
    Set<String> set = new HashSet<>();


    private boolean isCommit;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_task_check_add;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initTitle();
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

        mProjectStatusRsps = new ArrayList<TaskCheckAddBeanReq>();
        mItemDragAdapter = new ItemDragAdapter(mProjectStatusRsps);
        mTvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskCheckAddBeanReq taskCheckAddBeanReq = new TaskCheckAddBeanReq();
                taskCheckAddBeanReq.setName("");
                taskCheckAddBeanReq.setDtgc("");
                taskCheckAddBeanReq.setCheckContent("");
                mProjectStatusRsps.add(taskCheckAddBeanReq);
                mItemDragAdapter.notifyDataSetChanged();
            }
        });

        TaskCheckAddBeanReq taskCheckAddBeanReq = new TaskCheckAddBeanReq();
        taskCheckAddBeanReq.setName("");
        taskCheckAddBeanReq.setDtgc("");
        taskCheckAddBeanReq.setCheckContent("");
        mProjectStatusRsps.add(taskCheckAddBeanReq);
        mRvContent.setLayoutManager(new LinearLayoutManager(this));
        mRvContent.setAdapter(mItemDragAdapter);
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mItemDragAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(mRvContent);
        mItemDragAdapter.enableSwipeItem();
        mTvCommit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                isCommit = true;
                listId.clear();
                listCheckTaskId.clear();
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
                if(TextUtils.isEmpty(mTv02.getText().toString())){
                    RxToast.showToast("检查人不能为空!");
                    return;
                }
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
                new DatePickerDialog(TaskCheckAddActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
            }
        });
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


    public class ItemDragAdapter extends BaseItemDraggableAdapter<TaskCheckAddBeanReq, BaseViewHolder> {
        public ItemDragAdapter(ArrayList<TaskCheckAddBeanReq> data) {
            super(R.layout.item_task_check_add_01, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, TaskCheckAddBeanReq item) {
            final TextView tv_01 = baseViewHolder.getView(R.id.et_01);
            final TextView tv_02 = baseViewHolder.getView(R.id.et_02);
            final TextView tv_03 = baseViewHolder.getView(R.id.et_03);
            RxLogUtils.d(tv_01.getText().toString() + ":" + tv_02.getText().toString() + ":" + tv_03.getText().toString());
            if(!TextUtils.isEmpty(tv_01.getText().toString())) {
                set.add(tv_01.getText().toString() );
                Iterator<String> it = set.iterator();
                mStringBuffer.setLength(0);
                while (it.hasNext()) {
                    String str = it.next();
                    mStringBuffer.append(str+ ",");
                }
                mTv02.setText(mStringBuffer.substring(0, mStringBuffer.length() - 1));

            }

            listId.add("");
            listCheckTaskId.add("");
            listSingleProject.add("df3ecf3d-0380-4e62-809a-f6d2d6309841");
            listCheckManId.add("武卫兵");
            listCheckMan.add(tv_01.getText().toString());
            listCheckType.add("高处作业");
            listCheckContent.add("在建工程外侧水平安全网设置应符合规范要求，多层和高层建筑每隔四层且不大于10m，应设一道3m宽的水平安全网");
            listCheckStatus.add("0");
            if(baseViewHolder.getAdapterPosition() == mProjectStatusRsps.size()-1&&isCommit==true){
                TaskCheckAddReq req = new TaskCheckAddReq();
                req.setCheckNum(mEt01.getText().toString());
                req.setCheckDate(mTv01.getText().toString());
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
                req.setUpdateDate(mTv01.getText().toString());
                req.setCreateDate(mTv01.getText().toString());
                req.setCheckMen(mTv02.getText().toString());
                if(mRb01.isChecked()){
                    req.setCheckBasis("计划");
                }else if(mRb02.isChecked()){
                    req.setCheckBasis("专项");
                }else{
                    req.setCheckBasis("其他");
                }
                Observable<BaseBeanRsp<SupervisionPlanFirstRsp>> observable = RetrofitFactory.getInstance().saveCheckTask(req);
                observable.compose(RxSchedulers.<BaseBeanRsp<SupervisionPlanFirstRsp>>compose(
                )).subscribe(new BaseObserver<SupervisionPlanFirstRsp>() {
                    @Override
                    protected void onHandleSuccess(BaseBeanRsp<SupervisionPlanFirstRsp> beanRsp) {
                        // 登陆成功 保存用户信息等操作
                        RxToast.showToast(beanRsp.getResult());
                        TaskCheckAddActivity.this.finish();
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
                    final MaterialDialog dialog = new MaterialDialog.Builder(TaskCheckAddActivity.this)
                            .title("检查联系人").titleColor(TaskCheckAddActivity.this.getResources().getColor(R.color.main_color))
                            .customView(R.layout.dialog_task_check_add01, wrapInScrollView)
                            .positiveText("取消")
                            .show();
                    View view1 = dialog.getCustomView();
                    RecyclerView mRvContent2 = view1.findViewById(R.id.rv_content);
                    mRvContent2.setLayoutManager(new LinearLayoutManager(TaskCheckAddActivity.this));
                    ArrayList<QzjxBeanRsp> mQzjxBeanRsps = new ArrayList<QzjxBeanRsp>();
                    BaseQuickAdapter mBaseQuickAdapter2 = new BaseQuickAdapter<QzjxBeanRsp, BaseViewHolder>(R.layout.item_task_check_add_item_01, mQzjxBeanRsps) {
                        @Override
                        protected void convert(final BaseViewHolder baseViewHolder, final QzjxBeanRsp rowsBean) {
                            baseViewHolder.setText(R.id.tv_01, rowsBean.getSbdjbh());
                            baseViewHolder.setText(R.id.tv_02, rowsBean.getSblx());
                            baseViewHolder.setText(R.id.tv_03, rowsBean.getSbxh());
                            baseViewHolder.setText(R.id.tv_04, rowsBean.getSfyzx());
                         baseViewHolder.getView(R.id.ll_01).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                      tv_01.setText(rowsBean.getSbdjbh());
                                      dialog.dismiss();
                                }
                            });
                        }
                    };
                    mRvContent2.setAdapter(mBaseQuickAdapter2);
                    QzjxBeanRsp qzjxBeanRsp = new QzjxBeanRsp();
                    qzjxBeanRsp.setSbdjbh("123");
                    qzjxBeanRsp.setSblx("456");
                    qzjxBeanRsp.setSbxh("789");
                    qzjxBeanRsp.setSfyzx("1000");
                    mQzjxBeanRsps.add(qzjxBeanRsp);
                    QzjxBeanRsp qzjxBeanRsp2 = new QzjxBeanRsp();
                    qzjxBeanRsp2.setSbdjbh("abc");
                    qzjxBeanRsp2.setSblx("def");
                    qzjxBeanRsp2.setSbxh("ghk");
                    qzjxBeanRsp2.setSfyzx("mmp");
                    mQzjxBeanRsps.add(qzjxBeanRsp2);
                    mBaseQuickAdapter2.notifyDataSetChanged();
                }
            });
            tv_02.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MaterialDialog dialog = new MaterialDialog.Builder(TaskCheckAddActivity.this)
                            .title("选择工程单体").titleColor(TaskCheckAddActivity.this.getResources().getColor(R.color.main_color))
                            .customView(R.layout.dialog_task_check_add01, wrapInScrollView)
                            .positiveText("取消")
                            .show();
                    View view1 = dialog.getCustomView();
                    RecyclerView mRvContent2 = view1.findViewById(R.id.rv_content);
                    mRvContent2.setLayoutManager(new LinearLayoutManager(TaskCheckAddActivity.this));
                    ArrayList<QzjxBeanRsp> mQzjxBeanRsps = new ArrayList<QzjxBeanRsp>();
                    BaseQuickAdapter mBaseQuickAdapter2 = new BaseQuickAdapter<QzjxBeanRsp, BaseViewHolder>(R.layout.item_task_check_add_item_02, mQzjxBeanRsps) {
                        @Override
                        protected void convert(BaseViewHolder baseViewHolder, QzjxBeanRsp rowsBean) {
                            baseViewHolder.setText(R.id.tv_01, rowsBean.getSbdjbh());
                        }
                    };
                    mRvContent2.setAdapter(mBaseQuickAdapter2);
                    QzjxBeanRsp qzjxBeanRsp = new QzjxBeanRsp();
                    qzjxBeanRsp.setSbdjbh("123");
                    qzjxBeanRsp.setSblx("456");
                    qzjxBeanRsp.setSbxh("789");
                    qzjxBeanRsp.setSfyzx("1000");
                    mQzjxBeanRsps.add(qzjxBeanRsp);
                    QzjxBeanRsp qzjxBeanRsp2 = new QzjxBeanRsp();
                    qzjxBeanRsp2.setSbdjbh("123");
                    qzjxBeanRsp2.setSblx("456");
                    qzjxBeanRsp2.setSbxh("789");
                    qzjxBeanRsp2.setSfyzx("1000");
                    mQzjxBeanRsps.add(qzjxBeanRsp2);
                    mBaseQuickAdapter2.notifyDataSetChanged();
                }
            });
            tv_03.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MaterialDialog dialog = new MaterialDialog.Builder(TaskCheckAddActivity.this)
                            .title("选择检查内容").titleColor(TaskCheckAddActivity.this.getResources().getColor(R.color.main_color))
                            .customView(R.layout.dialog_task_check_add03, wrapInScrollView)
                            .positiveText("取消")
                            .show();
                    View view1 = dialog.getCustomView();
                    RecyclerView mRvContent2 = view1.findViewById(R.id.rv_content);
                    mRvContent2.setLayoutManager(new LinearLayoutManager(TaskCheckAddActivity.this));
                    ArrayList<QzjxBeanRsp> mQzjxBeanRsps = new ArrayList<QzjxBeanRsp>();
                    BaseQuickAdapter mBaseQuickAdapter2 = new BaseQuickAdapter<QzjxBeanRsp, BaseViewHolder>(R.layout.item_task_check_add_item_03, mQzjxBeanRsps) {
                        @Override
                        protected void convert(BaseViewHolder baseViewHolder, QzjxBeanRsp rowsBean) {
                            baseViewHolder.setText(R.id.tv_01, rowsBean.getSbdjbh());
                            baseViewHolder.setText(R.id.tv_02, rowsBean.getSblx());
                            baseViewHolder.setText(R.id.tv_03, rowsBean.getSbxh());
                            baseViewHolder.getView(R.id.tv_04).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    RxToast.showToast("查看");
                                }
                            });
                        }
                    };
                    mRvContent2.setAdapter(mBaseQuickAdapter2);
                    QzjxBeanRsp qzjxBeanRsp = new QzjxBeanRsp();
                    qzjxBeanRsp.setSbdjbh("123");
                    qzjxBeanRsp.setSblx("456");
                    qzjxBeanRsp.setSbxh("789");
                    qzjxBeanRsp.setSfyzx("1000");
                    mQzjxBeanRsps.add(qzjxBeanRsp);
                    QzjxBeanRsp qzjxBeanRsp2 = new QzjxBeanRsp();
                    qzjxBeanRsp2.setSbdjbh("123");
                    qzjxBeanRsp2.setSblx("456");
                    qzjxBeanRsp2.setSbxh("789");
                    qzjxBeanRsp2.setSfyzx("1000");
                    mQzjxBeanRsps.add(qzjxBeanRsp2);
                    mBaseQuickAdapter2.notifyDataSetChanged();
                }
            });
        }
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
