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
import com.threehmis.bjaj.api.bean.request.AllCityBeanReq;
import com.threehmis.bjaj.api.bean.request.ChangeAddressRequestBean;
import com.threehmis.bjaj.api.bean.request.CommonProjectIdReq;
import com.threehmis.bjaj.api.bean.request.GetLoginListReq;
import com.threehmis.bjaj.api.bean.request.GetMenusListReq;
import com.threehmis.bjaj.api.bean.request.GetSearchReq;
import com.threehmis.bjaj.api.bean.request.JsjBeanReq;
import com.threehmis.bjaj.api.bean.request.ProjectCheckTaskReq;
import com.threehmis.bjaj.api.bean.request.ProjectInfoProgressReq;
import com.threehmis.bjaj.api.bean.request.ProjectInfoReq;
import com.threehmis.bjaj.api.bean.request.QzjxBeanReq;
import com.threehmis.bjaj.api.bean.request.SafeCommentBeanReq;
import com.threehmis.bjaj.api.bean.request.SupervisionJDReq;
import com.threehmis.bjaj.api.bean.request.SupervisionPlanFirstReq;
import com.threehmis.bjaj.api.bean.request.SupervisionPlanReq;
import com.threehmis.bjaj.api.bean.request.TaskCheckAddReq;
import com.threehmis.bjaj.api.bean.respon.ChangeAddressResponBean;
import com.threehmis.bjaj.api.bean.respon.GetExamReportRsp;
import com.threehmis.bjaj.api.bean.respon.GetLoginListRsp;
import com.threehmis.bjaj.api.bean.respon.GetMainAddressRsp;
import com.threehmis.bjaj.api.bean.respon.GetMenusListRsp;
import com.threehmis.bjaj.api.bean.respon.GetMyProjectInfoRsp;
import com.threehmis.bjaj.api.bean.respon.GetSearchRsp;
import com.threehmis.bjaj.api.bean.respon.JsjBeanRsp;
import com.threehmis.bjaj.api.bean.respon.ProjectProgressRsp;
import com.threehmis.bjaj.api.bean.respon.ProjectStatusRsp;
import com.threehmis.bjaj.api.bean.respon.ProjectTaskCheckRsp;
import com.threehmis.bjaj.api.bean.respon.QzjxBeanRsp;
import com.threehmis.bjaj.api.bean.respon.SafeCommentRsp;
import com.threehmis.bjaj.api.bean.respon.SupervisionJDRsp;
import com.threehmis.bjaj.api.bean.respon.SupervisionPlanFirstRsp;

