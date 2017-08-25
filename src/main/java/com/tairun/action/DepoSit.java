package com.tairun.action;

import com.alibaba.fastjson.JSON;
import com.tairun.businessmodel.Depositb;
import com.tairun.model.Account;
import com.tairun.model.Log;
import com.tairun.model.OrderSheet;
import com.tairun.model.Pickupd;
import com.tairun.server.utils.SpringUtil;
import com.tairun.service.LogService;
import com.tairun.serviceimpl.AccountService;
import com.tairun.serviceimpl.OrderSheetService;
import com.tairun.serviceimpl.PickupdService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by THINK on 2017/8/24.
 */
public class DepoSit {
    private OrderSheetService orderSheetService= SpringUtil.getBean(OrderSheetService.class);
    private PickupdService pickupdService = SpringUtil.getBean(PickupdService.class);
    private AccountService accountService = SpringUtil.getBean(AccountService.class);
    private LogService logService = SpringUtil.getBean(LogService.class);
    public String depositzi(String identifier,String action,String account,String waybillType,String password,
                            String waybill_number,String customerPhone,String box_number,String box_password,String box_type){
        String response=null;
        int id = 0;
        SDKTestSendTemplateSMS sdkTestSendTemplateSMS= new SDKTestSendTemplateSMS();
        double account3=0;
        String name = null;
        String telephone = null;
        Date createdate = null;
        Pickupd pickupd = new Pickupd();
        Double charg = new Double(0.5);
        int box_number1 = Integer.parseInt(box_number);
        if (waybillType.equals("tairun")) {//把条件改为运单号去泰润商城订单中查询时不为空，则为泰润商城订单，执行下面内容
            pickupd=Setpivkudp(account,box_number,0.0,identifier,customerPhone,waybill_number,waybillType,"存件");
            pickupdService.insertPickupd(pickupd);
            response=fansuccess(identifier,action,password,waybill_number,box_type,customerPhone,account);
            String neirong="您的物件在"+identifier+"自提柜中"+box_number+"号柜子," +
                    "请及时领取";
            sdkTestSendTemplateSMS.status(customerPhone,neirong);
            // 构造日志信息
            String test=account+"存件在"+identifier+"自提柜的"+box_number+"号柜子中,此订单为泰润商城订单，运单号为"+waybill_number;
            log(test);
        } else if (waybillType.equals("other")) {//把条件改为运单号去泰润商城订单中查询时为空，则不为泰润商城订单，执行下面内容
            List<Account> list = accountService.findByTelephonetwo(account);
            Double account2 = new Double(0.5);
            charg = 0.5;
            if (list != null) {
                for (Account account1 : list) {
                    telephone = account1.getTelephone();
                    password = account1.getPassword();
                    account2 = account1.getAccount();
                    id = account1.getId();
                    name = account1.getName();
                    createdate = account1.getCreateDate();
                }
                if(box_type.equals("large")){
                    if(account2>=3){
                        charg=3.0;
                        response=zhixing(account,box_password,customerPhone,identifier,waybill_number,box_number1,charg,
                        account3,account2,telephone,password,id,name,createdate,box_number,action,waybillType,box_type);
                    }
                }if (box_type.equals("medium")){
                    if(account2>=2){
                        charg=2.0;
                        response=zhixing(account,box_password,customerPhone,identifier,waybill_number,box_number1,charg,
                                account3,account2,telephone,password,id,name,createdate,box_number,action,waybillType,box_type);
                    }
                }if(box_type.equals("small")){
                    if(account2>=1){
                        charg=1.0;
                        response=zhixing(account,box_password,customerPhone,identifier,waybill_number,box_number1,charg,
                                account3,account2,telephone,password,id,name,createdate,box_number,action,waybillType,box_type);
                    }
                }

            } else {
                response=fan(identifier,action,password,waybill_number,box_type,customerPhone,account);
            }
        }else{
            response=fan(identifier,action,password,waybill_number,box_type,customerPhone,account);
        }
        return response;
    }
    /**
     * 快递员存件记录
     */
    public Pickupd Setpivkudp(String account, String boxnumber, Double charge, String code,
                              String customernumber, String waybillnumbrer, String ordertype, String status){
        Pickupd pickupd= new Pickupd();
        pickupd.setAcount(account);
        pickupd.setBoxnumber(boxnumber);
        pickupd.setCharge(charge);
        pickupd.setCreatedate(Calendar.getInstance().getTime());
        pickupd.setCustomernumber(customernumber);
        pickupd.setOrdertype(ordertype);
        pickupd.setCode(code);
        pickupd.setWaybillnumbrer(waybillnumbrer);
        pickupd.setStatus(status);
        return pickupd;
    }
    /**
     * 存件成功后返回数据
     */
    public String  fansuccess(String identifier,String action,String password,String waybill_number,String box_type,
                              String customerPhone,String account){
        Depositb depositb = new Depositb();
        depositb.setResult("success");
        depositb.setIdentifier(identifier);
        depositb.setAction(action);
        depositb.setPassword(password);
        depositb.setWaybill_number(waybill_number);
        depositb.setBox_type(box_type);
        depositb.setCustomer_number(customerPhone);
        depositb.setAccount(account);
        int num1 = JSON.toJSONString(depositb).length();
        String response = "##@1@" + num1 + "@" + JSON.toJSONString(depositb);
        return response;
    }
    /**
     * 存件失败后返回数据
     */
    public String  fan(String identifier,String action,String password,String waybill_number,String box_type,
                       String customerPhone,String account){
        Depositb depositb = new Depositb();
        depositb.setResult("false");
        depositb.setIdentifier(identifier);
        depositb.setAction(action);
        depositb.setPassword(password);
        depositb.setWaybill_number(waybill_number);
        depositb.setBox_type(box_type);
        depositb.setCustomer_number(customerPhone);
        depositb.setAccount(account);
        int num1 = JSON.toJSONString(depositb).length();
        String response = "##@1@" + num1 + "@" + JSON.toJSONString(depositb);
        return response;
    }
    /**
     *构造日志
     */
    public void log(String response){
        Log log = new Log();
        log.setCreateDate(Calendar.getInstance().getTime());
        log.setInfo(response);
        logService.insert(log);
    }
    public String zhixing(String account,String box_password,String customerPhone,String identifier,String waybill_number
    ,int box_number1,Double charg,double account3,double account2,String telephone,String password,
                        int id,String name,Date createdate,String box_number,String action,String waybillType,
                       String box_type){
        Pickupd pickupd = new Pickupd();
        String response=null;
        OrderSheet orderSheet = new OrderSheet();
        orderSheet.setCreateDate(Calendar.getInstance().getTime());
        orderSheet.setAccount(account);
        orderSheet.setBoxPassword(box_password);
        orderSheet.setCustomerNumber(customerPhone);
        orderSheet.setIdentifier(identifier);
        orderSheet.setOrderType("1");
        orderSheet.setWaybillNumber(waybill_number);
        orderSheet.setBoxNumber(box_number1);
        orderSheet.setCharge(charg);
        orderSheet.setIsendorno(0);
        int num = orderSheetService.insertOrderSheet(orderSheet);
        if (num > 0) {
            account3 = account2 - charg;
            Account account1 = new Account();
            account1.setTelephone(telephone);
            account1.setPassword(password);
            account1.setAccount(account3);
            account1.setId(id);
            account1.setName(name);
            account1.setCreateDate(createdate);
            int n = accountService.updateaccount(account1);
            if (n > 0) {
                pickupd=Setpivkudp(account,box_number,account3,identifier,customerPhone,waybill_number,waybillType,"存件");
                pickupdService.insertPickupd(pickupd);
                String neirong="您的物件在"+identifier+"自提柜中"+box_number+"号柜子," +
                        "请及时领取";
                SDKTestSendTemplateSMS sdkTestSendTemplateSMS= new SDKTestSendTemplateSMS();
                System.out.println(customerPhone+"========================-----------------");
                sdkTestSendTemplateSMS.status(customerPhone,neirong);
                response=fansuccess(identifier,action,password,waybill_number,box_type,customerPhone,account);
                String test=account+"存件在"+identifier+"自提柜的"+box_number+"号柜子中,此订单不是泰润商城订单，运单号为"+waybill_number;
                log(test);
            } else {
                response=fan(identifier,action,password,waybill_number,box_type,customerPhone,account);
            }
        } else {
            response=fan(identifier,action,password,waybill_number,box_type,customerPhone,account);
        }
        return response;
    }
}
