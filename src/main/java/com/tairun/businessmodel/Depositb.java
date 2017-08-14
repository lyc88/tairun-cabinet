package com.tairun.businessmodel;

/**
 * Created by THINK on 2017/8/2.
 */
public class Depositb extends IdenAction{
    public String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    public String account;
    public String password;
    public String waybill_number;
    public String customer_number;
    public String box_type;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWaybill_number() {
        return waybill_number;
    }

    public void setWaybill_number(String waybill_number) {
        this.waybill_number = waybill_number;
    }

    public String getCustomer_number() {
        return customer_number;
    }

    public void setCustomer_number(String customer_number) {
        this.customer_number = customer_number;
    }

    public String getBox_type() {
        return box_type;
    }

    public void setBox_type(String box_type) {
        this.box_type = box_type;
    }
}
