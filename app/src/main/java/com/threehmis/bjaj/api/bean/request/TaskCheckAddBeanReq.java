package com.threehmis.bjaj.api.bean.request;

/**
 * Created by llz on 2018/3/6.
 */

public class TaskCheckAddBeanReq {

    private String name;
    private String dtgc;
    private String checkContent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDtgc() {
        return dtgc;
    }

    public void setDtgc(String dtgc) {
        this.dtgc = dtgc;
    }

    public String getCheckContent() {
        return checkContent;
    }

    public void setCheckContent(String checkContent) {
        this.checkContent = checkContent;
    }
}
