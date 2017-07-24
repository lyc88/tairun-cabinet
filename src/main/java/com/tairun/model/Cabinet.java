package com.tairun.model;

import java.util.Date;

public class Cabinet {
    private Integer id;

    private String code;

    private Integer number;

    private Byte status;

    private String courierTelephone;

    private Date createDate;

    private Date beginDate;

    private Date endDate;

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
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getContactsTelephone() {
        return contactsTelephone;
    }

    public void setContactsTelephone(String contactsTelephone) {
        this.contactsTelephone = contactsTelephone == null ? null : contactsTelephone.trim();
    }
}