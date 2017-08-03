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

    private String imgUpdateStr;

    public String getImgUpdateStr() {
        return imgUpdateStr;
    }

    public void setImgUpdateStr(String imgUpdateStr) {
        this.imgUpdateStr = imgUpdateStr;
    }

    private Date createDate;

    private Date updateDate;

    private String createDateStr;

    private String updateDateStr;

    private String find ;
    private String upImg;

    public String getFind() {
        return this.find;
    }

    public String getUpImg() {
        return this.upImg;
    }

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
        if(id != null){
            this.find = "<a onclick=find("+id+");>查看</a>";
    }
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
        if(this.update==0){
            this.setUpdateStr("否");
        }else if(this.update==1){
            this.setUpdateStr("是");
        }
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
        if(imgId != null){
            this.upImg = "<a onclick=upImg("+imgId+");>图片编辑</a>";
        }
    }

    public Integer getImgUpdate() {
        return imgUpdate;
    }

    public void setImgUpdate(Integer imgUpdate) {
        this.imgUpdate = imgUpdate;
        if(this.imgUpdate==0){
            this.setImgUpdateStr("否");
        }else if(this.imgUpdate==1){
            this.setImgUpdateStr("是");
        }
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