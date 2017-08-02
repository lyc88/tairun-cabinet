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
    public Object pCPhotoUpload(String type, String needsThumb, HttpServletRequest request)throws Exception{
        try {
            String  dir= "ZYCXIMG";
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            StringBuffer pictureUrls =new StringBuffer();
            List<String> imagePath = new ArrayList<>();
            if (multipartResolver.isMultipart(request)) {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                Iterator<String> iter = multiRequest.getFileNames();
                if(iter.hasNext()){
                    while (iter.hasNext()) {
                        MultipartFile file = multiRequest.getFile((String) iter.next());
                        //文件名称
                        String fileName = file.getOriginalFilename();
                        if (!fileName.equals("")) {
									/*String path1 = Thread.currentThread()
											.getContextClassLoader().getResource("").getPath() + "dowload" + File.separator;*/
                            //获得文件后缀名称
                            String prefix =fileName.substring(fileName.lastIndexOf(".")+1);
                            //图片文件放的位置文件夹目录
                            String path = "";
                            if(prefix.equalsIgnoreCase("png") || prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("bmp")){
                                // 项目在容器中实际发布运行的根路径
                                String realPath =  request.getSession().getServletContext().getRealPath("/");
                                String path2 = realPath.substring(0,realPath.lastIndexOf(System.getProperty("file.separator")));
                                String p = realPath.substring(0, (path2.lastIndexOf(System.getProperty("file.separator"))))+System.getProperty("file.separator")+"zycxFile"+System.getProperty("file.separator")+dir;
                                // 自定义的文件名称
                                String trueFileName= "pictutre"+prefix;
                                // 设置存放图片文件的路径
                                path=p+System.getProperty("file.separator")+trueFileName;
                                //转为标准文件盘符格式
                                String acesP = "http://"+ "pictureUpLoad"+":"+request.getServerPort()+"//"+"zycxFile"+"//"+dir+"//"+trueFileName;
                                final File localFile = new File(path);
                                //将文件复制到指定文件夹下面
                                try {
                                    if(!localFile.exists()){
                                        localFile.mkdirs();
                                    }
                                    file.transferTo(localFile);
                                } catch (Exception e) {
                                }
                                imagePath.add(path);
                                StringBuffer sb = new StringBuffer();
                                sb.append(acesP);
                                sb.append(",");
                                pictureUrls.append(sb);
                            }else{
                                return new String("系统暂不支持此格式图片");
                            }
                        }
                    }
                    String purl = "";
                    if(pictureUrls.toString()==null){
                        return new String("没有图片路径");
                    }else {
                        Map<String,String> retMap =new HashMap<>();
                        purl = pictureUrls.substring(0, pictureUrls.length() - 1);
                        return new String("请求成功");
                    }
                }else {
                    return new String("未发现图片");
                }
            }else{
                return new String("未发现图片");
            }
        }catch (Exception e){
            return new String("参数有误");
        }
    }
}
