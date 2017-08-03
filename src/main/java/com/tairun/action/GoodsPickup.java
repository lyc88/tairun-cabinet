package com.tairun.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tairun.business.State;
import com.tairun.model.Account;
import com.tairun.model.Log;
import com.tairun.server.utils.JsonUtil;
import com.tairun.server.utils.SpringUtil;
import com.tairun.service.LogService;
import com.tairun.serviceimpl.AccountService;

import java.util.Calendar;
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
        String identifier = JSONObject.toJSONString(map.get("identifier"));
        String action = JSONObject.toJSONString(map.get("action"));
        String boxNumbe = JSONObject.toJSONString(map.get("boxNumbe"));
        String state = JSONObject.toJSONString(map.get("state"));
        String response=fuzhi();
        return response;
    }
    /*
    *快递员登录
    */
    public String LoginKuai(String msg){
        String[] json = msg.split("@");
        Map<String,Object> map = JsonUtil.convertJsonStrToMap(json[3]);
        // 取出数据
        String identifier = JSONObject.toJSONString(map.get("identifier"));
        String action = JSONObject.toJSONString(map.get("action"));
        //String account = JSONObject.toJSONString(map.get("account"));
        String account = (String)map.get("account");
        String password = JSONObject.toJSONString(map.get("password"));
        //AccountService accountService = new AccountService();
        /*Account account1=accountService.findByTelephoneandpassword(account,password);
        if(account1!=null){
            String response=fuzhi();
           *//* channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));*//*
            return response;
        }else{
            String ms="找不到该快递员";
            return ms;
        }*/
       return "";

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
        String action = JSONObject.toJSONString(map.get("action"));
        String account = JSONObject.toJSONString(map.get("account"));
        String password = JSONObject.toJSONString(map.get("password"));
        String waybill_number = JSONObject.toJSONString(map.get("waybill_number"));
        String customerPhone = JSONObject.toJSONString(map.get("customerPhone"));
        String box_number = JSONObject.toJSONString(map.get("box_number"));
        String box_password = JSONObject.toJSONString(map.get("box_password"));
        String box_type = JSONObject.toJSONString(map.get("box_type"));
        String response=fuzhi();

        // 构造日志信息
        Log log = new Log();
        log.setCreateDate(Calendar.getInstance().getTime());
        log.setInfo(response);
        logService.insert(log);

        return response;
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
    public String timing(String msg){
        return msg;
    }
    /*
   *下载更新
   */
    public String Downloadupdate(String msg){
        return msg;
    }
    //赋值方法
    public String fuzhi(){
        State state = new State();
        state.setBoxNumber(1);
        state.setState("normal");
        state.setCustomerPhone("15278199288");
        state.setAction("courier_login");
        state.setBalance(13213);
        state.setIdentifier("123456");
        state.setResult("false");
        String response = "##@1@140@"+ JSON.toJSONString(state);
        return response;
    }

}
