package com.threehmis.bjaj.api.bean.request;

/**
 * Created by llz on 2018/3/6.
 */

public class TaskCheckAddBeanReq {

    private String createMan="";
    private String createManId="";
    private String listSingleProject="";
    private String listSingleProjectId="";
    private String checkType="";
    private String checkContetnt="";
    private String checkStatus="";

    public String getCreateManId() {
        return createManId;
    }

    public void setCreateManId(String createManId) {
        this.createManId = createManId;
    }

    public String getCheckContetnt() {
        return checkContetnt;
    }

    public void setCheckContetnt(String checkContetnt) {
        this.checkContetnt = checkContetnt;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public String getListSingleProject() {
        return listSingleProject;
    }

    public void setListSingleProject(String listSingleProject) {
        this.listSingleProject = listSingleProject;
    }

    public String getListSingleProjectId() {
        return listSingleProjectId;
    }

    public void setListSingleProjectId(String listSingleProjectId) {
        this.listSingleProjectId = listSingleProjectId;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }
}
