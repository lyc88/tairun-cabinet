package com.tairun.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tairun.businessmodel.Depositb;
import com.tairun.businessmodel.Loginkuaib;
import com.tairun.businessmodel.Pickupb;
import com.tairun.model.Account;
import com.tairun.model.Log;
import com.tairun.server.utils.JsonUtil;
import com.tairun.server.utils.SpringUtil;
import com.tairun.service.LogService;
import com.tairun.serviceimpl.AccountService;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by THINK on 2017/7/31.
 * 逻辑
 */
public class GoodsPickup {
    private AccountService accountService = SpringUtil.getBean(AccountService.class);
    private LogService logService = SpringUtil.getBean(LogService.class);
    /*
    *用户取货的信息
    */
    public String Pickup(String msg){
        String[] json = msg.split("@");
        Map<String,Object> map = JsonUtil.convertJsonStrToMap(json[3]);
        // 取出数据
        String identifier = JSONObject.toJSONString(map.get("identifier"));
        String action = (String)map.get("action");
        String boxNumbe = JSONObject.toJSONString(map.get("boxNumbe"));
        String state = (String)map.get("state");
        Pickupb pickupb = new Pickupb();
        pickupb.setIdentifier(identifier);
        pickupb.setAction("快递员取件");
        pickupb.setResult(0);
        int num= JSON.toJSONString(pickupb).length();
        String response = "##@1@"+num+"@"+ JSON.toJSONString(pickupb);

        // 构造日志信息
        Log log = new Log();
        log.setCreateDate(Calendar.getInstance().getTime());
        log.setInfo(response);
        logService.insert(log);
        return response;
    }
    /*
    *快递员登录
    */
    public String LoginKuai(String msg){
        String[] json = msg.split("@");
        Map<String,Object> map = JsonUtil.convertJsonStrToMap(json[3]);
        // 取出数据
        String action = (String)map.get("action");
        String identifier = JSONObject.toJSONString(map.get("identifier"));
        String account = (String)map.get("account");
        String password = (String)map.get("password");
        double account2=0;
        List<Account> list=accountService.findByTelephoneandpassword(account,password);
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
    /*
    *根据运单号码获取对应的客户联系方式
    */
    public String WaybillNumber(String msg){
        return msg;
    }
    /*
    *快递员存件
    */
    public String Deposit(String msg){
        String[] json = msg.split("@");
        Map<String,Object> map = JsonUtil.convertJsonStrToMap(json[3]);
        String identifier = JSONObject.toJSONString(map.get("identifier"));
        String action = (String) map.get("action");
        String account = (String) map.get("account");
        String order_type = (String) map.get("order_type");
        String waybill_number = (String) map.get("waybill_number");
        String customerPhone = (String) map.get("customerPhone");
        String box_number = JSONObject.toJSONString(map.get("box_number"));
        String box_password = JSONObject.toJSONString(map.get("box_password"));
        String box_type = JSONObject.toJSONString(map.get("box_type"));
        Depositb deposit = new Depositb();
        int id=0;
        if(order_type.equals("0")){
            deposit.setResult(0);
            deposit.setIdentifier(identifier);
            deposit.setOperation_information("存件成功");
            deposit.setAction("快递员存件");
            int num= JSON.toJSONString(deposit).length();
            String response = "##@1@"+num+"@"+ JSON.toJSONString(deposit);
            // 构造日志信息
            Log log = new Log();
            log.setCreateDate(Calendar.getInstance().getTime());
            log.setInfo(response);
            logService.insert(log);
            return response;
        }else if(order_type.equals("1")){
            //根据运单号查询到账户，余额减0.5，修余额，返回开箱码到联系人手机
            List<Account> list=accountService.findByTelephonetwo(account);
            String response=null;
            double account2=0;
            if(list!=null) {
                for (Account account1 : list) {
                    account2 = account1.getAccount();
                    id=account1.getId();
                }
            }
             double account3=account2-0.5;

            int n=accountService.updateaccount(id,account3);
            if(n>0){
                deposit.setResult(0);
                deposit.setIdentifier(identifier);
                deposit.setOperation_information("存件成功");
                deposit.setAction("快递员存件");
                int num= JSON.toJSONString(deposit).length();
                response = "##@1@"+num+"@"+ JSON.toJSONString(deposit);
                // 构造日志信息
                Log log = new Log();
                log.setCreateDate(Calendar.getInstance().getTime());
                log.setInfo(response);
                logService.insert(log);
            }else{
            }
            return response;
        }else{
            return null;
        }
    }
    /*
   *管理员登录
   */
    public String LoginGuan(String msg){
        return msg;
    }
    /*
   *管理员开柜
   */
    public String OpenCabinet(String msg){
        return msg;
    }
    /*
   *定时执行的任务
   */
    public String Timing(String msg){
        return msg;
    }
    /*
   *下载更新
   */
    public String Downloadupdate(String msg){
        return msg;
    }
}
