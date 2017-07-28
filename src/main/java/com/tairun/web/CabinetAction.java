package com.tairun.web;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.tairun.model.Cabinet;
import com.tairun.server.utils.EUDataGridResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import com.tairun.serviceimpl.CabinetService;
/**
 * Created by cyt on 2017/7/18.
 */
@Controller
    @RequestMapping("cabinet")
    public class CabinetAction extends BaseAction {
        @Resource
    private CabinetService cabinetService;

    /**
     * 查看所有柜子信息*/
    @RequestMapping("/showListAll")
    @ResponseBody
    public EUDataGridResult selectAll(@RequestParam(value = "page", defaultValue = "1") int pageNum, @RequestParam(value = "rows", defaultValue = "5") int pageSize, @RequestParam Byte status, @RequestParam int id) {
        EUDataGridResult euDataGridResult = null;
        try {
            euDataGridResult = cabinetService.selectAll(pageNum, pageSize, status, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return euDataGridResult;
    }

    /*清空柜子*/
    @RequestMapping("/updateCab")
    public String updateCab(@RequestParam int cid,@RequestParam Byte status,@RequestParam Cabinet cabinet) {
        int num = cabinetService.updateCabinet(cabinet);
        return "index";
    }


}
