package com.tairun.web;

import com.tairun.model.User;
import com.tairun.serviceimpl.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by lyc on 2017/7/27.
 */
@Controller
@RequestMapping("admin")
public class AdminAction {

    @Autowired
    private UserService userService;
    @RequestMapping("login")
    public String login(HttpSession httpSession,String username, String password, Model model){
        if(StringUtils.isNotBlank(username) && StringUtils .isNotBlank(password)){
            User user = userService.login(username,password);
            //跳转到后台管理页面
            if(null != user){
                httpSession.setAttribute("user",user);
                return "forward:/WEB-INF/jsp/index.jsp";
            }else{
                model.addAttribute("msg","用户名或密码错误");
            }
        }else{
            model.addAttribute("msg","用户名或密码不能为空");
        }
        return "forward:/login.jsp";
    }
}
