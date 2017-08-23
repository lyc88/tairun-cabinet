package com.tairun.model;

import java.util.Date;

public class Pickupd {
    private Integer id;

    private String ordertype;

    private String acount;

    private String code;

    private Double charge;

    private Date createdate;

    private String customernumber;

    private String waybillnumbrer;

    private String boxnumber;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype == null ? null : ordertype.trim();
    }

    public String getAcount() {
        return acount;
    }

    public void setAcount(String acount) {
        this.acount = acount == null ? null : acount.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getCustomernumber() {
        return customernumber;
    }

    public void setCustomernumber(String customernumber) {
        this.customernumber = customernumber == null ? null : customernumber.trim();
    }

    public String getWaybillnumbrer() {
        return waybillnumbrer;
    }

    public void setWaybillnumbrer(String waybillnumbrer) {
        this.waybillnumbrer = waybillnumbrer == null ? null : waybillnumbrer.trim();
    }

    public String getBoxnumber() {
        return boxnumber;
    }

    public void setBoxnumber(String boxnumber) {
        this.boxnumber = boxnumber == null ? null : boxnumber.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}