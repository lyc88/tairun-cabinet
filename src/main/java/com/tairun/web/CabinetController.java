package com.tairun.web;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tairun.model.Cabinet;
import com.tairun.model.CabinetExample;
import com.tairun.service.CabinetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/7/18.
 */
@Controller
public class CabinetController {
    @Resource
    private CabinetService cabinetService;

    /**
     * 查看所有柜子信息*/
    @RequestMapping("/show")
    @ResponseBody
    public String selectAll(@RequestParam Byte status) {
            String jsonList = "";
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                List<Cabinet> list = cabinetService.selectAll(status);
                jsonList = objectMapper.writeValueAsString(list);
                System.out.print(list.get(0).getBeginDate());
            } catch (Exception e) {
                e.printStackTrace();
    }
        return jsonList;
    }

    /*清空柜子*/
    @RequestMapping("/updatecab")
    public String updateCab(@RequestParam int cid) {
        int num = cabinetService.updateCabinet(cid);
        return "index";
    }

    public CabinetService getCabinetService() {
        return cabinetService;
    }

    public void setCabinetService(CabinetService cabinetService) {
        this.cabinetService = cabinetService;
    }
}
