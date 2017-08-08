package com.tairun.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lyc on 2017/7/19.
 */
@Controller
public class IndexAction {

    /**
     * 登入页面
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "login";
    }

}
