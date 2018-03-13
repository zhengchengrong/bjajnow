package com.threehmis.bjaj.module.home.fragment.personcenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.BaseObserver;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.RxSchedulers;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.CommonProjectIdReq;
import com.threehmis.bjaj.api.bean.request.GetLoginListReq;
import com.threehmis.bjaj.api.bean.respon.GetLoginListRsp;
import com.threehmis.bjaj.api.bean.respon.ProjectStatusRsp;
import com.threehmis.bjaj.module.base.BaseFragment;
import com.threehmis.bjaj.widget.EmptyLayout;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;

/**
 * Created by llz on 2018/3/6.
 */

public class PersonCenterChildFragment02 extends BaseFragment {


    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;

    BaseQuickAdapter mBaseQuickAdapter;

    ArrayList mProjectStatusRsps;
    @Override
    protected void initInjector() {
    }

    @Override
    protected void initViews() {
        mRvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        mProjectStatusRsps = new ArrayList<String>();
        mBaseQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_person_center_child_01, mProjectStatusRsps) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, final String rowsBean) {
                baseViewHolder.setText(R.id.tv_01,rowsBean);
                baseViewHolder.getView(R.id.tv_01).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            Intent intent = new Intent(mActivity,PersonCenterChildDetailActivity.class);
                            intent.putExtra(Const.TYPE,rowsBean);
                            startActivity(intent);
                    }
                });
            }
        };
        mRvContent.setAdapter(mBaseQuickAdapter);

        getData();
    }

    private void getData() {
        CommonProjectIdReq req = new CommonProjectIdReq();
        Observable<BaseBeanRsp<Object>> observable = RetrofitFactory.getInstance().getLawDataType(req);
        observable.compose(RxSchedulers.<BaseBeanRsp<Object>>compose(
        )).subscribe(new BaseObserver<Object>() {
            @Override
            protected void onHandleSuccess(BaseBeanRsp<Object> t) {
                mProjectStatusRsps.addAll(t.getProjectList());
                mBaseQuickAdapter.notifyDataSetChanged();
            }
            @Override
            protected void onHandleEmpty(BaseBeanRsp<Object> t) {
                RxToast.showToast(t.getProjectList().get(0).toString());
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_person_center_child_02;
    }


}
