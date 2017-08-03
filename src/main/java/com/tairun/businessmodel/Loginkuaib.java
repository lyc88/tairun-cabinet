package com.tairun.businessmodel;

/**
 * Created by THINK on 2017/8/2.
 */
public class Loginkuaib extends IdenAction{
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String account;
    public double balance;
    public int result;
}
