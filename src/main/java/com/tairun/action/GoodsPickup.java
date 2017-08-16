package com.tairun.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tairun.businessmodel.*;
import com.tairun.model.*;
import com.tairun.server.utils.JsonUtil;
import com.tairun.server.utils.SpringUtil;
import com.tairun.service.LogService;
import com.tairun.serviceimpl.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileInputStream;
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
        String boxNumber = JSONObject.toJSONString(map.get("boxNumber"));
        int boxNumber1= Integer.parseInt(boxNumber);
        String state = (String)map.get("state");
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
                        Pickupb pickupb = new Pickupb();
                        pickupb.setIdentifier(identifier);
                        pickupb.setAction(action);
                        pickupb.setResult("success");
                        int num= JSON.toJSONString(pickupb).length();
                        response = "##@1@"+num+"@"+ JSON.toJSONString(pickupb);
                        // 构造日志信息
                        String test=identifier+"自提柜中的"+boxNumber+"号柜子东西取走了";
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
        List<Account> list1=  accountService.findByTelephoneandpassword(account,password);
        if(null!=list1){
            List<OrderSheet> list=orderSheetService.findBywaybillnumber(waybill_number);
            if(null!=list){
                for(OrderSheet orderSheet:list){
                    customernumber=orderSheet.getCustomerNumber();
                }
                waybillNumberb.setIdentifier(identifier);
                waybillNumberb.setCustomer_number(customernumber);
                waybillNumberb.setAction(action);
                waybillNumberb.setAccount(account);
                waybillNumberb.setPassword(password);
                waybillNumberb.setResult("success");
                waybillNumberb.setWaybill_number(waybill_number);
                int num= JSON.toJSONString(waybillNumberb).length();
                response = "##@1@"+num+"@"+ JSON.toJSONString(waybillNumberb);
            }else{
                waybillNumberb.setIdentifier(identifier);
                waybillNumberb.setAction(action);
                waybillNumberb.setResult("flase");
                int num= JSON.toJSONString(waybillNumberb).length();
                response = "##@1@"+num+"@"+ JSON.toJSONString(waybillNumberb);
            }
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
        String waybillType = JSONObject.toJSONString(map.get("waybillType"));
        String password = (String) map.get("password");
        String waybill_number = (String) map.get("waybillNumber");
        String customerPhone = (String) map.get("customerPhone");
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
        Double charg = new Double(0.5);
        if (waybillType.equals("0")) {//把条件改为运单号去泰润商城订单中查询时不为空，则为泰润商城订单，执行下面内容
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
                response=fansuccess(identifier,action,password,waybill_number,box_type,customerPhone,account);
                // 构造日志信息
                String test=account+"存件在"+identifier+"自提柜的"+box_number+"号柜子中,此订单为泰润商城订单，运单号为"+waybill_number;
                log(test);
            } else {
                response=fan(identifier,action,password,waybill_number,box_type,customerPhone,account);
                return response;
            }
        } else if (waybillType.equals("1")) {//把条件改为运单号去泰润商城订单中查询时为空，则不为泰润商城订单，执行下面内容
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
    @Value("url")
    String url;
    public String Timing(String msg){
        String response=null,NewPicUrl=null,NewVersionUrl;
        int getupdate=0,getimgupdate=0,imgid=0;
        Map<String,Object> map = JsonUtil.convertJsonStrToMap(msg);
        String identifier = (String)map.get("identifier");
        String action = (String)map.get("action");
        String temperature = JSONObject.toJSONString(map.get("temperature"));
        List<Selfcabinet> list=selfCabinetService.findBycode(identifier);
        if(null!=list){
            for(Selfcabinet selfcabinet:list){
               getupdate=selfcabinet.getIsUpdate();
                getimgupdate=selfcabinet.getImgUpdate();
                imgid=selfcabinet.getImgId();
            }
            Timingb timingb= new Timingb();
            if(getimgupdate==0){
                timingb.setIdentifier(identifier);
                timingb.setAction(action);
                timingb.setIsNewAdPic("no");
                if(getupdate==0){
                    timingb.setIsNewVersion("no");
                    timingb.setResult("success");
                    int num1= JSON.toJSONString(timingb).length();
                    response = "##@1@"+num1+"@"+ JSON.toJSONString(timingb);
                }else if(getupdate==1){
                    timingb.setIsNewVersion("yes");
                    timingb.setResult("success");
                    int num1= JSON.toJSONString(timingb).length();
                    response = "##@1@"+num1+"@"+ JSON.toJSONString(timingb);
                }else{
                    timingb.setIsNewVersion("yes");
                    timingb.setResult("false");
                    int num1= JSON.toJSONString(timingb).length();
                    response = "##@1@"+num1+"@"+ JSON.toJSONString(timingb);
                }
            }else if(getimgupdate==1){
                List<Files> list1=fileService.selectByImgid(imgid);
                for(Files files : list1){
                    NewPicUrl+=files.getFilePath()+",";
                }
                Properties prop = new Properties();
                try {
                    prop.load(new FileInputStream(new File("D:\\gitlocal\\tairun-cabinet\\src\\main\\resources\\fileurl.properties")));
                }catch (Exception e){
                    e.printStackTrace();
                }
                url=prop.getProperty("url");
                timingb.setIdentifier(identifier);
                timingb.setAction(action);
                timingb.setIsNewAdPic("yes");
                if(getupdate==0){
                    timingb.setIsNewVersion("no");
                    timingb.setNewPicUrl(NewPicUrl);
                    timingb.setNewVersionUrl(url);
                    timingb.setResult("success");
                    int num1= JSON.toJSONString(timingb).length();
                    response = "##@1@"+num1+"@"+ JSON.toJSONString(timingb);
                }else if(getupdate==1){
                    timingb.setIsNewVersion("yes");
                    timingb.setNewPicUrl(NewPicUrl);
                    timingb.setNewVersionUrl(url);
                    timingb.setResult("success");
                    int num1= JSON.toJSONString(timingb).length();
                    response = "##@1@"+num1+"@"+ JSON.toJSONString(timingb);
                }else{
                    timingb.setIsNewVersion("no");
                    timingb.setNewPicUrl(NewPicUrl);
                    timingb.setNewVersionUrl(url);
                    timingb.setResult("false");
                    int num1= JSON.toJSONString(timingb).length();
                    response = "##@1@"+num1+"@"+ JSON.toJSONString(timingb);
                }
            }
        }else{
            Timingb timingb= new Timingb();
            timingb.setResult("false");
            int num1= JSON.toJSONString(timingb).length();
            response = "##@1@"+num1+"@"+ JSON.toJSONString(timingb);
        }

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
                Date create =cabinet.getCreateDate();
                int id=cabinet.getId();
                Date enddate=null;
                Date begindate=null;
                cabinet.setId(id);
                cabinet.setStatus(new Byte("0"));
                cabinet.setCreateDate(create);
                cabinet.setContactsTelephone("");
                cabinet.setBeginDate(enddate);
                cabinet.setEndDate(begindate);
                cabinet.setContactsTelephone("");
                cabinetService.updatecabinet(cabinet);
            }
            List<Selfcabinet> list1= selfCabinetService.findBycode(identifier);
            int id=0;
            Date creates=null;
            if(null!=list1){
                for(Selfcabinet selfcabinet:list1){
                    id=selfcabinet.getId();
                    creates=selfcabinet.getCreateDate();
                }
                Selfcabinet selfcabinet = new Selfcabinet();
                selfcabinet.setId(id);
                selfcabinet.setCode(identifier);
                selfcabinet.setCreateDate(creates);
                selfcabinet.setImgUpdate(0);
                selfcabinet.setIsUpdate(new Byte("0"));
                selfcabinet.setStatus(new Byte("0"));
                selfcabinet.setImgId(ident);
                selfcabinet.setUpdateDate(Calendar.getInstance().getTime());
                int n=selfCabinetService.updateself(selfcabinet);
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
            for(int i=0;i<boxTotalNum;i++){
                Cabinet cabinet = new Cabinet();
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
    //充值业务
    public String couriercharge(String msg){
        Map<String,Object> map = JsonUtil.convertJsonStrToMap(msg);
        String account = (String)map.get("account");
        String telephone = (String)map.get("telephone");
        Double money=Double.parseDouble(account);
        String action = (String)map.get("action");
        String password = (String)map.get("password");
        String response=null;
        CourierChargeb courierChargeb = new CourierChargeb();
        List<Account> list=accountService.findByTelephoneandpassword(telephone,password);
        if(null!=list){
            Double moneyold= list.get(0).getAccount();
            Double moneynew=moneyold+money;
            Account account1= new Account();
            account1.setId(list.get(0).getId());
            account1.setName(list.get(0).getName());
            account1.setAccount(moneynew);
            account1.setPassword(list.get(0).getPassword());
            account1.setTelephone(list.get(0).getTelephone());
            account1.setCreateDate(list.get(0).getCreateDate());
            int num=accountService.updateaccount(account1);
            if(num>0){
                courierChargeb.setTelephone(telephone);
                courierChargeb.setResult("success");
                courierChargeb.setMoney(moneynew);
                int num1= JSON.toJSONString(courierChargeb).length();
                response = "##@1@"+num1+"@"+ JSON.toJSONString(courierChargeb);
            }else{
                courierChargeb.setTelephone(telephone);
                courierChargeb.setResult("false");
                courierChargeb.setMoney(moneynew);
                int num1= JSON.toJSONString(courierChargeb).length();
                response = "##@1@"+num1+"@"+ JSON.toJSONString(courierChargeb);
            }
        }else{
            courierChargeb.setResult("false");
            int num1= JSON.toJSONString(courierChargeb).length();
            response = "##@1@"+num1+"@"+ JSON.toJSONString(courierChargeb);
        }

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
}