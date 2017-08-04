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
    public String uploadImg(MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response) {
        File targetFile;
        // 存储路径
        String msgUrl = "";
        // 是否上传成功标志
        boolean flag = false;
        // 取图片的原始名称、后缀
        String fileName = uploadFile.getOriginalFilename();
        if (fileName != null && fileName != "") {
            // 存储路径
            String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/images/uploadImgs/";
            // 文件存储位置
            String path = request.getSession().getServletContext().getRealPath("/images/uploadImgs/");
            File fileToo = new File(path + "/");
            // 如果文件夹不存在则创建
            if (!fileToo.exists() && !fileToo.isDirectory()) {
                fileToo.mkdir();
            }
            targetFile = new File(fileToo, fileName);
            try {
                uploadFile.transferTo(targetFile);
                msgUrl = returnUrl + "/" + fileName;
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (flag) {
            return msgUrl;
        }
        return null;
    }
}
