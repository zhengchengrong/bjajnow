package com.threehmis.bjaj.api.bean.respon;

/**
 * Created by llz on 2018/2/24.
 */

public class ProjectInfoTwoRep {


    /**
     * unitType : 监理单位
     * unitName : 北京国电德胜工程项目管理有限公司
     * gradeNo : E111006423
     * grade : 甲级
     */

    private String unitType;
    private String unitName;
    private String gradeNo;
    private String grade;

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

    public String getGradeNo() {
        return gradeNo;
    }

    public void setGradeNo(String gradeNo) {
        this.gradeNo = gradeNo;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
