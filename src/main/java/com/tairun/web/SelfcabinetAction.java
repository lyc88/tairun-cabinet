package com.tairun.web;

import com.tairun.server.utils.EUDataGridResult;
import com.tairun.serviceimpl.SelfCabinetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by THINK on 2017/7/27.
 */
@Controller
@RequestMapping("selfCabinet")
public class SelfcabinetAction {
    @Resource
    private SelfCabinetService selfCabinetService;
    /**
     * 查看所有柜子信息*/
    @RequestMapping("/showSelfcabinetList")
    @ResponseBody
    public EUDataGridResult selectSelecabinet(@RequestParam(value="page", defaultValue="1")int pageNum, @RequestParam(value="rows", defaultValue="5")int pageSize){
        EUDataGridResult euDataGridResult = null;
        try {
            euDataGridResult = selfCabinetService.selectSelfcabinetAll(pageNum,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return euDataGridResult;
    }
}
