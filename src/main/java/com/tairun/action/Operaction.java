package com.tairun.action;

import com.tairun.server.session.Session;
import com.tairun.server.utils.JsonUtil;

import java.util.Map;

/**
 * Created by LKH on 2017/7/31.
 */
public class Operaction {

    public String event(Session session, String msg) {
        // 信息提取,取出json数据
        String result = null;
        Map<String, Object> map = JsonUtil.convertJsonStrToMap(msg);
        // 取出操作类型
        String action = (String) map.get("action");
        //用户取货之后将信息发送到服务器
        GoodsPickup goodsPickup = new GoodsPickup();
        if ("goods_pickup".equals(action)) {
            // 取货逻辑
            result = goodsPickup.Pickup(msg);
            //快递员登录
        } else if ("courier_login".equals(action)) {
            result = goodsPickup.LoginKuai(msg);
        }
        //根据运单号码获取对应的客户联系方式
        else if ("goods_info".equals(action)) {
            result = goodsPickup.WaybillNumber(msg);
        }
        //快递员存件
        else if ("goods_pickin".equals(action)) {
            result = goodsPickup.Deposit(msg);
        }
        //管理员登录
        else if ("admin_login".equals(action)) {
            result = goodsPickup.LoginGuan(msg);
        }
        //管理员开箱
        else if ("admin_open_box".equals(action)) {
            result = goodsPickup.OpenCabinet(msg);
        }
        //定时任务
        else if ("timing_task".equals(action)) {
            result = goodsPickup.Timing(msg);
        }
        //8.恢复出厂设置
        else if ("fresh_init".equals(action)) {
            result = goodsPickup.Downloadupdate(msg);
        }
        //更新返回
        else if("remote_update_state".equals(action)){
            result = goodsPickup.updatstate(msg);
        }
        return result;
    }
}
