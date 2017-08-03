package com.tairun.businessmodel;

/**
 * Created by THINK on 2017/8/2.
 */
public class LoginGuanb extends IdenAction{
    public String account;
    public int result;
    public String operaction_information;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getOperaction_information() {
        return operaction_information;
    }

    public void setOperaction_information(String operaction_information) {
        this.operaction_information = operaction_information;
    }
}
