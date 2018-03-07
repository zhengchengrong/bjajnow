package com.threehmis.bjaj.api.bean.respon;

/**
 * Created by llz on 2018/3/7.
 */

public class WillDoRsp {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String type;
    private String date;

}
