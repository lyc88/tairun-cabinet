package com.tairun.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Selfcabinet {
    private Integer id;

    private String code;

    private String info;

    private Byte update;

    private String updateStr;

    public String getUpdateStr() {
        return updateStr;
    }

    public void setUpdateStr(String updateStr) {
        this.updateStr = updateStr;
    }

    private Integer imgId;

    private Integer imgUpdate;

    private Date createDate;

    private Date updateDate;

    private String createDateStr;

    private String updateDateStr;

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public String getUpdateDateStr() {
        return updateDateStr;
    }

    public void setUpdateDateStr(String updateDateStr) {
        this.updateDateStr = updateDateStr;
    }

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Byte getUpdate() {
        return update;
    }

    public void setUpdate(Byte update) {
        this.update = update;
        if(this.update==new Byte("0")){
            this.setUpdateStr("否");
        }else if(this.update==new Byte("1")){
            this.setUpdateStr("是");
        }
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public Integer getImgUpdate() {
        return imgUpdate;
    }

    public void setImgUpdate(Integer imgUpdate) {
        this.imgUpdate = imgUpdate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
        if(null != this.createDate){
            this.createDateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.createDate);
        }
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
        if(null != this.updateDate){
            this.updateDateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.updateDate);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}