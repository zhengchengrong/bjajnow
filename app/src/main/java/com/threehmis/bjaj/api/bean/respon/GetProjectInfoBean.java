package com.threehmis.bjaj.api.bean.respon;

import java.util.List;

/**
 * Created by llz on 2018/1/29.
 */

public class GetProjectInfoBean {


    /**
     * projectList : [{"projectId":"1835d27d-625e-4a1b-bffd-47608e89aa08","projectCode":"201709282009","projectName":"1#、2#住宅楼（公租房）[朝阳区高井2号地保障性住房用地（配建商品房及公建）项目]局部内装修工程","projectNum":"京建安[2018]0054号","sgxkzh":"[2018]施[朝]装字0021号","bjDate":1517474384000,"region":"朝阳区","address":"朝阳北路82号院1至2号楼（平房）","projectType":"装修","area":36231.58,"projectCost":2012.1557,"high":0,"contractPeriod":"149","tfzyDate":null,"singleProject":"null,1#、2#住宅楼（公租房）[朝阳区高井2号地保障性住房用地（配建商品房及公建）项目]局部内装修工程","registerDate":null,"registerMan":null,"updateDate":null,"updateMan":null,"versionId":null,"areaStart":null,"areaEnd":null,"projectCostStart":null,"projectCostEnd":null,"beginDate":null,"endDate":null,"projectGpsX":null,"projectGpsY":null,"projectGpsZ":null,"floorUp":null,"monitorDept":null,"monitors":null,"bjMan":null,"projectStatus":null,"dataIp":null,"jsdw":null,"sgdw":null,"jldw":null,"kcdw":null,"sjdw":null,"jsdwMan":null,"sgdwMan":null,"jldwMan":null},[{"unitType":"建设单位","unitName":"北京市保障性住房建设投资中心","gradeNo":"","grade":"","personName":null,"duty":null,"title":null,"regisQualification":null,"titleNo":null,"tel":null,"safeProduceNum":null},{"unitType":"监理单位","unitName":"北京华厦工程项目管理有限责任公司","gradeNo":"E111006407","grade":"甲级","personName":null,"duty":null,"title":null,"regisQualification":null,"titleNo":null,"tel":null,"safeProduceNum":null},{"unitType":"施工单位","unitName":"北京房地集团有限公司、山东金马首装饰材料有限公司","gradeNo":"D111002773","grade":"一级","personName":null,"duty":null,"title":null,"regisQualification":null,"titleNo":null,"tel":null,"safeProduceNum":"JZ安许证字【2015】112898-1 "}],[]]
     * result : 查询工程信息成功
     * verification : true
     * showSubmit : 0
     */

    private String result;
    private boolean verification;
    private String showSubmit;
    private List<ProjectListBean> projectList;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isVerification() {
        return verification;
    }

    public void setVerification(boolean verification) {
        this.verification = verification;
    }

    public String getShowSubmit() {
        return showSubmit;
    }

    public void setShowSubmit(String showSubmit) {
        this.showSubmit = showSubmit;
    }

