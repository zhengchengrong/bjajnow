package com.threehmis.bjaj.api.bean.request;

import java.util.List;

/**
 * Created by llz on 2018/3/7.
 */

public class TaskCheckAddReq {


    /**
     * id :
     * projectId : 1bb69ede-b55f-46e8-b35b-1540ae7bd152
     * projectNum : 10012454544000
     * projectName : 人民日报社老旧小区抗震节能综合改造工程（C区）
     * checkNum : JS001
     * checkBasis : 计划
     * checkDate : 2018-01-30
     * checkMen : 任伟，武卫兵
     * checkStatus : 0
     * createMan : 武卫兵
     * createDate : 2018-01-30
     * updateDate : 2018-01-30
     * versionId : 3HJD0035
     * listId : ["","",""]
     * listCheckTaskId : ["","",""]
     * listSingleProject : ["df3ecf3d-0380-4e62-809a-f6d2d6309841","df3ecf3d-0380-4e62-809a-f6d2d6309841","df3ecf3d-0380-4e62-809a-f6d2d6309841"]
     * listCheckManId : ["BC65D741-793D-4343-8838-A1F53875FE09","0031EE90-5A22-4801-83D9-1FF4E593C8E7","0031EE90-5A22-4801-83D9-1FF4E593C8E7"]
     * listCheckMan : ["武卫兵","任伟","任伟"]
     * listCheckType : ["高处作业","塔式起重机","塔式起重机"]
     * listCheckContent : ["在建工程外侧水平安全网设置应符合规范要求，多层和高层建筑每隔四层且不大于10m，应设一道3m宽的水平安全网","物料提升机安装完毕后，施工总承包应组织出租、安装、监理等有关单位进行验收，验收合格方可使用。","物料提升机的安装单位应有安装、拆卸方案，并按方案及操作规程组织安装、拆卸工作。"]
     * listCheckStatus : ["0","0","0"]
     */

    private String id="";
    private String projectId="";
    private String projectNum="";
    private String projectName="";
    private String checkNum="";
    private String checkBasis="";
    private String checkDate="";
    private String checkMen="";
    private String checkStatus="";
    private String createMan="";
    private String createDate="";
    private String updateDate="";
    private String versionId="";
    private String otherProblem="";
    private List<String> listId;
    private List<String> listCheckTaskId;
    private List<String> listSingleProject;
    private List<String> listCheckManId;
    private List<String> listCheckMan;
    private List<String> listCheckType;
    private List<String> listCheckContent;
    private List<String> listCheckStatus;
    private List<String> listCheckResult;

    public String getOtherProblem() {
        return otherProblem;
    }

    public void setOtherProblem(String otherProblem) {
        this.otherProblem = otherProblem;
    }

    public List<String> getListCheckResult() {
        return listCheckResult;
    }

    public void setListCheckResult(List<String> listCheckResult) {
        this.listCheckResult = listCheckResult;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum;
    }

    public String getCheckBasis() {
        return checkBasis;
    }

    public void setCheckBasis(String checkBasis) {
        this.checkBasis = checkBasis;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckMen() {
        return checkMen;
    }

    public void setCheckMen(String checkMen) {
        this.checkMen = checkMen;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public List<String> getListId() {
        return listId;
    }

    public void setListId(List<String> listId) {
        this.listId = listId;
    }

    public List<String> getListCheckTaskId() {
        return listCheckTaskId;
    }

    public void setListCheckTaskId(List<String> listCheckTaskId) {
        this.listCheckTaskId = listCheckTaskId;
    }

    public List<String> getListSingleProject() {
        return listSingleProject;
    }

    public void setListSingleProject(List<String> listSingleProject) {
        this.listSingleProject = listSingleProject;
    }

    public List<String> getListCheckManId() {
        return listCheckManId;
    }

    public void setListCheckManId(List<String> listCheckManId) {
        this.listCheckManId = listCheckManId;
    }

    public List<String> getListCheckMan() {
        return listCheckMan;
    }

    public void setListCheckMan(List<String> listCheckMan) {
        this.listCheckMan = listCheckMan;
    }

    public List<String> getListCheckType() {
        return listCheckType;
    }

    public void setListCheckType(List<String> listCheckType) {
        this.listCheckType = listCheckType;
    }

    public List<String> getListCheckContent() {
        return listCheckContent;
    }

    public void setListCheckContent(List<String> listCheckContent) {
        this.listCheckContent = listCheckContent;
    }

    public List<String> getListCheckStatus() {
        return listCheckStatus;
    }

    public void setListCheckStatus(List<String> listCheckStatus) {
        this.listCheckStatus = listCheckStatus;
    }
}
