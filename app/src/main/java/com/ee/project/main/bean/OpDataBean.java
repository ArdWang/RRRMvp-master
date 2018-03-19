package com.ee.project.main.bean;

/**
 * Created by Administrator on 2018/3/15.
 *
 */

public class OpDataBean {
    private Integer opImage;

    public Integer getOpImage() {
        return opImage;
    }

    public void setOpImage(Integer opImage) {
        this.opImage = opImage;
    }

    public String getOpTitle() {
        return opTitle;
    }

    public void setOpTitle(String opTitle) {
        this.opTitle = opTitle;
    }

    private String opTitle;

    public OpDataBean(Integer opImage,String opTitle){
        this.opImage = opImage;
        this.opTitle = opTitle;
    }
}