    public List<ProjectListBean> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectListBean> projectList) {
        this.projectList = projectList;
    }

    public static class ProjectListBean {
        /**
         * projectId : 1835d27d-625e-4a1b-bffd-47608e89aa08
         * projectCode : 201709282009
         * projectName : 1#、2#住宅楼（公租房）[朝阳区高井2号地保障性住房用地（配建商品房及公建）项目]局部内装修工程
         * projectNum : 京建安[2018]0054号
         * sgxkzh : [2018]施[朝]装字0021号
         * bjDate : 1517474384000
         * region : 朝阳区
         * address : 朝阳北路82号院1至2号楼（平房）
         * projectType : 装修
         * area : 36231.58
         * projectCost : 2012.1557
         * high : 0
         * contractPeriod : 149
         * tfzyDate : null
         * singleProject : null,1#、2#住宅楼（公租房）[朝阳区高井2号地保障性住房用地（配建商品房及公建）项目]局部内装修工程
         * registerDate : null
         * registerMan : null
         * updateDate : null
         * updateMan : null
         * versionId : null
         * areaStart : null
         * areaEnd : null
         * projectCostStart : null
         * projectCostEnd : null
         * beginDate : null
         * endDate : null
         * projectGpsX : null
         * projectGpsY : null
         * projectGpsZ : null
         * floorUp : null
         * monitorDept : null
         * monitors : null
         * bjMan : null
         * projectStatus : null
         * dataIp : null
         * jsdw : null
         * sgdw : null
         * jldw : null
         * kcdw : null
         * sjdw : null
         * jsdwMan : null
         * sgdwMan : null
         * jldwMan : null
         */

        private String projectId;
        private String projectCode;
        private String projectName;
        private String projectNum;
        private String sgxkzh;
        private long bjDate;
        private String region;
        private String address;
        private String projectType;
        private double area;
        private double projectCost;
        private int high;
        private String contractPeriod;
        private Object tfzyDate;
        private String singleProject;
        private Object registerDate;
        private Object registerMan;
        private Object updateDate;
        private Object updateMan;
        private Object versionId;
        private Object areaStart;
        private Object areaEnd;
        private Object projectCostStart;
        private Object projectCostEnd;
        private Object beginDate;
        private Object endDate;
        private Object projectGpsX;
        private Object projectGpsY;
        private Object projectGpsZ;
        private Object floorUp;
        private Object monitorDept;
        private Object monitors;
        private Object bjMan;
        private Object projectStatus;
        private Object dataIp;
        private Object jsdw;
        private Object sgdw;
        private Object jldw;
        private Object kcdw;
        private Object sjdw;
        private Object jsdwMan;
        private Object sgdwMan;
        private Object jldwMan;

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

        public long getBjDate() {
            return bjDate;
        }

        public void setBjDate(long bjDate) {
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

        public int getHigh() {
            return high;
        }

        public void setHigh(int high) {
            this.high = high;
        }

        public String getContractPeriod() {
            return contractPeriod;
        }

        public void setContractPeriod(String contractPeriod) {
            this.contractPeriod = contractPeriod;
        }

        public Object getTfzyDate() {
            return tfzyDate;
        }

        public void setTfzyDate(Object tfzyDate) {
            this.tfzyDate = tfzyDate;
        }

        public String getSingleProject() {
            return singleProject;
        }

        public void setSingleProject(String singleProject) {
            this.singleProject = singleProject;
        }

        public Object getRegisterDate() {
            return registerDate;
        }

        public void setRegisterDate(Object registerDate) {
            this.registerDate = registerDate;
        }

        public Object getRegisterMan() {
            return registerMan;
        }

        public void setRegisterMan(Object registerMan) {
            this.registerMan = registerMan;
        }

        public Object getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(Object updateDate) {
            this.updateDate = updateDate;
        }

        public Object getUpdateMan() {
            return updateMan;
        }

        public void setUpdateMan(Object updateMan) {
            this.updateMan = updateMan;
        }

        public Object getVersionId() {
            return versionId;
        }

        public void setVersionId(Object versionId) {
            this.versionId = versionId;
        }

        public Object getAreaStart() {
            return areaStart;
        }

        public void setAreaStart(Object areaStart) {
            this.areaStart = areaStart;
        }

        public Object getAreaEnd() {
            return areaEnd;
        }

        public void setAreaEnd(Object areaEnd) {
            this.areaEnd = areaEnd;
        }

        public Object getProjectCostStart() {
            return projectCostStart;
        }

        public void setProjectCostStart(Object projectCostStart) {
            this.projectCostStart = projectCostStart;
        }

        public Object getProjectCostEnd() {
            return projectCostEnd;
        }

        public void setProjectCostEnd(Object projectCostEnd) {
            this.projectCostEnd = projectCostEnd;
        }

        public Object getBeginDate() {
            return beginDate;
        }

        public void setBeginDate(Object beginDate) {
            this.beginDate = beginDate;
        }

        public Object getEndDate() {
            return endDate;
        }

        public void setEndDate(Object endDate) {
            this.endDate = endDate;
        }

        public Object getProjectGpsX() {
            return projectGpsX;
        }

        public void setProjectGpsX(Object projectGpsX) {
            this.projectGpsX = projectGpsX;
        }

        public Object getProjectGpsY() {
            return projectGpsY;
        }

        public void setProjectGpsY(Object projectGpsY) {
            this.projectGpsY = projectGpsY;
        }

        public Object getProjectGpsZ() {
            return projectGpsZ;
        }

        public void setProjectGpsZ(Object projectGpsZ) {
            this.projectGpsZ = projectGpsZ;
        }

        public Object getFloorUp() {
            return floorUp;
        }

        public void setFloorUp(Object floorUp) {
            this.floorUp = floorUp;
        }

        public Object getMonitorDept() {
            return monitorDept;
        }

        public void setMonitorDept(Object monitorDept) {
            this.monitorDept = monitorDept;
        }

        public Object getMonitors() {
            return monitors;
        }

        public void setMonitors(Object monitors) {
            this.monitors = monitors;
        }

        public Object getBjMan() {
            return bjMan;
        }

        public void setBjMan(Object bjMan) {
            this.bjMan = bjMan;
        }

        public Object getProjectStatus() {
            return projectStatus;
        }

        public void setProjectStatus(Object projectStatus) {
            this.projectStatus = projectStatus;
        }

        public Object getDataIp() {
            return dataIp;
        }

        public void setDataIp(Object dataIp) {
            this.dataIp = dataIp;
        }

        public Object getJsdw() {
            return jsdw;
        }

        public void setJsdw(Object jsdw) {
            this.jsdw = jsdw;
        }

        public Object getSgdw() {
            return sgdw;
        }

        public void setSgdw(Object sgdw) {
            this.sgdw = sgdw;
        }

        public Object getJldw() {
            return jldw;
        }

        public void setJldw(Object jldw) {
            this.jldw = jldw;
        }

        public Object getKcdw() {
            return kcdw;
        }

        public void setKcdw(Object kcdw) {
            this.kcdw = kcdw;
        }

        public Object getSjdw() {
            return sjdw;
        }

        public void setSjdw(Object sjdw) {
            this.sjdw = sjdw;
        }

        public Object getJsdwMan() {
            return jsdwMan;
        }

        public void setJsdwMan(Object jsdwMan) {
            this.jsdwMan = jsdwMan;
        }

        public Object getSgdwMan() {
            return sgdwMan;
        }

        public void setSgdwMan(Object sgdwMan) {
            this.sgdwMan = sgdwMan;
        }

        public Object getJldwMan() {
            return jldwMan;
        }

        public void setJldwMan(Object jldwMan) {
            this.jldwMan = jldwMan;
        }
    }
}
