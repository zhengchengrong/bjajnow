/*
 * Copyright (c) zcr 2018.
 */

package com.threehmis.bjaj.api.bean.request;

/**
 * Created by llz on 2018/3/9.
 */

public class CommonProjectIdReq {

    private String projectId;
    private String lawContent;

    private String lawType;
    private String lawTitle;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    private String pk;

    public String getLawType() {
        return lawType;
    }

    public void setLawType(String lawType) {
        this.lawType = lawType;
    }

    public String getLawTitle() {
        return lawTitle;
    }

    public void setLawTitle(String lawTitle) {
        this.lawTitle = lawTitle;
    }

    public String getLawContent() {
        return lawContent;
    }

    public void setLawContent(String lawContent) {
        this.lawContent = lawContent;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
