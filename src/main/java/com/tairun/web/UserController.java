package com.tairun.web;

import com.tairun.model.Cabinet;
import org.springframework.stereotype.Controller;
import com.tairun.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cyt on 2017/7/20.
 */
@Controller
public class UserController {
    @Resource
    private UserService userService;

    /**
     *
     * @param tele
     * @return
     */
    @RequestMapping("/selectAccount")
    public String selectByAccount(@RequestParam String tele) {
        List<Cabinet> list = userService.selectByAccount(tele);
        return "";
    }


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


}
