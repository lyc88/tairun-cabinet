package com.tairun.web;

import com.tairun.model.Account;
import com.tairun.server.utils.EUDataGridResult;
import com.tairun.serviceimpl.AccountService;
import com.tairun.serviceimpl.OrderSheetService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 快递员信息查询
 * Created by lyc on 2017/7/26.
 */
@Controller
@RequestMapping("account")
public class AccountAction {
    @Autowired
    private AccountService accountService;

    @Autowired
    private OrderSheetService orderSheetService;

    /**
     * 快递员信息列表
     * @param pageNum
     * @param pageSize
     * @param telephone
     * @param name
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public EUDataGridResult getAccountPage(@RequestParam(value="page", defaultValue="1")int pageNum, @RequestParam(value="rows", defaultValue="10")int pageSize,String telephone,String name){

        EUDataGridResult euDataGridResult = accountService.getAccountPage(pageNum,pageSize,telephone,name);

        return  euDataGridResult;
    }

    /**
     * 支付页面
     * @return
     */
    @RequestMapping("toRecharge")
    public String recharge(){
        return "recharge";
    }
    /**
     * 选择金额页面
     * @return
     */
    @RequestMapping("toPrepaid")
    public String prepaid(){
        return "prepaid";
    }
    /**
     * 注册页面
     * @return
     */
    @RequestMapping("toRegister")
    public String register(){
        return "register";
    }
    /**
     * 快递员注册
     * @param telephone
     * @param password
     * @return
     */
    @RequestMapping("register")
    public String register(String telephone,String password,String name,Model model){
        //验证手机号是否被注册
        if(StringUtils.isNotBlank(telephone)){
            Account account = accountService.findByTelephone(telephone.trim());
            if(null != account){
                model.addAttribute("msg","手机号已经被注册了");
                return "forward:/WEB-INF/views/register.jsp";
            }
        }

        if(StringUtils.isNotBlank(telephone) && StringUtils .isNotBlank(password) && StringUtils .isNotBlank(name)) {
            Account account = new Account();
            account.setTelephone(telephone);
            account.setPassword(password);
            account.setName(name);
            accountService.save(account);
            return "forward:/WEB-INF/views/success.jsp";
        }else{
            model.addAttribute("msg","手机号或用户名或密码不能为空");
        }
        return "forward:/WEB-INF/views/register.jsp";
    }
    @RequestMapping("login")
    public String login(String username,String password,HttpServletResponse response,HttpServletRequest request,Model model){
        if(username!=null&&password!=null){
            Account account = accountService.fintoaccountpassword(username,password);
            if(null!=account) {
                HttpSession session = request.getSession();
                session.setAttribute("account", username);
                Object a = session.getAttribute("account");
                model.addAttribute("msg",username);
                return "forward:/WEB-INF/views/guanindex.jsp";
            }else{
                model.addAttribute("msg","用户名或密码不正确");
                return "forward:/WEB-INF/views/courier/logincourier.jsp";
            }
        }else{
            model.addAttribute("msg","用户名或密码不能为空");
            return "forward:/WEB-INF/views/courier/logincourier.jsp";
        }

    }
    @RequestMapping("towei")
    @ResponseBody
    public EUDataGridResult selectOrderSheet(@RequestParam(value="page", defaultValue="1")int pageNum, @RequestParam(value="rows", defaultValue="10")int pageSize,HttpServletRequest httpServletRequest){
        EUDataGridResult euDataGridResult = null;
        HttpSession session = httpServletRequest.getSession();
        Object a = session.getAttribute("account");
        String acount = (String)a;
        try {
            euDataGridResult = orderSheetService.getOrderSheetPage(pageNum,pageSize,acount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return euDataGridResult;
    }
    @RequestMapping("loginyemian")
    public String login(){
        return "forward:/WEB-INF/views/courier/logincourier.jsp";
    }

    @RequestMapping("loginwei")
    public String loginwei(){
        return "forward:/WEB-INF/views/courier/weiqu.jsp";
    }
    @RequestMapping("loginli")
    public String loginguan(){
        return "forward:/WEB-INF/views/register.jsp";
    }
    @RequestMapping("loginlu")
    public String loginguanlu(){
        return "forward:/WEB-INF/views/courier/logincourier.jsp";
    }
}
