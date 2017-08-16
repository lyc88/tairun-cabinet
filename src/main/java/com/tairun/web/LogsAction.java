package com.tairun.web;

import com.tairun.server.utils.EUDataGridResult;
import com.tairun.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by THINK on 2017/8/14.
 */
@Controller
@RequestMapping("logs")
public class LogsAction {
    @Autowired
    private LogService logServiceImpl;
    @RequestMapping("list")
    @ResponseBody
    public EUDataGridResult getAccountPage(@RequestParam(value="page", defaultValue="1")int pageNum, @RequestParam(value="rows", defaultValue="10")int pageSize){

        EUDataGridResult euDataGridResult = logServiceImpl.getAccountPage(pageNum,pageSize);

        return  euDataGridResult;
    }
    @RequestMapping("deletebyid")
    @ResponseBody
    public void deleteid(Integer logId){
      int num=logServiceImpl.deletebyid(logId);
      System.out.println(num);
    }
}
