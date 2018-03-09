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
