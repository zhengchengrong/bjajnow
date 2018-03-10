/*
 * Copyright (c) zcr 2018.
 */

package com.threehmis.bjaj.api.bean.respon;

/**
 * Created by llz on 2018/3/8.
 */

public class SupervisionJDRsp {


    /**
     * personDuty : 项目安全负责人
     * personName : 斯列坤
     * unitType : 设计单位
     * unitName : 浙江亚厦装饰股份有限公司
     */

    private String personDuty;
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
}
