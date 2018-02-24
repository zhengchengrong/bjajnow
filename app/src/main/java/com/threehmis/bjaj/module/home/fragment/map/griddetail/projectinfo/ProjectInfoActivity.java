package com.threehmis.bjaj.module.home.fragment.map.griddetail.projectinfo;

import android.os.Bundle;

import com.google.gson.Gson;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.BaseObserver;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.RxSchedulers;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.GetLoginListReq;
import com.threehmis.bjaj.api.bean.request.ProjectInfoReq;
import com.threehmis.bjaj.api.bean.respon.GetLoginListRsp;
import com.threehmis.bjaj.api.bean.respon.GetMenusListRsp;
import com.threehmis.bjaj.api.bean.respon.GetMyProjectInfoRsp;
import com.threehmis.bjaj.api.bean.respon.ProjectInfoOneRep;
import com.threehmis.bjaj.api.bean.respon.ProjectInfoTwoRep;
import com.threehmis.bjaj.module.base.BaseActivity;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by llz on 2018/2/24.
 */

public class ProjectInfoActivity extends BaseActivity {

    String projectId;
    String projectName;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_project_info;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

        getDatas();
    }

    private void getDatas() {
        Bundle bundle = this.getIntent().getExtras();// 获取传递过来的封装了数据的Bundle
        projectId= bundle.getString(Const.PROJECTID);// 获取name_Key对应的Value
        projectName= bundle.getString(Const.PROJECTNAME);// 获取name_Key对应的Value
        ProjectInfoReq req = new ProjectInfoReq();
        req.setProjectId(projectId);

        Observable<GetMyProjectInfoRsp> observable = RetrofitFactory.getInstance().getProjectProfile(req);
        observable.compose(RxSchedulers.<GetMyProjectInfoRsp>compose(
        )).subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(Object o) {
                GetMyProjectInfoRsp getMyProjectInfoRsp = (GetMyProjectInfoRsp)o;
                ProjectInfoOneRep projectInfoOneRep = new Gson().fromJson(new Gson().toJson( getMyProjectInfoRsp.getProjectList().get(0)),ProjectInfoOneRep.class);
                ArrayList<ProjectInfoTwoRep> twoReps = (ArrayList<ProjectInfoTwoRep>) getMyProjectInfoRsp.getProjectList().get(1);
                for(int i=0;i<twoReps.size();i++){
                    ProjectInfoTwoRep rep =  new Gson().fromJson(new Gson().toJson(  twoReps.get(i)),ProjectInfoTwoRep.class);
                }
                ArrayList<String> three = (ArrayList<String>) getMyProjectInfoRsp.getProjectList().get(2);
                 for(int i=0;i<three.size();i++){
                    String str =  three.get(i);
                 }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
