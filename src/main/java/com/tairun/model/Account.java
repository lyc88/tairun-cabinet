package com.tairun.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {
    private Integer id;

    private String telephone;

    private Double account;

    private Date createDate;

    /**
     * 时间视图值
     */
    private String createDateStr;

    public String getCreateDateStr() {
        return createDateStr;
    }

    private String name;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}