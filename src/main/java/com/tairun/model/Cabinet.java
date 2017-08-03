package com.tairun.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cabinet {
    private Integer id;

    private String code;

    private Integer number;

    private Byte status;

    private String statusStr;

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    private String courierTelephone;

    private Date createDate;

    private Date beginDate;

    private Date endDate;

    private String createDateStr;

    private String beginDateStr;

    private String endDateStr;

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public String getBeginDateStr() {
        return beginDateStr;
    }

    public void setBeginDateStr(String beginDateStr) {
        this.beginDateStr = beginDateStr;

    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }

    private String contactsTelephone;

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
        if(this.status==0){
           this.setStatusStr("否");
        }else if(this.status==1){
            this.setStatusStr("是");
        }
    }

    public String getCourierTelephone() {
        return courierTelephone;
    }

    public void setCourierTelephone(String courierTelephone) {
        this.courierTelephone = courierTelephone == null ? null : courierTelephone.trim();
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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
        if(null != this.beginDate){
            this.beginDateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.beginDate);
        }
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
        if(null != this.endDate){
            this.endDateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.endDate);
        }
    }

    public String getContactsTelephone() {
        return contactsTelephone;
    }

    public void setContactsTelephone(String contactsTelephone) {
        this.contactsTelephone = contactsTelephone == null ? null : contactsTelephone.trim();
    }
}