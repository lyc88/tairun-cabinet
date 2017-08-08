package com.tairun.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lyc on 2017/8/4.
 */
@Controller

public class FileUpAction {

    /**
     * 文件上传
     * @param file
     * @return
     */
    @RequestMapping("fileUp")
    @ResponseBody
    public Map fileUp(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request){
        HashMap hashMap = new HashMap();
        long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+file.getOriginalFilename());
        String fileName = file.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("upfile");

        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
            hashMap.put("msg","success");
            hashMap.put("src",""+targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            hashMap.put("msg","error");
            hashMap.put("src","");
        }

       return hashMap;
    }
}
