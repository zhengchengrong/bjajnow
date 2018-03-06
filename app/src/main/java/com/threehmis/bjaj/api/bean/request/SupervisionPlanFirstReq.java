package com.threehmis.bjaj.api.bean.request;

/**
 * Created by llz on 2018/3/6.
 */

public class SupervisionPlanFirstReq {

    private String projectId;

    private String monitorName;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }
}
