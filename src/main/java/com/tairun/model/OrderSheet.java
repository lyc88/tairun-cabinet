package com.tairun.model;

import java.util.Date;

public class OrderSheet {
    private Integer id;

    private String identifier;

    private String orderType;

    private String waybillNumber;

    private String boxPassword;

    private String customerNumber;

    private String account;

    private Date createDate;

    private Integer boxNumber;

    private Double charge;

    private Integer isendorno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier == null ? null : identifier.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getWaybillNumber() {
        return waybillNumber;
    }

    public void setWaybillNumber(String waybillNumber) {
        this.waybillNumber = waybillNumber == null ? null : waybillNumber.trim();
    }

    public String getBoxPassword() {
        return boxPassword;
    }

    public void setBoxPassword(String boxPassword) {
        this.boxPassword = boxPassword == null ? null : boxPassword.trim();
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber == null ? null : customerNumber.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(Integer boxNumber) {
        this.boxNumber = boxNumber;
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }

    public Integer getIsendorno() {
        return isendorno;
    }

    public void setIsendorno(Integer isendorno) {
        this.isendorno = isendorno;
    }
}