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

    @RequestMapping("index")
    public String index(){

        return "forward:/WEB-INF/jsp/list.jsp";
    }

    @RequestMapping("json")
    @ResponseBody
    public String json(){
        String string = "[\n" +
                "{\"productid\":\"FI-SW-01\",\"productname\":\"Koi\"},\n" +
                "{\"productid\":\"K9-DL-01\",\"productname\":\"Dalmation\"},\n" +
                "{\"productid\":\"RP-SN-01\",\"productname\":\"Rattlesnake\"},\n" +
                "{\"productid\":\"RP-LI-02\",\"productname\":\"Iguana\"},\n" +
                "{\"productid\":\"FL-DSH-01\",\"productname\":\"Manx\"},\n" +
                "{\"productid\":\"FL-DLH-02\",\"productname\":\"Persian\"},\n" +
                "{\"productid\":\"AV-CB-01\",\"productname\":\"Amazon Parrot\"}\n" +
                "]";
        return string;
    }
}
