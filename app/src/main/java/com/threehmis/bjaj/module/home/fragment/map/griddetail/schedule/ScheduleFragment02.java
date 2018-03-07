package com.threehmis.bjaj.module.home.fragment.map.griddetail.schedule;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.BaseObserver;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.RxSchedulers;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.ProjectInfoProgressReq;
import com.threehmis.bjaj.api.bean.request.ProjectInfoReq;
import com.threehmis.bjaj.api.bean.respon.ProjectProgressRsp;
import com.threehmis.bjaj.api.bean.respon.ProjectStatusRsp;
import com.threehmis.bjaj.module.base.BaseFragment;
import com.threehmis.bjaj.module.home.fragment.adminmain.childmainfragment.AllCityFragment;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.projectinfo.ProjectInfoActivity;
import com.threehmis.bjaj.module.home.fragment.map.griddetail.projectinfo.ProjectInfoImageVPActivity;
import com.threehmis.bjaj.utils.GlideTools;
import com.threehmis.bjaj.widget.EmptyLayout;
import com.vondear.rxtools.RxSPUtils;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;

/**
 * Created by llz on 2018/2/27.
 */

public class ScheduleFragment02 extends BaseFragment {
    @BindView(R.id.spinner01)
    Spinner mSpinner01;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.tv_search)
    ImageView mTvSearch;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    private ArrayList<ProjectProgressRsp> mProjectStatusRsps;
    BaseQuickAdapter mBaseQuickAdapter;
    private static final String[] spinnerData = {"单体名称", "形象进度"};
    private ArrayAdapter<String> adapterSpinner;

    private String id = "";
    private String type = "单体名称";

    private String imageUrl;

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        id = RxSPUtils.getString(mActivity, Const.PROJECTID);
        mProjectStatusRsps = new ArrayList<ProjectProgressRsp>();
        mRvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        mBaseQuickAdapter = new BaseQuickAdapter<ProjectProgressRsp, BaseViewHolder>(R.layout.item_schedule_02, mProjectStatusRsps) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, ProjectProgressRsp rowsBean) {
                baseViewHolder.setText(R.id.tv_01, baseViewHolder.getAdapterPosition() + 1 + "");
                baseViewHolder.setText(R.id.tv_02, rowsBean.getBranchName());
                baseViewHolder.setText(R.id.tv_03, rowsBean.getProjectXXJD());
                baseViewHolder.setText(R.id.tv_04, rowsBean.getCreatedate());
                imageUrl =rowsBean.getXxjdPhoto() ;
                ImageView imageView = (ImageView) baseViewHolder.getView(R.id.tv_05);
                GlideTools.GlideNoId(imageUrl,imageView);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mActivity,ProjectInfoImageVPActivity.class);
                        intent.putExtra(Const.IMAGEURL,imageUrl);
                        startActivity(intent);
                    }
                });
            }
        };
        mRvContent.setAdapter(mBaseQuickAdapter);

        //将可选内容与ArrayAdapter连接起来
        adapterSpinner = new ArrayAdapter<String>(mActivity, android.R.layout.simple_spinner_item, spinnerData);
        //设置下拉列表的风格
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        mSpinner01.setAdapter(adapterSpinner);
        //添加事件Spinner事件监听
        mSpinner01.setOnItemSelectedListener(new SpinnerSelectedListener());
        //设置默认值
        mSpinner01.setVisibility(View.VISIBLE);
        //搜索
        mTvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getDatas();
            }
        });
        getDatas();
    }

    private void getDatas() {
        ProjectInfoProgressReq req = new ProjectInfoProgressReq();
       // req.setProjectId(id);
        req.setProjectId("0132f691-b8c5-411f-8d28-758c989470b2");
        if(type.equals("单体名称")){
            req.setBranchName(TextUtils.isEmpty(mEtSearch.getText().toString())?"":mEtSearch.getText().toString());
        }else{
            req.setProjectXxjd(TextUtils.isEmpty(mEtSearch.getText().toString())?"":mEtSearch.getText().toString());
        }
        Observable<BaseBeanRsp<ProjectProgressRsp>> observable = RetrofitFactory.getInstance().getProjectProgress(req);
        observable.compose(RxSchedulers.<BaseBeanRsp<ProjectProgressRsp>>compose(
        )).subscribe(new BaseObserver<ProjectProgressRsp>() {
            @Override
            protected void onHandleSuccess(BaseBeanRsp<ProjectProgressRsp> projectProgressRspBaseBeanRsp) {
                mProjectStatusRsps.clear();
                mProjectStatusRsps.addAll(projectProgressRspBaseBeanRsp.getProjectList());
                mBaseQuickAdapter.notifyDataSetChanged();
            }

            @Override
            protected void onHandleEmpty(BaseBeanRsp<ProjectProgressRsp> t) {
                RxToast.showToast(t.getResult());
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
    //使用数组形式操作
    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            type = spinnerData[arg2];
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_schedule02;
    }


}
