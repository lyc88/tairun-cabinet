package com.tairun.businessmodel;

/**
 * Created by THINK on 2017/8/2.
 */
public class WaybillNumberb extends IdenAction{
    public String waybill_number;
    public String customer_number;

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

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int result;
}
