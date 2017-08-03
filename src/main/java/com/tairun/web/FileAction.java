package com.tairun.web;

import com.tairun.model.Files;
import com.tairun.serviceimpl.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;
/**
 * Created by cyt on 2017/7/31.
 */
@Controller
@RequestMapping("file")
public class FileAction {
    @Resource
    private FileService fileService;

    /**
     * 根据id查询图片信息
     *
     * @return
     */
    @RequestMapping("/fileUp")
    public String uploadImg(HttpServletRequest request, HttpServletResponse response) {
        return "";
    }
}
