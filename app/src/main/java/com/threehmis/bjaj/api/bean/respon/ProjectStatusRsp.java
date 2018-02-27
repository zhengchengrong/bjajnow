package com.threehmis.bjaj.api.bean.respon;

/**
 * Created by llz on 2018/2/27.
 */

public class ProjectStatusRsp {


    /**
     * projectId : 0132f691-b8c5-411f-8d28-758c989470b2
     * projectName : 后勤楼及实验室等8项（北京同仁堂健康药业股份有限公司大兴生产基地建设项目）
     * registerDate : 2017-09-04 20:25:27.0
     * projectStatus : SP020
     */

    private String projectId;
    private String projectName;
    private String registerDate;
    private String projectStatus;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }
}
