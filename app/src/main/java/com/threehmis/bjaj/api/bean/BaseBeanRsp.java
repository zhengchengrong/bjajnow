/*
 * Copyright (c) zcr 2018.
 */

package com.threehmis.bjaj.api.bean;

import java.util.List;

/**
 * Created by 3hcd on 2016/12/13 0013.
 */

public class BaseBeanRsp<DATA>  {
    /**
     * projectList : []
     * result : 未找到监督计划，新建一份监督计划
     * verification : true
     * showSubmit : 1
     * jokerVO : {"address":"朝阳区新源南路8号院3号楼（左家庄）","beginDate":"","endDate":"","dangerousList":[],"monitorList":[{"monitorType":"监督组长","monitorTitleNo":"112121212","monitorName":"武卫兵"},{"monitorType":"监督组长","monitorTitleNo":"112121212","monitorName":"武卫兵"},{"monitorType":"监督组长","monitorTitleNo":"112121212","monitorName":"武卫兵"},{"monitorType":"监督组长","monitorTitleNo":"112121212","monitorName":"武卫兵"}],"projectId":"5f82526c-ffae-4b4d-b63b-0d357c7db42d","projectNum":"京建安[2018]0060号","projectName":"朝阳区新源南路8号院3号楼101室局部内装工程","jdjhJSDW":"北京新荣记餐饮管理有限公司"}
     */

    public  String result;
    public boolean verification;
    public    String showSubmit;
    public  JokerVOBean jokerVO;
    public List<DATA> projectList;
    /**
     * objectVO : {"evaluationCriterion":"未建立安全生产责任制的，扣10分；责任制未经责任人签字确认的，扣5分；未对安全生产责任制进行考核的，扣5分；各种安全生产制度不健全的，扣5分；未备有各工种安全技术操作规程的，扣5分","evaluationPhase":"施工前准备阶段，土方开挖及基坑支护阶段，基础施工阶段，结构施工1/2前，结构施工1/2后，装饰施工阶段，完工预验阶段","inspectionObject":"","isPhotoUpload":"0","lawDataContent":"【依据一】《建设工程安全生产管理条例》【依据二】《北京市建设工程施工现场管理办法》（北京市人民政府令第247号）","lawDataIndex":"1","lawDataScore":"10","lawDataTitle":"安全生产责任制符合要求","lawDataType":"施工安全行为","pk":"57f82b35-4d7c-42c4-af88-929cfa0d6268","subItem":"安全管理"}
     * projectList : []
     */

    private ObjectVOBean objectVO;


    // 根据后台给的信息，看什么代码代表成功，我的是 001
    public boolean isSuccess() {
        return verification == true;
    }
    public boolean isError() {
        return verification == false;
    }
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

    public JokerVOBean getJokerVO() {
        return jokerVO;
    }

    public void setJokerVO(JokerVOBean jokerVO) {
        this.jokerVO = jokerVO;
    }

