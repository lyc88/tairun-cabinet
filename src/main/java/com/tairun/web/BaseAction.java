package com.tairun.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lyc on 2017/7/26.
 */
@Controller
@RequestMapping("/base")
public class BaseAction {
        //方法参数folder通过@PathVariable指定其值可以从@RequestMapping的{folder}获取，同理file也一样
        @RequestMapping("/goURL/{folder}/{file}")
        public String goURL(@PathVariable String folder, @PathVariable String file) {
            System.out.println("goURL.folder|file===" + folder+"/"+file);
            return "forward:/WEB-INF/"+folder+"/"+file+".jsp";
        }
}
