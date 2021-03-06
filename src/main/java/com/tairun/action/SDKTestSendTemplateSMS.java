package com.tairun.action;

import com.cloopen.rest.sdk.CCPRestSDK;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by THINK on 2017/8/22.
 */
//发送短信的
@Component
public class SDKTestSendTemplateSMS {
  public void status(String telp,String neirong){
  /*public static void main(String[] args) {*/
      HashMap<String, Object> result = null;
      CCPRestSDK restAPI = new CCPRestSDK();
      restAPI.init("app.cloopen.com", "8883");
      // 初始化服务器地址和端口，生产环境配置成app.cloopen.com，端口是8883.
      restAPI.setAccount("8a216da85a3c0c39015a4073df2a01c4", "2b7dd134ebe744de9870ed50f8a9b044");
      // 初始化主账号名称和主账号令牌，登陆云通讯网站后，可在控制首页中看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN。
      restAPI.setAppId("8a216da85a3c0c39015a4073e10d01ca");
      // 请使用管理控制台中已创建应用的APPID。
      result = restAPI.sendTemplateSMS(telp,"164178" ,new String[]{neirong});
      //他显示的就是这个result为空了
      System.out.println("SDKTestGetSubAccounts result=" + result);
      if("000000".equals(result.get("statusCode"))){
          //正常返回输出data包体信息（map）
          HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
          Set<String> keySet = data.keySet();
          for(String key:keySet){
              Object object = data.get(key);
              System.out.println(key +" = "+object);
          }
      }else{
          //异常返回输出错误码和错误信息
          System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
      }
  }
 public static void main(String [] args){
        SDKTestSendTemplateSMS sdkTestSendTemplateSMS= new SDKTestSendTemplateSMS();
        //sdkTestSendTemplateSMS.status("13790874855");
    }
}
