package com.tairun.web;

import com.tairun.model.Account;
import com.tairun.server.utils.EUDataGridResult;
import com.tairun.serviceimpl.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 快递员信息查询
 * Created by lyc on 2017/7/26.
 */
@Controller
@RequestMapping("account")
public class AccountAction {
    @Autowired
    private AccountService accountService;

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
                return "forward:/WEB-INF/jsp/register.jsp";
            }
        }

        if(StringUtils.isNotBlank(telephone) && StringUtils .isNotBlank(password) && StringUtils .isNotBlank(name)) {
            Account account = new Account();
            account.setTelephone(telephone);
            account.setPassword(password);
            account.setName(name);
            accountService.save(account);
            return "forward:/WEB-INF/jsp/success.jsp";
        }else{
            model.addAttribute("msg","手机号或用户名或密码不能为空");
        }
        return "forward:/WEB-INF/jsp/register.jsp";
    }
}
