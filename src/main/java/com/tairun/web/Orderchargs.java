package com.tairun.web;

import com.tairun.serviceimpl.OrderSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lkh on 2017/8/14.
 */
@Controller
@RequestMapping("orderchargs")
public class Orderchargs {
    @Autowired
    private OrderSheetService orderSheetService;

    @ResponseBody
    @RequestMapping("toNumber")
    public List getOrderSheetService(String tel) {
        List list=orderSheetService.findBywaybillnumber(tel);
//        model.addAttribute("list",list);
//        return "forward:/WEB-INF/views/boxCodeInquire.jsp";
        return list;
    }
}
