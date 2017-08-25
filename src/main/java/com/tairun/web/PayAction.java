package com.tairun.web;

import com.tairun.model.Account;
import com.tairun.model.Prepaid;
import com.tairun.server.utils.AlipayConfig;
import com.tairun.server.utils.AlipayNotify;
import com.tairun.server.utils.AlipaySubmit;
import com.tairun.serviceimpl.AccountService;
import com.tairun.serviceimpl.PrepaidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by lyc on 2017/8/16.
 */
@Controller
@RequestMapping("alipay")
public class PayAction {
   @Autowired
    private PrepaidService prepaidService;
    @Autowired
    private AccountService accountService;
    /**
     * 支付宝支付页面
     *
     * @return
     */
    @RequestMapping("index")
    public String alipayIndex() {

        return "prepaid";
    }
    @RequestMapping("alipayapi")
    public String alipayapi(HttpServletRequest request, HttpServletResponse response, Model model) {
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = request.getParameter("WIDout_trade_no");

        //订单名称，必填
        String subject = request.getParameter("WIDsubject");

        //付款金额，必填
        String total_fee = request.getParameter("WIDtotal_fee");

        BigDecimal num=new BigDecimal(total_fee);

        //商品描述，可空
        String body = request.getParameter("WIDbody");
        HttpSession session = request.getSession();
        Object a = session.getAttribute("account");
        String name= (String)a;
        Prepaid prepaid = new Prepaid();
        prepaid.setName(name);
        prepaid.setOrderid(out_trade_no);
        prepaid.setNum(num);
        prepaid.setStatus("未支付");
        prepaidService.save(prepaid);
        //////////////////////////////////////////////////////////////////////////////////

        //把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", AlipayConfig.payment_type);
        sParaTemp.put("notify_url", AlipayConfig.notify_url);
        sParaTemp.put("return_url", AlipayConfig.return_url);
        sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
        sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
        sParaTemp.put("out_trade_no", out_trade_no);
        sParaTemp.put("subject", subject);
        sParaTemp.put("total_fee", total_fee);
        sParaTemp.put("body", body);
        for (String key : sParaTemp.keySet()) {
            System.out.println("key= "+ key + " and value================== " + sParaTemp.get(key));
        }
        //其他业务参数根据在 线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.O9yorI&treeId=62&articleId=103740&docType=1
        //如sParaTemp.put("参数名","参数值");

        //建立请求
        String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");


        model.addAttribute("pay", sHtmlText);
        return "alipayapi";
    }

    @RequestMapping("return_url")
    public String callback(HttpServletRequest request){
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        int k=0;BigDecimal num=null;String liushuihao=null;
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
                k++;
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";

                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                valueStr = valueStr;
                params.put(name, valueStr);
                if(k==12){
                    liushuihao=valueStr;
                }
                if(k==14){
                    num=new BigDecimal(valueStr);
                }
            }

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
            //商户订单号

            String out_trade_no = request.getParameter("out_trade_no");
        System.out.println(out_trade_no+"1");
            //支付宝交易号

            String trade_no = request.getParameter("trade_no");
            System.out.println(trade_no+"2");
            //交易状态
            String trade_status = request.getParameter("trade_status");
            System.out.println(trade_status+"3");
            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
            BigDecimal moyer=null;
            //计算得出通知验证结果
            boolean verify_result = AlipayNotify.verify(params);
            String kname=null;
            if(verify_result){//验证成功
                //请在这里加上商户的业务逻辑程序代码
                List<Prepaid> list = prepaidService.findByorderid(out_trade_no);
                for(Prepaid prepaid:list){
                    kname=prepaid.getName();
                    prepaid.setStatus("支付成功");
                    moyer=prepaid.getNum();
                    prepaid.setSerialnumber(liushuihao);
                    prepaidService.updateorderid(prepaid);
                }
                Double aaaa=moyer.doubleValue();
                System.out.println(kname);
                List<Account> list1=accountService.findByTelephonetwo(kname);
                for(Account account1 : list1){
                    account1.setAccount(account1.getAccount()+aaaa);
                    accountService.updateaccount(account1);
                }
                //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
                if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
                    //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //如果有做过处理，不执行商户的业务程序
                }

                //该页面可做页面美工编辑

                //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
                return "successs";
            }else{
                //该页面可做页面美工编辑
                List<Prepaid> list = prepaidService.findByorderid(out_trade_no);
                for(Prepaid prepaid:list){
                    prepaid.setStatus("支付失败");
                    prepaidService.updateorderid(prepaid);
                }
                return "false";
            }
    }

}
