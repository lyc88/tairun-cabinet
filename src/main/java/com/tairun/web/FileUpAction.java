package com.tairun.web;

import com.alibaba.fastjson.JSONObject;
import com.tairun.dao.CabinetMapper;
import com.tairun.dao.FilesMapper;
import com.tairun.model.*;
import com.tairun.serviceimpl.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lyc on 2017/8/4.
 */
@Controller

public class FileUpAction {
    @Autowired
    FileService fileService;
    @Autowired
    FilesMapper filesMapper;
    @Autowired
    CabinetMapper cabinetMapper;
    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @RequestMapping("fileUp")
    @ResponseBody
    public Map fileUp(@RequestParam("cabinetId") String cabinetId, @RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {

        HashMap hashMap = new HashMap();
        long startTime = System.currentTimeMillis();
        System.out.println("fileName：" + file.getOriginalFilename());
        String fileName = file.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("upfile");


        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);

            Files files = new Files();
            files.setImgId(Integer.parseInt(cabinetId));
            files.setFilePath(path);
            files.setFileName(fileName);

            fileService.insterFile(files);
            hashMap.put("msg", "success");
            hashMap.put("src", "" + request.getContextPath()+"/upfile/"+fileName);
            hashMap.put("imageId",files.getId());
        } catch (IOException e) {
            e.printStackTrace();
            hashMap.put("msg", "error");
            hashMap.put("src", "");
        }


        return hashMap;
    }

    /**
     * 删除图片
     * @param imageId
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteImage")
    public Map deleteImage(Integer imageId){
        HashMap hashMap = new HashMap();
        try{
           /* System.out.println(imageId);
            fileService.deleteFile(imageId);*/
            filesMapper.deleteByPrimaryKey(imageId);
            hashMap.put("msg", "success");

        }catch (Exception e){
            hashMap.put("msg", "error");
        }
        return hashMap;
    }
    /**
     * 查询自提柜里面柜子信息
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping("selectcabinet_id")//这里,然后把返回的list显示在
    public List<Cabinet> selectcabinet_id(String code){
        CabinetExample cabinetExample = new CabinetExample();
        //查询条件
        CabinetExample.Criteria criteria = cabinetExample.createCriteria();
        criteria.andCodeEqualTo(code);
        List<Cabinet> list = cabinetMapper.selectByExample(cabinetExample);
        if(null != list && list.size()>0){
            System.out.println(JSONObject.toJSONString(list));
            return list;//返回有点问题//数据是有，但是不知道怎么把他取出来
        }else{
            return null;
        }
    }
}
