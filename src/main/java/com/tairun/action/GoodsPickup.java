package com.tairun.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tairun.businessmodel.*;
import com.tairun.model.*;
import com.tairun.server.utils.Httpget;
import com.tairun.server.utils.JsonUtil;
import com.tairun.server.utils.SpringUtil;
import com.tairun.service.LogService;
import com.tairun.serviceimpl.*;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by LKH on 2017/7/31.
 * 逻辑
 */
public class GoodsPickup {
    private AccountService accountService = SpringUtil.getBean(AccountService.class);
    private LogService logService = SpringUtil.getBean(LogService.class);
    private OrderSheetService orderSheetService= SpringUtil.getBean(OrderSheetService.class);
    private UserService userService=SpringUtil.getBean(UserService.class);
    private CabinetService cabinetService=SpringUtil.getBean(CabinetService.class);
    private SelfCabinetService selfCabinetService=SpringUtil.getBean(SelfCabinetService.class);
    private FileService fileService = SpringUtil.getBean(FileService.class);
    private PickupdService pickupdService = SpringUtil.getBean(PickupdService.class);
    /**
    *用户取货的信息
    */
    public String Pickup(String msg){
        int orderSheetid=0;
        byte number=0;
        int cabinedid=0;
        String response=null;
        Map<String,Object> map = JsonUtil.convertJsonStrToMap(msg);
        // 取出数据
        String identifier = (String)map.get("identifier");
        String action = (String)map.get("action");
        String courierAccount = (String)map.get("courierAccount");
        String boxNumber = JSONObject.toJSONString(map.get("boxNumber"));
        int boxNumber1= Integer.parseInt(boxNumber);
        String state = (String)map.get("state");
        Pickupd pickupd = new Pickupd();
        List<OrderSheet> list=orderSheetService.findByBoxIdent(identifier,boxNumber1);
        if(null!=list){
            for(OrderSheet orderSheet:list){
                orderSheetid=orderSheet.getId();
            }
            int n=orderSheetService.deleteOrderSheet(orderSheetid);
            if(n>0){
                List<Cabinet> list1=cabinetService.findBycodeandnumber(identifier,boxNumber1);
                if(null!=list1){
                    for(Cabinet cabinet:list1){
                        cabinedid=cabinet.getId();
                    }
                    Cabinet cabinet = new Cabinet();
                    cabinet.setStatus(number);
                    cabinet.setCode(identifier);
                    cabinet.setNumber(boxNumber1);
                    cabinet.setId(cabinedid);
                    int m=cabinetService.updatecabinet(cabinet);
                    if(m>0){
                        pickupd=Setqupivkudp(courierAccount,identifier,boxNumber);
                        pickupdService.insertPickupd(pickupd);
                        Pickupb pickupb = new Pickupb();
                        pickupb.setIdentifier(identifier);
                        pickupb.setAction(action);
                        pickupb.setResult("success");
                        int num= JSON.toJSONString(pickupb).length();
                        response = "##@1@"+num+"@"+ JSON.toJSONString(pickupb);
                        // 构造日志信息
                        String test=identifier+"自提柜中的"+boxNumber+"号柜子东西被"+courierAccount+"取走了";
                        log(test);
                    }else{
                        response=qufan(identifier,action);
                    }
                }
            }else{
                response=qufan(identifier,action);
            }
        }else{
            response=qufan(identifier,action);
        }

        return response;
    }
    /**
    *快递员登录
    */
    public String LoginKuai(String msg){

        Map<String,Object> map = JsonUtil.convertJsonStrToMap(msg);
        // 取出数据
        String action = (String)map.get("action");
        String identifier = (String)map.get("identifier");
        String account = (String)map.get("account");
        String password = (String)map.get("password");
        double account2=0;
        List<Account> list=  accountService.findByTelephoneandpassword(account,password);
        if(list!=null){
            account2 = list.get(0).getAccount();
            Loginkuaib loginkuai= new Loginkuaib();
            loginkuai.setIdentifier(identifier);
            loginkuai.setAccount(account);
            loginkuai.setBalance(account2);
            loginkuai.setAction(action);
            loginkuai.setResult("success");//0表示操作成功
            int num= JSON.toJSONString(loginkuai).getBytes().length;
            String response="##@1@"+num+"@"+ JSON.toJSONString(loginkuai);
            return response;
        }else{
            Loginkuaib loginkuai= new Loginkuaib();
            loginkuai.setIdentifier(identifier);
            loginkuai.setAccount(account);;
            loginkuai.setAction(action);
            loginkuai.setResult("false");
            int num= JSON.toJSONString(loginkuai).getBytes().length;
            String response="##@1@"+num+"@"+ JSON.toJSONString(loginkuai);
            return response;
        }
    }
    /**
    *根据运单号码获取对应的客户联系方式
    */
    public String WaybillNumber(String msg){
        String customernumber=null;
        String response=null;

        Map<String,Object> map = JsonUtil.convertJsonStrToMap(msg);
        String identifier = (String)map.get("identifier");
        String action = (String) map.get("action");
        String waybill_number = (String) map.get("waybillNumber");
        String account = (String) map.get("account");
        String password = (String) map.get("password");
        WaybillNumberb waybillNumberb = new WaybillNumberb();

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("name", "sarin");
       String result = Httpget.sendGet("http://192.168.6.4:8080/legendshop/themeModule/selectBySubnumber/"+waybill_number, parameters);

        List<Account> list1=  accountService.findByTelephoneandpassword(account,password);
        if(null!=list1){
                waybillNumberb.setIdentifier(identifier);
                waybillNumberb.setCustomer_number(result);
                waybillNumberb.setAction(action);
                waybillNumberb.setAccount(account);
                waybillNumberb.setPassword(password);
                waybillNumberb.setResult("success");
                waybillNumberb.setWaybill_number(waybill_number);
                int num= JSON.toJSONString(waybillNumberb).length();
                response = "##@1@"+num+"@"+ JSON.toJSONString(waybillNumberb);
        }else{
            waybillNumberb.setResult("flase");
            int num= JSON.toJSONString(waybillNumberb).length();
            response = "##@1@"+num+"@"+ JSON.toJSONString(waybillNumberb);
        }

        return response;
    }
    /**
    *快递员存件
    */
    public String Deposit(String msg) {
        Map<String, Object> map = JsonUtil.convertJsonStrToMap(msg);
        String identifier = (String) map.get("identifier");
        String action = (String) map.get("action");
        String account = (String) map.get("account");
        String waybillType = (String) map.get("waybillType");
        String password = (String) map.get("password");
        String waybill_number = (String) map.get("waybillNumber");
        String customerPhone = (String) map.get("customerNumber");
        String box_number = JSONObject.toJSONString(map.get("boxNumber"));
        int box_number1 = Integer.parseInt(box_number);
        String box_password = JSONObject.toJSONString(map.get("boxNassword"));
        String box_type = JSONObject.toJSONString(map.get("boxType"));
        Depositb deposit = new Depositb();
        String response=null;
        int id = 0;
        double account3;
        String name = null;
        String telephone = null;
        Date createdate = null;
        Pickupd pickupd = new Pickupd();
        Double charg = new Double(0.5);
        if (waybillType.equals("tairun")) {//把条件改为运单号去泰润商城订单中查询时不为空，则为泰润商城订单，执行下面内容
            OrderSheet orderSheet = new OrderSheet();
            orderSheet.setCreateDate(Calendar.getInstance().getTime());
            orderSheet.setAccount(account);
            orderSheet.setBoxPassword(box_password);
            orderSheet.setCustomerNumber(customerPhone);
            orderSheet.setIdentifier(identifier);
            orderSheet.setOrderType("0");
            orderSheet.setWaybillNumber(waybill_number);
            orderSheet.setBoxNumber(box_number1);
            orderSheet.setCustomerNumber(customerPhone);
            orderSheet.setCharge(0.0);
            int n = orderSheetService.insertOrderSheet(orderSheet);
            if (n > 0) {
                pickupd=Setpivkudp(account,box_number,0.0,identifier,customerPhone,waybill_number,waybillType,"存件");
                pickupdService.insertPickupd(pickupd);
                response=fansuccess(identifier,action,password,waybill_number,box_type,customerPhone,account);
                // 构造日志信息
                String test=account+"存件在"+identifier+"自提柜的"+box_number+"号柜子中,此订单为泰润商城订单，运单号为"+waybill_number;
                log(test);
            } else {
                response=fan(identifier,action,password,waybill_number,box_type,customerPhone,account);
                return response;
            }
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
                if (account2 > charg) {
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
                            response=fansuccess(identifier,action,password,waybill_number,box_type,customerPhone,account);
                            String test=account+"存件在"+identifier+"自提柜的"+box_number+"号柜子中,此订单不是泰润商城订单，运单号为"+waybill_number;
                            log(test);
                        } else {
                            response=fan(identifier,action,password,waybill_number,box_type,customerPhone,account);
                        }
                    } else {
                        response=fan(identifier,action,password,waybill_number,box_type,customerPhone,account);
                    }
                } else {
                    response=fan(identifier,action,password,waybill_number,box_type,customerPhone,account);
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
   *管理员登录
   */
    public String LoginGuan(String msg){
        String response=null;
        Map<String,Object> map = JsonUtil.convertJsonStrToMap(msg);
        String identifier = (String)map.get("identifier");
        String action = (String)map.get("action");
        String account = (String)map.get("account");
        String password = (String)map.get("password");
        List<User> list = userService.findByusernameandpassword(account,password);
        if(null!=list){
            LoginGuanb loginGuanb = new LoginGuanb();
            loginGuanb.setIdentifier(identifier);
            loginGuanb.setAction(action);
            loginGuanb.setAccount(account);
            loginGuanb.setResult("success");
            int num1= JSON.toJSONString(loginGuanb).getBytes().length;
            response = "##@1@"+num1+"@"+ JSON.toJSONString(loginGuanb);
        }else{
            LoginGuanb loginGuanb = new LoginGuanb();
            loginGuanb.setIdentifier(identifier);
            loginGuanb.setAction(action);
            loginGuanb.setAccount(account);
            loginGuanb.setResult("false");
            int num1= JSON.toJSONString(loginGuanb).getBytes().length;
            response = "##@1@"+num1+"@"+ JSON.toJSONString(loginGuanb);
        }
        return response;
    }
    /**
   *管理员开柜
   */
    public String OpenCabinet(String msg){
        Map<String,Object> map = JsonUtil.convertJsonStrToMap(msg);
        String identifier = (String)map.get("identifier");
        String action = (String)map.get("action");
        String account = (String)map.get("account");
        String password = (String)map.get("password");
        String box_number = (String)map.get("box_number");
        String response=null;
        List<User> list=userService.findByusernameandpassword(account,password);
        if(null!=list){
            OpenCabinetb openCabinetb = new OpenCabinetb();
            openCabinetb.setIdentifier(identifier);
            openCabinetb.setAction(action);
            openCabinetb.setResult("successs");
            int num1= JSON.toJSONString(openCabinetb).length();
            response = "##@1@"+num1+"@"+ JSON.toJSONString(openCabinetb);
            String test= "自提柜号"+identifier+"管理员操作"+action+"管理员账户"+account+"自提柜对应的柜子号"+box_number;
            log(test);
        }else{
            OpenCabinetb openCabinetb = new OpenCabinetb();
            openCabinetb.setIdentifier(identifier);
            openCabinetb.setAction(action);
            openCabinetb.setResult("flase");
            int num1= JSON.toJSONString(openCabinetb).length();
            response = "##@1@"+num1+"@"+ JSON.toJSONString(openCabinetb);
        }

        return response;
    }
    /**
   *定时执行的任务
   */
    public String Timing(String msg){
        Map<String,Object> map = JsonUtil.convertJsonStrToMap(msg);
        String identifier = (String)map.get("identifier");
        String action = (String)map.get("action");
        String temperature = JSONObject.toJSONString(map.get("temperature"));
        Timing timing = new Timing();
        String response=timing.timing(identifier,action,temperature);
        return response;
    }
    /**
   *恢复出厂设置
   */
    public String Downloadupdate(String msg){
        Map<String,Object> map = JsonUtil.convertJsonStrToMap(msg);
        String identifier = (String)map.get("identifier");
        int ident=Integer.parseInt(identifier);
        String action = (String)map.get("action");
        String boxTotalNum1 = JSONObject.toJSONString(map.get("boxTotalNum"));
        int boxTotalNum = Integer.parseInt(boxTotalNum1);
        List<Cabinet> list=cabinetService.findBycode(identifier);
        String response=null;
        if(null!=list){
            for(Cabinet cabinet : list){
                cabinet.setStatus(new Byte("0"));
                cabinetService.updatecabinet(cabinet);
            }
            List<Selfcabinet> list1= selfCabinetService.findBycode(identifier);
            int n=0;
            Date creates=null;
            if(null!=list1){
                for(Selfcabinet selfcabinet:list1){
                    selfcabinet.setIsUpdate(new Byte("0"));
                    selfcabinet.setStatus(new Byte("0"));
                    selfcabinet.setImgUpdate(0);
                    selfcabinet.setUpdateDate(Calendar.getInstance().getTime());
                    selfcabinet.setUpdateisorno("正常");
                    n=selfCabinetService.updateself(selfcabinet);
                }
                if(n>0){
                    response = Downfan(identifier,action);
                    String test=Calendar.getInstance().getTime()+"修改"+identifier+"柜子";
                    log(test);
                }
                else{
                    response=Downshifan(identifier,action);
                }
            }
        }else{
            int num=0;
            Cabinet cabinet = new Cabinet();
            for(int i=0;i<boxTotalNum;i++){
                cabinet.setCode(identifier);
                cabinet.setNumber(i+1);
                cabinet.setCreateDate(Calendar.getInstance().getTime());
                cabinet.setStatus(new Byte("0"));
                num=cabinetService.insertcabinet(cabinet);
            }
            if(num>0){
                response = Downfan(identifier,action);
                String test=Calendar.getInstance().getTime()+"修改"+identifier+"柜子";
                log(test);
            }else{
                response=Downshifan(identifier,action);
            }
        }
        return response;
    }

    /**
     *更新返回
     */
    public String updatstate(String msg){
        Map<String,Object> map = JsonUtil.convertJsonStrToMap(msg);
        String identifier = (String)map.get("identifier");
        String action = (String)map.get("action");
        String newVersionUpdata = (String)map.get("newVersionUpdata");
        String newPicUpdata = (String)map.get("newPicUpdata");
        RemoteupdatRestate remoteupdatestate= new RemoteupdatRestate();
        remoteupdatestate.update(identifier,newVersionUpdata,newPicUpdata);
        return "";
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
     * 取件失败返回
     */
    public String  qufan(String identifier,String action){
        Pickupb pickupb = new Pickupb();
        pickupb.setIdentifier(identifier);
        pickupb.setAction(action);
        pickupb.setResult("false");
        int num1 = JSON.toJSONString(pickupb).length();
        String response = "##@1@" + num1 + "@" + JSON.toJSONString(pickupb);
        return response;
    }
    /**
     * 恢复出厂成功后返回
     */
    public String  Downfan(String identifier,String action){
        Downloadupdateb downloadupdateb= new Downloadupdateb();
        downloadupdateb.setIdentifier(identifier);
        downloadupdateb.setAction(action);
        downloadupdateb.setResult("success");
        int num1= JSON.toJSONString(downloadupdateb).length();
        String response = "##@1@"+num1+"@"+ JSON.toJSONString(downloadupdateb);
        return response;
    }
    /**
     * 恢复出厂失败后返回
     */
    public String  Downshifan(String identifier,String action){
        Downloadupdateb downloadupdateb= new Downloadupdateb();
        downloadupdateb.setIdentifier(identifier);
        downloadupdateb.setAction(action);
        downloadupdateb.setResult("false");
        int num1= JSON.toJSONString(downloadupdateb).length();
        String response = "##@1@"+num1+"@"+ JSON.toJSONString(downloadupdateb);
        return response;
    }
    /**
     * 快递员存件记录
     */
    public Pickupd Setpivkudp(String account,String boxnumber,Double charge,String code,
                              String customernumber,String waybillnumbrer,String ordertype,String status){
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
     * 快递员取件记录
     */
    public Pickupd Setqupivkudp(String account,String code,String ordertype){
        Pickupd pickupd= new Pickupd();
        pickupd.setAcount(account);
        pickupd.setCreatedate(Calendar.getInstance().getTime());
        pickupd.setOrdertype(ordertype);
        pickupd.setCode(code);
        pickupd.setStatus("取件");
        return pickupd;
    }
    /**
     * MD5加密校验
     */
    public String MD5jy(String fileurl){
        String Md=null;
        try {
            Md=(DigestUtils.md5Hex(new FileInputStream(new File(fileurl))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Md;
    }

}