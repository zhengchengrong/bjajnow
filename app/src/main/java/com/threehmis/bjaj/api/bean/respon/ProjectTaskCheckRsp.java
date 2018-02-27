package com.threehmis.bjaj.api.bean.respon;

import java.io.Serializable;
import java.util.List;

/**
 * Created by llz on 2018/2/27.
 */

public class ProjectTaskCheckRsp implements Serializable {


    /**
     * id : 63c19cf2-4347-43fa-81cd-025619006c94
     * projectId : 1bb69ede-b55f-46e8-b35b-1540ae7bd152
     * projectNum : 10012454544000
     * projectName : 人民日报社老旧小区抗震节能综合改造工程（C区）
     * checkNum : 10012455
     * checkBasis : 计划
     * checkDate : 2018-01-24 08:00:00.0
     * checkMen : 张东萍
     * checkDept :
     * checkManager :
     * otherProblem : 1211212
     * reformType : 立即整改
     * reformDate : 2018-01-31 08:00:00.0
     * isUnitJd : 1
     * isCountyJd : 1
     * isCityJd : 0
     * noticeNum : 1001245454400020180129
     * signMan : 武卫兵
     * signDate : 2018-01-29 03:36:40.0
     * replyContent : 整改回复
     * replyDate : null
     * confirmComment :
     * confirmDate : null
     * checkStatus : 2
     * createMan : 武卫兵
     * createDate : 2018-01-29 03:34:04.0
     * updateDate : 2018-01-29 03:36:40.0
     * versionId : 3HJD0035
     * checkDivisionVOSet : [{"id":"9070a45c-65d5-49ee-b88d-b6593e509158","checkTaskID":"63c19cf2-4347-43fa-81cd-025619006c94","singleProject":"3adc9def-ee9a-4e56-8ca8-331174fccb9d","checkManId":"05290323-636B-4857-98FD-8570478B22EA","checkMan":"张东萍","checkType":"塔式起重机","checkContent":"物料提升机安装完毕后，施工总承包应组织出租、安装、监理等有关单位进行验收，验收合格方可使用。","checkPart":"检查部位","checkResult":"问题项","resultContent":"12212","checkStatus":"1","createMan":"武卫兵","createDate":"2018-01-29 03:34:04.0","updateDate":"2018-01-29 03:34:04.0","versionId":"3HJD0035"}]
     */

    private String id;
    private String projectId;
    private String projectNum;
    private String projectName;
    private String checkNum;
    private String checkBasis;
    private String checkDate;
    private String checkMen;
    private String checkDept;
    private String checkManager;
    private String otherProblem;
    private String reformType;
    private String reformDate;
    private String isUnitJd;
    private String isCountyJd;
    private String isCityJd;
    private String noticeNum;
    private String signMan;
    private String signDate;
    private String replyContent;
    private String replyDate;
    private String confirmComment;
    private String confirmDate;
    private String checkStatus;
    private String createMan;
    private String createDate;
    private String updateDate;
    private String versionId;
    private List<CheckDivisionVOSetBean> checkDivisionVOSet;

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

    public String getCheckDept() {
        return checkDept;
    }

    public void setCheckDept(String checkDept) {
        this.checkDept = checkDept;
    }

    public String getCheckManager() {
        return checkManager;
    }

    public void setCheckManager(String checkManager) {
        this.checkManager = checkManager;
    }

    public String getOtherProblem() {
        return otherProblem;
    }

    public void setOtherProblem(String otherProblem) {
        this.otherProblem = otherProblem;
    }

    public String getReformType() {
        return reformType;
    }

    public void setReformType(String reformType) {
        this.reformType = reformType;
    }

    public String getReformDate() {
        return reformDate;
    }

    public void setReformDate(String reformDate) {
        this.reformDate = reformDate;
    }

    public String getIsUnitJd() {
        return isUnitJd;
    }

    public void setIsUnitJd(String isUnitJd) {
        this.isUnitJd = isUnitJd;
    }

    public String getIsCountyJd() {
        return isCountyJd;
    }

    public void setIsCountyJd(String isCountyJd) {
        this.isCountyJd = isCountyJd;
    }

    public String getIsCityJd() {
        return isCityJd;
    }

    public void setIsCityJd(String isCityJd) {
        this.isCityJd = isCityJd;
    }

    public String getNoticeNum() {
        return noticeNum;
    }

    public void setNoticeNum(String noticeNum) {
        this.noticeNum = noticeNum;
    }

    public String getSignMan() {
        return signMan;
    }

    public void setSignMan(String signMan) {
        this.signMan = signMan;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public String getConfirmComment() {
        return confirmComment;
    }

    public void setConfirmComment(String confirmComment) {
        this.confirmComment = confirmComment;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
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

    public List<CheckDivisionVOSetBean> getCheckDivisionVOSet() {
        return checkDivisionVOSet;
    }

    public void setCheckDivisionVOSet(List<CheckDivisionVOSetBean> checkDivisionVOSet) {
        this.checkDivisionVOSet = checkDivisionVOSet;
    }

    public static class CheckDivisionVOSetBean implements Serializable {
        /**
         * id : 9070a45c-65d5-49ee-b88d-b6593e509158
         * checkTaskID : 63c19cf2-4347-43fa-81cd-025619006c94
         * singleProject : 3adc9def-ee9a-4e56-8ca8-331174fccb9d
         * checkManId : 05290323-636B-4857-98FD-8570478B22EA
         * checkMan : 张东萍
         * checkType : 塔式起重机
         * checkContent : 物料提升机安装完毕后，施工总承包应组织出租、安装、监理等有关单位进行验收，验收合格方可使用。
         * checkPart : 检查部位
         * checkResult : 问题项
         * resultContent : 12212
         * checkStatus : 1
         * createMan : 武卫兵
         * createDate : 2018-01-29 03:34:04.0
         * updateDate : 2018-01-29 03:34:04.0
         * versionId : 3HJD0035
         */

        private String id;
        private String checkTaskID;
        private String singleProject;
        private String checkManId;
        private String checkMan;
        private String checkType;
        private String checkContent;
        private String checkPart;
        private String checkResult;
        private String resultContent;
        private String checkStatus;
        private String createMan;
        private String createDate;
        private String updateDate;
        private String versionId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCheckTaskID() {
            return checkTaskID;
        }

        public void setCheckTaskID(String checkTaskID) {
            this.checkTaskID = checkTaskID;
        }

        public String getSingleProject() {
            return singleProject;
        }

        public void setSingleProject(String singleProject) {
            this.singleProject = singleProject;
        }

        public String getCheckManId() {
            return checkManId;
        }

        public void setCheckManId(String checkManId) {
            this.checkManId = checkManId;
        }

        public String getCheckMan() {
            return checkMan;
        }

        public void setCheckMan(String checkMan) {
            this.checkMan = checkMan;
        }

        public String getCheckType() {
            return checkType;
        }

        public void setCheckType(String checkType) {
            this.checkType = checkType;
        }

        public String getCheckContent() {
            return checkContent;
        }

        public void setCheckContent(String checkContent) {
            this.checkContent = checkContent;
        }

        public String getCheckPart() {
            return checkPart;
        }

        public void setCheckPart(String checkPart) {
            this.checkPart = checkPart;
        }

        public String getCheckResult() {
            return checkResult;
        }

        public void setCheckResult(String checkResult) {
            this.checkResult = checkResult;
        }

        public String getResultContent() {
            return resultContent;
        }

        public void setResultContent(String resultContent) {
            this.resultContent = resultContent;
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
    }
}
