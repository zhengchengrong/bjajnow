package com.threehmis.bjaj.api;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.BaseEntity;
import com.threehmis.bjaj.api.bean.ItemResult;
import com.threehmis.bjaj.api.bean.request.ChangeAddressRequestBean;
import com.threehmis.bjaj.api.bean.request.GetLoginListReq;
import com.threehmis.bjaj.api.bean.request.GetMenusListReq;
import com.threehmis.bjaj.api.bean.request.GetSearchReq;
import com.threehmis.bjaj.api.bean.request.ProjectInfoReq;
import com.threehmis.bjaj.api.bean.respon.ChangeAddressResponBean;
import com.threehmis.bjaj.api.bean.respon.GetExamReportRsp;
import com.threehmis.bjaj.api.bean.respon.GetLoginListRsp;
import com.threehmis.bjaj.api.bean.respon.GetMainAddressRsp;
import com.threehmis.bjaj.api.bean.respon.GetMenusListRsp;
import com.threehmis.bjaj.api.bean.respon.GetMyProjectInfoRsp;
import com.threehmis.bjaj.api.bean.respon.GetSearchRsp;

/**
 * Created by zhengchengrong on 2017/9/1.
 */

public interface BjajService {

    @POST("monitorunit/getMonitorunit")
    Observable<BaseBeanRsp<ChangeAddressResponBean>> getMonitorunit(@Body ChangeAddressRequestBean params);


    // 获取附近工地列表
    @FormUrlEncoded
    @POST("monitorunit/getMonitorunit")
    Observable<BaseBeanRsp<ChangeAddressResponBean>> getMonitorunitStr(@Field("params") String  str);

    //根据关键字搜索工地
    @FormUrlEncoded
    @POST("monitorunit/findByUnitName")
    Observable<BaseBeanRsp<ChangeAddressResponBean>> byKeyForMonitorunit(@Field("params") String  str);

    //登陆
    @POST("login/getProjectList")
    Observable<BaseBeanRsp<GetLoginListRsp>> toLogin(@Body GetLoginListReq  req);

    //根据关键字搜索地图上的位置
    @POST("project/getProjectListByKeyword")
    Observable<BaseBeanRsp<GetSearchRsp>> byKeyForMapLocal(@Body GetSearchReq req);

    //根据关键字搜索地图上的位置
    @POST("menu/getMenuList")
    Observable<BaseBeanRsp<GetMenusListRsp>> getMenuList(@Body GetMenusListReq req);

    //获取工程信息
    @POST("project/getProjectProfile")
    Observable<GetMyProjectInfoRsp> getProjectProfile(@Body ProjectInfoReq req);


/*    @POST("monitorunit/getMonitorunit")
    Observable<BaseBeanRsp<ChangeAddressResponBean>> getMonitorunitStr(@Body ChangeAddressRequestBean params);*/

}
