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
        String[] json = msg.split("@");
        Map<String,Object> map = JsonUtil.convertJsonStrToMap(json[3]);
        // 取出数据
        String identifier = (String)map.get("identifier");
        String action = (String)map.get("action");
        String boxNumber = JSONObject.toJSONString(map.get("boxNumber"));
        int boxNumber1= Integer.parseInt(boxNumber);
        String state = (String)map.get("state");
        Pickupb pickupb = new Pickupb();
        pickupb.setIdentifier(identifier);
        pickupb.setAction("快递员取件");
        pickupb.setResult(0);
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
                        int num= JSON.toJSONString(pickupb).length();
                        response = "##@1@"+num+"@"+ JSON.toJSONString(pickupb);
                        // 构造日志信息
                        log(response);
                    }else{
                        response="柜子清空失败";
                    }
                }

            }else{
                response="连接超时，请重发";
            }
        }else{
            response="没有该箱子";
        }

        return response;
    }
    /**
    *快递员登录
    */
    public String LoginKuai(String msg){
        String[] json = msg.split("@");
        Map<String,Object> map = JsonUtil.convertJsonStrToMap(json[3]);
        // 取出数据
        String action = (String)map.get("action");
        String identifier = (String)map.get("identifier");
        String account = (String)map.get("account");
        String password = (String)map.get("password");
        double account2=0;
        List<Account> list=  accountService.findByTelephoneandpassword(account,password);
        if(list!=null){
            for(Account account1:list){
                account2=account1.getAccount();
            }
            Loginkuaib loginkuai= new Loginkuaib();
            loginkuai.setIdentifier(identifier);
            loginkuai.setAccount(account);
            loginkuai.setBalance(account2);
            loginkuai.setAction(action);
            loginkuai.setResult(0);//0表示操作成功
            int num= JSON.toJSONString(loginkuai).length();
            String response = "##@1@"+num+"@"+ JSON.toJSONString(loginkuai);
           /* channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));*/
            return response;
        }else{
            String ms="没有该快递员账户";
            return ms;
        }
    }
    /**
    *根据运单号码获取对应的客户联系方式
    */
    public String WaybillNumber(String msg){
        String customernumber=null;
        String response=null;
        String[] json = msg.split("@");
        Map<String,Object> map = JsonUtil.convertJsonStrToMap(json[3]);
        String identifier = (String)map.get("identifier");
        String action = (String) map.get("action");
        String waybill_number = (String) map.get("waybill_number");
        List<OrderSheet> list=orderSheetService.findBywaybillnumber(waybill_number);
        if(null!=list){
            for(OrderSheet orderSheet:list){
                customernumber=orderSheet.getCustomerNumber();
            }
            WaybillNumberb waybillNumberb = new WaybillNumberb();
            waybillNumberb.setIdentifier(identifier);
            waybillNumberb.setCustomer_number(customernumber);
            waybillNumberb.setAction(action);
            waybillNumberb.setResult(0);
            waybillNumberb.setWaybill_number(waybill_number);
            int num= JSON.toJSONString(waybillNumberb).length();
            response = "##@1@"+num+"@"+ JSON.toJSONString(waybillNumberb);
        }else{
            response="没有该运单号";
        }
        return response;
    }
    /**
    *快递员存件
    */
    public String Deposit(String msg){
        String[] json = msg.split("@");
        Map<String,Object> map = JsonUtil.convertJsonStrToMap(json[3]);
        String identifier = (String)map.get("identifier");
        String action = (String) map.get("action");
        String account = (String) map.get("account");
        String order_type = (String) map.get("order_type");
        String waybill_number = (String) map.get("waybill_number");
        String customerPhone = (String) map.get("customerPhone");
        String box_number = JSONObject.toJSONString(map.get("box_number"));
        int box_number1= Integer.parseInt(box_number);
        String box_password = JSONObject.toJSONString(map.get("box_password"));
        String box_type = JSONObject.toJSONString(map.get("box_type"));
        Depositb deposit = new Depositb();
        String response;
        int id=0;
        double account3;
        String name=null;
        String telephone=null;
        String password=null;
        Date createdate=null;
        Double charg=new Double(0.5);
        if(order_type.equals("0")){//把条件改为运单号去泰润商城订单中查询时不为空，则为泰润商城订单，执行下面内容
            OrderSheet orderSheet= new OrderSheet();
            orderSheet.setCreateDate(Calendar.getInstance().getTime());
            orderSheet.setAccount(account);
            orderSheet.setBoxPassword(box_password);
            orderSheet.setCustomerNumber(customerPhone);
            orderSheet.setIdentifier(identifier);
            orderSheet.setOrderType("0");
            orderSheet.setWaybillNumber(waybill_number);
            orderSheet.setBoxNumber(box_number1);
            orderSheet.setCharge(0.0);
            int n=orderSheetService.insertOrderSheet(orderSheet);
            if(n>0){
                deposit.setResult(0);
                deposit.setIdentifier(identifier);
                deposit.setOperation_information("存件成功");
                deposit.setAction("快递员存件");
                int num= JSON.toJSONString(deposit).length();
                response = "##@1@"+num+"@"+ JSON.toJSONString(deposit);
                // 构造日志信息
                log(response);
            }else{
                response="连接超时，请重新发送请求";
            }
        }else if(order_type.equals("1")){//把条件改为运单号去泰润商城订单中查询时为空，则不为泰润商城订单，执行下面内容
            List<Account> list=accountService.findByTelephonetwo(account);
            Double account2=new Double(0.5);
            charg=0.5;
            if( list!=null) {
                for (Account account1 : list) {
                    telephone=account1.getTelephone();
                    password=account1.getPassword();
                    account2 = account1.getAccount();
                    id=account1.getId();
                    name=account1.getName();
                    createdate=account1.getCreateDate();
                }
                if(account2>charg){
                    OrderSheet orderSheet= new OrderSheet();
                    orderSheet.setCreateDate(Calendar.getInstance().getTime());
                    orderSheet.setAccount(account);
                    orderSheet.setBoxPassword(box_password);
                    orderSheet.setCustomerNumber(customerPhone);
                    orderSheet.setIdentifier(identifier);
                    orderSheet.setOrderType("1");
                    orderSheet.setWaybillNumber(waybill_number);
                    orderSheet.setBoxNumber(box_number1);
                    orderSheet.setCharge(charg);
                    int num=orderSheetService.insertOrderSheet(orderSheet);
                    if(num>0){
                        account3=account2-charg;
                        Account account1= new Account();
                        account1.setTelephone(telephone);
                        account1.setPassword(password);
                        account1.setAccount(account3);
                        account1.setId(id);
                        account1.setName(name);
                        account1.setCreateDate(createdate);
                        int n=accountService.updateaccount(account1);
                        if(n>0){
                            deposit.setResult(0);
                            deposit.setIdentifier(identifier);
                            deposit.setOperation_information("存件成功");
                            deposit.setAction("快递员存件");
                            int num1= JSON.toJSONString(deposit).length();
                            response = "##@1@"+num1+"@"+ JSON.toJSONString(deposit);
                            log(response);
                        }else{
                            response= "存件失败";
                        }
                    }else{
                        response="连接超时，请重新发送请求";
                    }
                }else{
                    response="你的余额不足，请及时充值";
                }

            }else{
                response=null;
            }
        }else{
            response=null;
        }
        return response;
    }
    /**
   *管理员登录
   */
    public String LoginGuan(String msg){
        String response=null;
        String[] json = msg.split("@");
        Map<String,Object> map = JsonUtil.convertJsonStrToMap(json[3]);
        String identifier = (String)map.get("identifier");
        String action = (String)map.get("action");
        String account = (String)map.get("account");
        String password = (String)map.get("password");
        List<User> list=userService.findByusernameandpassword(account,password);
        if(null!=list){
            LoginGuanb loginGuanb = new LoginGuanb();
            loginGuanb.setIdentifier(identifier);
            loginGuanb.setAction(action);
            loginGuanb.setAccount(account);
            loginGuanb.setResult(0);
            int num1= JSON.toJSONString(loginGuanb).length();
            response = "##@1@"+num1+"@"+ JSON.toJSONString(loginGuanb);
        }else{
            response="账户或密码错误，请重新登录";
        }
        return response;
    }
    /**
   *管理员开柜
   */
    public String OpenCabinet(String msg){
        String[] json = msg.split("@");
        Map<String,Object> map = JsonUtil.convertJsonStrToMap(json[3]);
        String identifier = (String)map.get("identifier");
        String action = (String)map.get("action");
        String account = (String)map.get("account");
        String password = (String)map.get("password");
        String box_number = (String)map.get("box_number");
        OpenCabinetb openCabinetb = new OpenCabinetb();
        openCabinetb.setIdentifier(identifier);
        openCabinetb.setAction(action);
        openCabinetb.setResult(0);
        int num1= JSON.toJSONString(openCabinetb).length();
        String response = "##@1@"+num1+"@"+ JSON.toJSONString(openCabinetb);
        String test= "自提柜号"+identifier+"管理员操作"+action+"管理员账户"+account+"自提柜对应的柜子号"+box_number;
        log(test);
        return response;
    }
    /**
   *定时执行的任务
   */
    @Value("url")
    String url;
    public String Timing(String msg){
        String response=null,NewPicUrl=null,NewVersionUrl;
        String numpicurl[];
        int getupdate=0,getimgupdate=0,imgid=0;
        String[] json = msg.split("@");
        Map<String,Object> map = JsonUtil.convertJsonStrToMap(json[3]);
        String identifier = (String)map.get("identifier");
        String action = (String)map.get("action");
        String box_temperature = (String)map.get("box_temperature");
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
                timingb.setIsNewVersion(getupdate);
                timingb.setIsNewAdPic(getimgupdate);
                timingb.setResult("success");
                int num1= JSON.toJSONString(timingb).length();
                response = "##@1@"+num1+"@"+ JSON.toJSONString(timingb);
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
                timingb.setIsNewVersion(getupdate);
                timingb.setIsNewAdPic(getimgupdate);
                timingb.setNewPicUrl(NewPicUrl);
                timingb.setNewVersionUrl(url);
                timingb.setResult("success");
                int num1= JSON.toJSONString(timingb).length();
                response = "##@1@"+num1+"@"+ JSON.toJSONString(timingb);
            }
        }

        return response;
    }
    /**
   *恢复出厂设置
   */
    public String Downloadupdate(String msg){
        String[] json = msg.split("@");
        Map<String,Object> map = JsonUtil.convertJsonStrToMap(json[3]);
        String identifier = (String)map.get("identifier");
        String action = (String)map.get("action");
        String boxTotalNum = (String)map.get("boxTotalNum");
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
                selfcabinet.setUpdateDate(Calendar.getInstance().getTime());
                int n=selfCabinetService.updateself(selfcabinet);
                if(n>0){
                    Downloadupdateb downloadupdateb= new Downloadupdateb();
                    downloadupdateb.setIdentifier(identifier);
                    downloadupdateb.setAction(action);
                    downloadupdateb.setBoxTotalNum(boxTotalNum);
                    downloadupdateb.setResult(1);
                    int num1= JSON.toJSONString(downloadupdateb).length();
                    response = "##@1@"+num1+"@"+ JSON.toJSONString(downloadupdateb);
                    String test=Calendar.getInstance().getTime()+"修改"+identifier+"柜子";
                    log(test);
                }
            }
        }
        return response;
    }
    /**
     *远程更新、下载的执行状态状态
     */
    public String remoteupdatestate(String msg){
        String[] json = msg.split("@");
        Map<String,Object> map = JsonUtil.convertJsonStrToMap(json[3]);
        String identifier = (String)map.get("identifier");
        String action = (String)map.get("action");
        String newVersionUpdata = (String)map.get("newVersionUpdata");
        String newPicUpdata = (String)map.get("newPicUpdata");
        int num=0;
        String response=null;
        if(newVersionUpdata.equals("success")&&newPicUpdata.equals("success")){
            List<Selfcabinet> list=selfCabinetService.findBycode(identifier);
            if(null!=list){
                for(Selfcabinet selfcabinet : list){
                    selfcabinet.setId(selfcabinet.getId());
                    selfcabinet.setCode(selfcabinet.getCode());
                    selfcabinet.setStatus(selfcabinet.getStatus());
                    selfcabinet.setIsUpdate(selfcabinet.getIsUpdate());
                    selfcabinet.setCreateDate(selfcabinet.getCreateDate());
                    selfcabinet.setUpdateDate(selfcabinet.getUpdateDate());
                    selfcabinet.setInfo(selfcabinet.getInfo());
                    selfcabinet.setImgUpdate(0);
                    selfcabinet.setImgId(selfcabinet.getImgId());
                    num=selfCabinetService.updateself(selfcabinet);
                    if(num>0){
                        int num1= JSON.toJSONString(selfcabinet).length();
                        response = "##@1@"+num1+"@"+ JSON.toJSONString(selfcabinet);
                        String test="收到";
                    }
                }
            }
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
}