import java.util.ArrayList;

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

    // 获取用户信息
    @POST("user/getUserInfo")
    Observable<BaseBeanRsp<GetLoginListRsp>> getUserInfo(@Body GetLoginListReq  req);
    // 修改密码
    @POST("user/modifyPassword")
    Observable<BaseBeanRsp<GetLoginListRsp>> modifyPassword(@Body GetLoginListReq  req);
    // 修改电话
    @POST("person/modifyPhone")
    Observable<BaseBeanRsp<GetLoginListRsp>> modifyPhone(@Body GetLoginListReq  req);
    //根据关键字搜索地图上的位置
    @POST("project/getProjectListByKeyword")
    Observable<BaseBeanRsp<GetSearchRsp>> byKeyForMapLocal(@Body GetSearchReq req);

    //根据关键字搜索地图上的位置
    @POST("menu/getMenuList")
    Observable<BaseBeanRsp<GetMenusListRsp>> getMenuList(@Body GetMenusListReq req);

    //获取工程信息
    @POST("project/getProjectProfile")
    Observable<GetMyProjectInfoRsp> getProjectProfile(@Body ProjectInfoReq req);

    //获取形象进度
    @POST("project/getProjectStatus")
    Observable<BaseBeanRsp<ProjectStatusRsp>> getProjectStatus(@Body ProjectInfoReq req);

    //获取单体形象进度
    @POST("monitorProgress/getProjectProgress")
    Observable<BaseBeanRsp<ProjectProgressRsp>> getProjectProgress(@Body ProjectInfoProgressReq req);

    //获取 任务指派.现场检查.监督记录.整改通知
    @POST("checkTask/getCheckTask")
    Observable<BaseBeanRsp<ProjectTaskCheckRsp>> getCheckTask(@Body ProjectCheckTaskReq req);

    //获取 安评
    @POST("AP/getAP")
    Observable<BaseBeanRsp<SafeCommentRsp>> getAP(@Body SafeCommentBeanReq req);
    //获取 起重机械
    @POST("QZJX/getQZJX")
    Observable<BaseBeanRsp<QzjxBeanRsp>> getQZJX(@Body QzjxBeanReq req);
    //获取 脚手架
    @POST("JSJ/getJSJ")
    Observable<BaseBeanRsp<JsjBeanRsp>> getJsj(@Body JsjBeanReq req);

    //获取监督计划
    @POST("monitorInfo/getMonitorInfo")
    Observable<BaseBeanRsp<SupervisionPlanFirstRsp>> getMonitorInfo(@Body SupervisionPlanFirstReq req);
    //获取监督交底
    @POST("monitorInfo/getMonitorInfo")
    Observable<BaseBeanRsp<SupervisionJDRsp>> getMonitorJDInfo(@Body SupervisionPlanFirstReq req);
    //添加任务指派
    @POST("checkTask/saveCheckTask")
    Observable<BaseBeanRsp<SupervisionPlanFirstRsp>> saveCheckTask(@Body TaskCheckAddReq req);
    //添加任务指派
    @POST("checkTask/modifyCheckTask")
    Observable<BaseBeanRsp<SupervisionPlanFirstRsp>> editCheckTask(@Body TaskCheckAddReq req);
    //添加监督计划
    @POST("monitorInfo/saveMonitorInfo")
    Observable<BaseBeanRsp<SupervisionPlanFirstRsp>> saveMonitorInfo(@Body SupervisionPlanReq req);
    //添加监督计划
    @POST("monitorInfo/saveMonitorInfo")
    Observable<BaseBeanRsp<SupervisionPlanFirstRsp>> saveMonitorInfo(@Body SupervisionJDReq req);
    //添加监督员
    @POST("project/getMonitor")
    Observable<BaseBeanRsp<CommonProjectIdReq>> getMonitor(@Body CommonProjectIdReq req);

    //添加监督员
    @POST("singleProject/getSingleProject")
    Observable<BaseBeanRsp<CommonProjectIdReq>> getSingleProject(@Body CommonProjectIdReq req);

    //添加监督员
    @POST("lawData/getLawData")
    Observable<BaseBeanRsp<CommonProjectIdReq>> getLawData(@Body CommonProjectIdReq req);

    //获取安全行为
    @POST("lawData/getLawDataType")
    Observable<BaseBeanRsp<Object>> getLawDataType(@Body CommonProjectIdReq req);
    //获取安全行为列表
    @POST("lawData/getLawDataList")
    Observable<BaseBeanRsp<Object>> getLawDataList(@Body CommonProjectIdReq req);
    //获取安全行为列表详情
    @POST("lawData/getLawDataInfo")
    Observable<BaseBeanRsp<Object>> getLawDataInfo(@Body CommonProjectIdReq req);

    //获取首页信息
    @POST("project/getProjectStatistic")
    Observable<BaseBeanRsp<Object>> getProjectStatistic(@Body AllCityBeanReq req);
    //获取变更记录
/*    @POST("monitorInfo/getMonitorInfo")
    Observable<BaseBeanRsp<SupervisionPlanFirstRsp>> getMonitorInfo2(@Body SupervisionPlanFirstReq req);*/
/*    @POST("monitorunit/getMonitorunit")
    Observable<BaseBeanRsp<ChangeAddressResponBean>> getMonitorunitStr(@Body ChangeAddressRequestBean params);*/

}
