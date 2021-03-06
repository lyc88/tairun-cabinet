package com.tairun.web;
import com.tairun.model.Cabinet;
import com.tairun.server.utils.EUDataGridResult;
import com.tairun.serviceimpl.CabinetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
/**
 * Created by cyt on 2017/7/18.
 */
    @Controller
    @RequestMapping("cabinet")
    public class CabinetAction {
    @Resource
    private CabinetService cabinetService;

    /**
     * 查看所有柜子信息*/
    @RequestMapping("/showListAll")
    @ResponseBody
    public EUDataGridResult selectAll(@RequestParam(value = "page", defaultValue = "1") int pageNum, @RequestParam(value = "rows", defaultValue = "5") int pageSize,@RequestParam int status,@RequestParam int code) {
        EUDataGridResult euDataGridResult = null;
        try {
            euDataGridResult = cabinetService.selectAll(pageNum, pageSize,1,code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return euDataGridResult;
    }


    @RequestMapping("/showListByStatus")
    @ResponseBody
    public EUDataGridResult selectByStatus(@RequestParam(value = "page", defaultValue = "1") int pageNum, @RequestParam(value = "rows", defaultValue = "5") int pageSize,@RequestParam Byte status) {
        EUDataGridResult euDataGridResult = null;
        try {
            euDataGridResult = cabinetService.selectByStatus(pageNum, pageSize,status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return euDataGridResult;
    }




    /*清空柜子*/
    @RequestMapping("/updateCab")
    public String updateCab(@RequestParam int cid,@RequestParam Byte status,@RequestParam Cabinet cabinet) {
        int num = cabinetService.updatecabinet(cabinet);
        return "index";
    }


}
