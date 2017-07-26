package com.tairun.web;

import com.tairun.server.utils.EUDataGridResult;
import com.tairun.serviceimpl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("list")
    @ResponseBody
    public EUDataGridResult getAccountPage(@RequestParam(value="page", defaultValue="1")int pageNum, @RequestParam(value="rows", defaultValue="10")int pageSize,String telephone,String name){

        EUDataGridResult euDataGridResult = accountService.getAccountPage(pageNum,pageSize,telephone,name);

        return  euDataGridResult;
    }
}