    public List<DATA> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<DATA> projectList) {
        this.projectList = projectList;
    }

    public ObjectVOBean getObjectVO() {
        return objectVO;
    }

    public void setObjectVO(ObjectVOBean objectVO) {
        this.objectVO = objectVO;
    }

    public  class ObjectVOBean {
        /**
         * evaluationCriterion : 未建立安全生产责任制的，扣10分；责任制未经责任人签字确认的，扣5分；未对安全生产责任制进行考核的，扣5分；各种安全生产制度不健全的，扣5分；未备有各工种安全技术操作规程的，扣5分
         * evaluationPhase : 施工前准备阶段，土方开挖及基坑支护阶段，基础施工阶段，结构施工1/2前，结构施工1/2后，装饰施工阶段，完工预验阶段
         * inspectionObject :
         * isPhotoUpload : 0
         * lawDataContent : 【依据一】《建设工程安全生产管理条例》【依据二】《北京市建设工程施工现场管理办法》（北京市人民政府令第247号）
         * lawDataIndex : 1
         * lawDataScore : 10
         * lawDataTitle : 安全生产责任制符合要求
         * lawDataType : 施工安全行为
         * pk : 57f82b35-4d7c-42c4-af88-929cfa0d6268
         * subItem : 安全管理
         */

        private String evaluationCriterion;
        private String evaluationPhase;
        private String inspectionObject;
        private String isPhotoUpload;
        private String lawDataContent;
        private String lawDataIndex;
        private String lawDataScore;
        private String lawDataTitle;
        private String lawDataType;
        private String pk;
        private String subItem;

        public String getEvaluationCriterion() {
            return evaluationCriterion;
        }

        public void setEvaluationCriterion(String evaluationCriterion) {
            this.evaluationCriterion = evaluationCriterion;
        }

        public String getEvaluationPhase() {
            return evaluationPhase;
        }

        public void setEvaluationPhase(String evaluationPhase) {
            this.evaluationPhase = evaluationPhase;
        }

        public String getInspectionObject() {
            return inspectionObject;
        }

        public void setInspectionObject(String inspectionObject) {
            this.inspectionObject = inspectionObject;
        }

        public String getIsPhotoUpload() {
            return isPhotoUpload;
        }

        public void setIsPhotoUpload(String isPhotoUpload) {
            this.isPhotoUpload = isPhotoUpload;
        }

        public String getLawDataContent() {
            return lawDataContent;
        }

        public void setLawDataContent(String lawDataContent) {
            this.lawDataContent = lawDataContent;
        }

        public String getLawDataIndex() {
            return lawDataIndex;
        }

        public void setLawDataIndex(String lawDataIndex) {
            this.lawDataIndex = lawDataIndex;
        }

        public String getLawDataScore() {
            return lawDataScore;
        }

        public void setLawDataScore(String lawDataScore) {
            this.lawDataScore = lawDataScore;
        }

        public String getLawDataTitle() {
            return lawDataTitle;
        }

        public void setLawDataTitle(String lawDataTitle) {
            this.lawDataTitle = lawDataTitle;
        }

        public String getLawDataType() {
            return lawDataType;
        }

        public void setLawDataType(String lawDataType) {
            this.lawDataType = lawDataType;
        }

        public String getPk() {
            return pk;
        }

        public void setPk(String pk) {
            this.pk = pk;
        }

        public String getSubItem() {
            return subItem;
        }

        public void setSubItem(String subItem) {
            this.subItem = subItem;
        }
    }

    public  class JokerVOBean {
        /**
         * address : 朝阳区新源南路8号院3号楼（左家庄）
         * beginDate :
         * endDate :
         * dangerousList : []
         * monitorList : [{"monitorType":"监督组长","monitorTitleNo":"112121212","monitorName":"武卫兵"},{"monitorType":"监督组长","monitorTitleNo":"112121212","monitorName":"武卫兵"},{"monitorType":"监督组长","monitorTitleNo":"112121212","monitorName":"武卫兵"},{"monitorType":"监督组长","monitorTitleNo":"112121212","monitorName":"武卫兵"}]
         * projectId : 5f82526c-ffae-4b4d-b63b-0d357c7db42d
         * projectNum : 京建安[2018]0060号
         * projectName : 朝阳区新源南路8号院3号楼101室局部内装工程
         * jdjhJSDW : 北京新荣记餐饮管理有限公司
         */

        private String address;
        private String beginDate;
        private String endDate;
        private String projectId;
        private String projectNum;
        private String projectName;
        private String jdjhJSDW;
        private String monitor;
        private String monitorLeader;
        private String projectCode;





        /**
         * username : szwwb
         * password : 202cb962ac59075b964b07152d234b70
         * personId : BC65D741-793D-4343-8838-A1F53875FE09
         * personName : 武卫兵
         * userId : BB624EDA-D758-4DBA-9360-52FB07C9AFE3
         * deptName : 房建监督一科
         * profession :
         * duty : 科长
         * title :
         * titleNo : 112121212
         * grade :
         * mobilePhone : 12345678901
         * updateDate : 2017-06-18
         */

        private String username;
        private String password;
        private String personId;
        private String personName;
        private String userId;
        private String deptName;
        private String profession;
        private String duty;
        private String title;
        private String titleNo;
        private String grade;
        private String mobilePhone;
        private String updateDate;
        /**
         * jdProject : 1184
         * zjProject : 1070
         * tgProject : 0
         * stopJDProject : 0
         * endJDProject : 114
         * lsProject : 0
         * nonLSProject : 1174
         * houseProject : 610
         * goverProject : 84
         * declaProject : 490
         * dangerProject : 5
         * totalJDGZ : 3
         * totalJDJH : 2
         * totalJDCC : 5
         * totalCFJE : 0
         * totalCFGC : 0
         * totalCFQY : 0
         * totalZGCL : 3
         */

        private String jdProject;
        private String zjProject;
        private String tgProject;
        private String stopJDProject;
        private String endJDProject;
        private String lsProject;
        private String nonLSProject;
        private String houseProject;
        private String goverProject;
        private String declaProject;
        private String dangerProject;
        private String totalJDGZ;
        private String totalJDJH;
        private String totalJDCC;
        private String totalCFJE;
        private String totalCFGC;
        private String totalCFQY;
        private String totalZGCL;

        public String getProjectCode() {
            return projectCode;
        }

        public void setProjectCode(String projectCode) {
            this.projectCode = projectCode;
        }

        public String getMonitor() {
            return monitor;
        }

        public void setMonitor(String monitor) {
            this.monitor = monitor;
        }

        public String getMonitorLeader() {
            return monitorLeader;
        }

        public void setMonitorLeader(String monitorLeader) {
            this.monitorLeader = monitorLeader;
        }

        private List<?> dangerousList;
        private List<MonitorListBean> monitorList;
        private List<MonitorListBean> personList;

        private List<MonitorListBean> jokerVOList;

        public List<MonitorListBean> getJokerVOList() {
            return jokerVOList;
        }

        public void setJokerVOList(List<MonitorListBean> jokerVOList) {
            this.jokerVOList = jokerVOList;
        }

        public List<MonitorListBean> getPersonList() {
            return personList;
        }

        public void setPersonList(List<MonitorListBean> personList) {
            this.personList = personList;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBeginDate() {
            return beginDate;
        }

        public void setBeginDate(String beginDate) {
            this.beginDate = beginDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
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

        public String getJdjhJSDW() {
            return jdjhJSDW;
        }

        public void setJdjhJSDW(String jdjhJSDW) {
            this.jdjhJSDW = jdjhJSDW;
        }

        public List<?> getDangerousList() {
            return dangerousList;
        }

        public void setDangerousList(List<?> dangerousList) {
            this.dangerousList = dangerousList;
        }

        public List<MonitorListBean> getMonitorList() {
            return monitorList;
        }

        public void setMonitorList(List<MonitorListBean> monitorList) {
            this.monitorList = monitorList;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPersonId() {
            return personId;
        }

        public void setPersonId(String personId) {
            this.personId = personId;
        }

        public String getPersonName() {
            return personName;
        }

        public void setPersonName(String personName) {
            this.personName = personName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public String getDuty() {
            return duty;
        }

        public void setDuty(String duty) {
            this.duty = duty;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitleNo() {
            return titleNo;
        }

        public void setTitleNo(String titleNo) {
            this.titleNo = titleNo;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getJdProject() {
            return jdProject;
        }

        public void setJdProject(String jdProject) {
            this.jdProject = jdProject;
        }

        public String getZjProject() {
            return zjProject;
        }

        public void setZjProject(String zjProject) {
            this.zjProject = zjProject;
        }

        public String getTgProject() {
            return tgProject;
        }

        public void setTgProject(String tgProject) {
            this.tgProject = tgProject;
        }

        public String getStopJDProject() {
            return stopJDProject;
        }

        public void setStopJDProject(String stopJDProject) {
            this.stopJDProject = stopJDProject;
        }

        public String getEndJDProject() {
            return endJDProject;
        }

        public void setEndJDProject(String endJDProject) {
            this.endJDProject = endJDProject;
        }

        public String getLsProject() {
            return lsProject;
        }

        public void setLsProject(String lsProject) {
            this.lsProject = lsProject;
        }

        public String getNonLSProject() {
            return nonLSProject;
        }

        public void setNonLSProject(String nonLSProject) {
            this.nonLSProject = nonLSProject;
        }

        public String getHouseProject() {
            return houseProject;
        }

        public void setHouseProject(String houseProject) {
            this.houseProject = houseProject;
        }

        public String getGoverProject() {
            return goverProject;
        }

        public void setGoverProject(String goverProject) {
            this.goverProject = goverProject;
        }

        public String getDeclaProject() {
            return declaProject;
        }

        public void setDeclaProject(String declaProject) {
            this.declaProject = declaProject;
        }

        public String getDangerProject() {
            return dangerProject;
        }

        public void setDangerProject(String dangerProject) {
            this.dangerProject = dangerProject;
        }

        public String getTotalJDGZ() {
            return totalJDGZ;
        }

        public void setTotalJDGZ(String totalJDGZ) {
            this.totalJDGZ = totalJDGZ;
        }

        public String getTotalJDJH() {
            return totalJDJH;
        }

        public void setTotalJDJH(String totalJDJH) {
            this.totalJDJH = totalJDJH;
        }

        public String getTotalJDCC() {
            return totalJDCC;
        }

        public void setTotalJDCC(String totalJDCC) {
            this.totalJDCC = totalJDCC;
        }

        public String getTotalCFJE() {
            return totalCFJE;
        }

        public void setTotalCFJE(String totalCFJE) {
            this.totalCFJE = totalCFJE;
        }

        public String getTotalCFGC() {
            return totalCFGC;
        }

        public void setTotalCFGC(String totalCFGC) {
            this.totalCFGC = totalCFGC;
        }

        public String getTotalCFQY() {
            return totalCFQY;
        }

        public void setTotalCFQY(String totalCFQY) {
            this.totalCFQY = totalCFQY;
        }

        public String getTotalZGCL() {
            return totalZGCL;
        }

        public void setTotalZGCL(String totalZGCL) {
            this.totalZGCL = totalZGCL;
        }

        public  class MonitorListBean {
            /**
             * monitorType : 监督组长
             * monitorTitleNo : 112121212
             * monitorName : 武卫兵
             */
            private String monitorType;
            private String monitorTitleNo;
            private String monitorName;
            private String personId;

            private String branchName;
            private String pk="";

            private String lawType;
            private String lawContent;
            private String id;
            private String lawTitle="";
            private String personDuty;

            private String isPhotoUpload="";
            private String lawDataScore="";

            public String getIsPhotoUpload() {
                return isPhotoUpload;
            }

            public void setIsPhotoUpload(String isPhotoUpload) {
                this.isPhotoUpload = isPhotoUpload;
            }

            public String getLawDataScore() {
                return lawDataScore;
            }

            public void setLawDataScore(String lawDataScore) {
                this.lawDataScore = lawDataScore;
            }

            public String getLawTitle() {
                return lawTitle;
            }

            public void setLawTitle(String lawTitle) {
                this.lawTitle = lawTitle;
            }

            private String personName;
            private String unitType;
            private String unitName;

            public String getPersonDuty() {
                return personDuty;
            }

            public void setPersonDuty(String personDuty) {
                this.personDuty = personDuty;
            }

            public String getPersonName() {
                return personName;
            }

            public void setPersonName(String personName) {
                this.personName = personName;
            }

            public String getUnitType() {
                return unitType;
            }

            public void setUnitType(String unitType) {
                this.unitType = unitType;
            }

            public String getUnitName() {
                return unitName;
            }

            public void setUnitName(String unitName) {
                this.unitName = unitName;
            }

            public String getLawType() {
                return lawType;
            }

            public void setLawType(String lawType) {
                this.lawType = lawType;
            }

            public String getLawContent() {
                return lawContent;
            }

            public void setLawContent(String lawContent) {
                this.lawContent = lawContent;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
            public String getBranchName() {
                return branchName;
            }

            public void setBranchName(String branchName) {
                this.branchName = branchName;
            }

            public String getPk() {
                return pk;
            }

            public void setPk(String pk) {
                this.pk = pk;
            }

            public String getPersonId() {
                return personId;
            }

            public void setPersonId(String personId) {
                this.personId = personId;
            }

            public String getMonitorType() {
                return monitorType;
            }

            public void setMonitorType(String monitorType) {
                this.monitorType = monitorType;
            }

            public String getMonitorTitleNo() {
                return monitorTitleNo;
            }

            public void setMonitorTitleNo(String monitorTitleNo) {
                this.monitorTitleNo = monitorTitleNo;
            }

            public String getMonitorName() {
                return monitorName;
            }

            public void setMonitorName(String monitorName) {
                this.monitorName = monitorName;
            }
        }
    }

/*    public boolean verification;  //是否有数据返回 true 有 flase 没有

//    public int total, iscollection;
//
    public String result; //标示后台接口异常信息 如 无此ID数据

    public int showSubmit; //showSubmit("0" 显示　"1" 不显示)

    public ArrayList<DATA> projectList; //返回的数据对象





    // 根据后台给的信息，看什么代码代表成功，我的是 001
    public boolean isSuccess() {
        return verification == true;
    }
    public boolean isError() {
        return verification == false;
    }



    public boolean isVerification() {
        return verification;
    }

    public void setVerification(boolean verification) {
        this.verification = verification;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getShowSubmit() {
        return showSubmit;
    }

    public void setShowSubmit(int showSubmit) {
        this.showSubmit = showSubmit;
    }

    public ArrayList<DATA> getProjectList() {
        return projectList;
    }

    public void setProjectList(ArrayList<DATA> projectList) {
        this.projectList = projectList;
    }*/
}
