package com.tairun.action;

import com.tairun.model.OrderSheet;
import com.tairun.serviceimpl.OrderSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by THINK on 2017/8/22.
 */
@Component
public class FlightTrainTask {
    @Autowired
    private OrderSheetService orderSheetService;
    @Scheduled(cron = "0/2 * * * * ? ") // 间隔5秒执行
    public void taskCycle() {
        System.out.println("使用SpringMVC框架配置定时任务");
        List<OrderSheet> list=orderSheetService.getOrderSheetPage1();
        for(OrderSheet orderSheet:list){
            System.out.println(orderSheet.getCreateDate());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sDate=sdf.format(orderSheet.getCreateDate());
            String sDate1=sdf.format(Calendar.getInstance().getTime());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                //customer_number
                Date d1 = df.parse(sDate1);
                Date d2 = df.parse(sDate);
                long diff = d1.getTime() - d2.getTime();
                if(diff>86400000){
                    System.out.println("快发送信息了");
                }
            }
            catch (Exception e) {

            }
        }
    }
}
