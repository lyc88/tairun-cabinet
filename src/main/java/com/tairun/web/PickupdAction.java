package com.tairun.web;

import com.tairun.server.utils.EUDataGridResult;
import com.tairun.serviceimpl.PickupdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 快递员信息查询
 * Created by lyc on 2017/7/26.
 */
@Controller
@RequestMapping("pickupd")
public class PickupdAction {
    @Autowired
    private PickupdService pickupdService;
    /**
     * 快递员取件信息列表
     * @param pageNum
     * @param pageSize
     * @param acount
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public EUDataGridResult getPickupdPage(@RequestParam(value="page", defaultValue="1")int pageNum, @RequestParam(value="rows", defaultValue="10")int pageSize,String acount,HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Object a = session.getAttribute("account");
        acount = (String)a;
        EUDataGridResult euDataGridResult = pickupdService.getPickupdPage(pageNum,pageSize,acount);
        return  euDataGridResult;
    }
    @RequestMapping("kuai")
    public String aaa(){
        return "forward:/WEB-INF/views/courier/pickup.jsp";
    }
    /**
     * 快递员存件信息列表
     * @param pageNum
     * @param pageSize
     * @param acount
     * @return
     */
    @RequestMapping("pickupdown")
    @ResponseBody
    public EUDataGridResult getPickupdownPage(@RequestParam(value="page", defaultValue="1")int pageNum, @RequestParam(value="rows", defaultValue="10")int pageSize,String acount,HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Object a = session.getAttribute("account");
        acount = (String)a;
        EUDataGridResult euDataGridResult = pickupdService.getPickupdownPage(pageNum,pageSize,acount);
        return  euDataGridResult;
    }
    @RequestMapping("qu")
    public String bbb(){
        return "forward:/WEB-INF/views/courier/pickupdown.jsp";
    }
}
