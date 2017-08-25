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
    @Scheduled(cron = "0/5 * * * * ?") // 间隔5秒执行
    public void taskCycle() {
        System.out.println("使用SpringMVC框架配置定时任务");
        List<OrderSheet> list=orderSheetService.getOrderSheetPage1();
        for(OrderSheet orderSheet:list){
            if(orderSheet.getIsendorno()==0){
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
                        SDKTestSendTemplateSMS sdkTestSendTemplateSMS= new SDKTestSendTemplateSMS();
                        String neirong="您在"+orderSheet.getIdentifier()+"自提柜中"+orderSheet.getBoxNumber()+"号柜子" +
                                "的物件已经超过一天未领取，请及时领取";
                        sdkTestSendTemplateSMS.status(orderSheet.getCustomerNumber(),neirong);
                        orderSheet.setIsendorno(1);
                        orderSheetService.updateorder(orderSheet);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String [] args){
        SDKTestSendTemplateSMS sdkTestSendTemplateSMS= new SDKTestSendTemplateSMS();
        //sdkTestSendTemplateSMS.status("13790874855");
    }
}
