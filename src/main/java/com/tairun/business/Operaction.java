package com.tairun.business;

import com.alibaba.fastjson.JSONObject;
import com.tairun.action.GoodsPickup;
import com.tairun.server.session.Session;
import com.tairun.server.utils.JsonUtil;

import java.util.Map;

/**
 * Created by THINK on 2017/7/31.
 */
public class Operaction {

    public String event(Session session, String msg){
        // 信息提取,取出json数据
        String result=null;
        if(null != msg){
            String[] json = msg.split("@");
            Map<String,Object> map = JsonUtil.convertJsonStrToMap(json[3]);
            // 取出操作类型
            String action = JSONObject.toJSONString(map.get("action"));
            //用户取货之后将信息发送到服务器
            GoodsPickup goodsPickup = new GoodsPickup();
            if("\"goods_pickup\"".equals(action)){
                // 取货逻辑
                result = goodsPickup.Pickup(msg);
            //快递员登录
            }else if("\"courier_login\"".equals(action)){
                result =goodsPickup.LoginKuai(msg);

            }
            //查询订单的联系人号码
            else if("\"goods_info\"".equals(action)){
                result = goodsPickup.WaybillNumber(msg);
                return result;
            }
            //快递员存件
            else if("\"goods_pickin\"".equals(action)){
                result = goodsPickup.Deposit(msg);
            }
            //管理员登录
            else if("\"admin_login\"".equals(action)){
                    result = goodsPickup.LoginGuan(msg);
            }
            //管理员开箱
            else if("\"admin_open_box\"".equals(action)){
                result = goodsPickup.OpenCabinet(msg);
            }
            //定时任务
            else if("\"timing_task\"".equals(action)){
                result = goodsPickup.timing(msg);
            }
            //下载更新
            else if("\"updata\"".equals(action)){
                result = goodsPickup.Downloadupdate(msg);
            }
        }
        return result;
    }
}
