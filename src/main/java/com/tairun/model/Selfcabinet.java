package com.tairun.model;

import java.util.Date;
import java.util.List;

public class Selfcabinet {
    private Integer id;

    private String code;

    private String info;

    private Byte status;

    private Byte isUpdate;

    private Integer imgId;

    private Integer imgUpdate;

    private Date createDate;

    private Date updateDate;

    private String name;

    private List<Files> files;

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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Byte isUpdate) {
        this.isUpdate = isUpdate;
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
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public List<Files> getFiles() {
        return files;
    }

    public void setFiles(List<Files> files) {
        this.files = files;
    }
}