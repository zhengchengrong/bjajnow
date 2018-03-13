/*
 * Copyright (c) zcr 2018.
 */

package com.threehmis.bjaj.api.bean.request;

/**
 * Created by llz on 2018/3/13.
 */

public class AllCityBeanReq {

    /**
     * year :
     * quarter :
     * isMonitorUnit : 0
     */

    private String year="";
    private String quarter="";
    private String isMonitorUnit="";

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getIsMonitorUnit() {
        return isMonitorUnit;
    }

    public void setIsMonitorUnit(String isMonitorUnit) {
        this.isMonitorUnit = isMonitorUnit;
    }
}
