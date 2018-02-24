package com.threehmis.bjaj.api.bean.respon;

/**
 * Created by llz on 2018/2/24.
 */

public class ProjectInfoOneRep {


    /**
     * projectId : 795ddfaa-d9ee-4e10-b544-d620bcaa9181
     * projectCode : 201801102006
     * projectName : 海淀北部燃气热电冷联供项目外部10KV电源供电管道
     * projectNum : 京建安[2018]0053号
     * sgxkzh : [2018]施[海]市政字0015号
     * bjDate : 2018-02-02 08:50:31.0
     * region : 海淀区
     * address : 北清路到翠湖南
     * projectType : 市政
     * area : 0.0
     * projectCost : 2043.101
     * high : 0.0
     * contractPeriod : 120
     * tfzyDate : null
     * singleProject : null,电力管线（地埋）
     */

    private String projectId;
    private String projectCode;
    private String projectName;
    private String projectNum;
    private String sgxkzh;
    private String bjDate;
    private String region;
    private String address;
    private String projectType;
    private double area;
    private double projectCost;
    private double high;
    private String contractPeriod;
    private String tfzyDate;
    private String singleProject;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getSgxkzh() {
        return sgxkzh;
    }

    public void setSgxkzh(String sgxkzh) {
        this.sgxkzh = sgxkzh;
    }

    public String getBjDate() {
        return bjDate;
    }

    public void setBjDate(String bjDate) {
        this.bjDate = bjDate;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getProjectCost() {
        return projectCost;
    }

    public void setProjectCost(double projectCost) {
        this.projectCost = projectCost;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public String getContractPeriod() {
        return contractPeriod;
    }

    public void setContractPeriod(String contractPeriod) {
        this.contractPeriod = contractPeriod;
    }

    public String getTfzyDate() {
        return tfzyDate;
    }

    public void setTfzyDate(String tfzyDate) {
        this.tfzyDate = tfzyDate;
    }

    public String getSingleProject() {
        return singleProject;
    }

    public void setSingleProject(String singleProject) {
        this.singleProject = singleProject;
    }
}
