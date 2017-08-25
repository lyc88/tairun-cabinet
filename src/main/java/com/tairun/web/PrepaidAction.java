package com.tairun.web;

import com.tairun.server.utils.EUDataGridResult;
import com.tairun.serviceimpl.PrepaidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 充值信息
 * Created by lkh on 2017/7/26.
 */
@Controller
@RequestMapping("prepaid")
public class PrepaidAction {
    @Autowired
    private PrepaidService prepaidService;
    @RequestMapping("list")
    @ResponseBody
    public EUDataGridResult getPrepaidPage(@RequestParam(value="page", defaultValue="1")int pageNum, @RequestParam(value="rows", defaultValue="10")int pageSize, String telephone, String name){

        EUDataGridResult euDataGridResult = prepaidService.getPrepaidPage(pageNum,pageSize);

        return  euDataGridResult;
    }
}
