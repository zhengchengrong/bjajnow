package com.threehmis.bjaj.api.bean.respon;

/**
 * Created by llz on 2018/3/1.
 */

public class AccessoryBeanRes {


    /**
     * projectId : 795ddfaa-d9ee-4e10-b544-d620bcaa9181
     * fileName : 施工总承包单位项目负责人质量终身责任承诺书
     * fileUrl : http://bjgccp.bjjs.gov.cn/Resource/Promise/2017_11/2017110917001339.png
     */

    private String projectId;
    private String fileName;
    private String fileUrl;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
