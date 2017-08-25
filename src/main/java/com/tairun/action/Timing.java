package com.tairun.action;

/**
 * Created by THINK on 2017/8/19.
 */

import com.alibaba.fastjson.JSON;
import com.tairun.businessmodel.Timingb;
import com.tairun.model.Files;
import com.tairun.model.Selfcabinet;
import com.tairun.server.utils.DataPropertiesUtil;
import com.tairun.server.utils.SpringUtil;
import com.tairun.serviceimpl.FileService;
import com.tairun.serviceimpl.SelfCabinetService;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 *定时执行的任务
 */
public class Timing {
    private SelfCabinetService selfCabinetService= SpringUtil.getBean(SelfCabinetService.class);
    private FileService fileService = SpringUtil.getBean(FileService.class);
    public String timing(String identifier,String action,String temperature){
        String response="",NewPicUrl="",NewVersionUrl="",md5y="",imgname="",Md="";
        int getupdate=0,getimgupdate=0,imgid=0;
        List<Selfcabinet> list=selfCabinetService.findBycode(identifier);
        if(null!=list){
            for(Selfcabinet selfcabinet:list){
                getupdate=selfcabinet.getIsUpdate();
                getimgupdate=selfcabinet.getImgUpdate();
                imgid=selfcabinet.getImgId();
                selfcabinet.setInfo(temperature);
                selfCabinetService.updateisorno(selfcabinet);
            }
            Timingb timingb= new Timingb();
            //是否图片更新
            if(getimgupdate==0){
                timingb.setIdentifier(identifier);
                timingb.setAction(action);
                timingb.setIsNewAdPic("no");
                DataPropertiesUtil prop=new DataPropertiesUtil();
                //是否固件更新
                if(getupdate==0){
                    timingb.setIsNewVersion("no");
                    timingb.setResult("success");
                    int num1= JSON.toJSONString(timingb).length();
                    response = "##@1@"+num1+"@"+ JSON.toJSONString(timingb);
                }else if(getupdate==1){
                    List<Files> list1=fileService.selectByImgid(imgid);
                    for(Files files : list1){
                        if(files.getSort()==1){
                            String aa="";
                            Md="";
                            aa=prop.getPropsAsString("IsNewVersion");
                            NewPicUrl=files.getFilePath()+files.getFileName();
                            Md=MD5jy(NewPicUrl);
                            md5y+=Md;
                            NewVersionUrl+=(aa+files.getFileName()+",");
                        }
                    }
                    timingb.setIsNewVersion("yes");
                    timingb.setNewPicUrl(imgname);
                    timingb.setNewVersionUrl(NewVersionUrl);
                    timingb.setNewVersionMD5(md5y);
                    timingb.setResult("success");
                    int num1= JSON.toJSONString(timingb).length();
                    response = "##@1@"+num1+"@"+ JSON.toJSONString(timingb);
                }else{
                    timingb.setIsNewVersion("no");
                    timingb.setResult("false");
                    int num1= JSON.toJSONString(timingb).length();
                    response = "##@1@"+num1+"@"+ JSON.toJSONString(timingb);
                }
            }else if(getimgupdate==1){
                System.out.println(imgid);
                response=updateveryes(identifier,action,getupdate,imgid);
            }
        }else{
            Timingb timingb= new Timingb();
            timingb.setResult("false");
            int num1= JSON.toJSONString(timingb).length();
            response = "##@1@"+num1+"@"+ JSON.toJSONString(timingb);
        }
        return response;
    }
    /**
     * MD5加密校验
     */
    public String MD5jy(String fileurl){
        String Md=null;
        try {
            System.out.println(fileurl+"+++++++++");
            Md=(DigestUtils.md5Hex(new FileInputStream(new File(fileurl))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Md;
    }
    public String updateveryes(String identifier,String action,int getupdate,int imgid){
        String response="",NewPicUrl="",NewVersionUrl="",md5y="",imgname="",Md="";
        List<Files> list1=fileService.selectByImgid(imgid);
        DataPropertiesUtil prop=new DataPropertiesUtil();
        String aa;
        for(Files files : list1){
            if(files.getSort()==0){
                aa="";
                aa=prop.getPropsAsString("IsNewAdPic");
                imgname+=(aa+files.getFileName()+",");
                System.out.println(imgname);
            }
        }
        Timingb timingb= new Timingb();
        timingb.setIdentifier(identifier);
        timingb.setAction(action);
        timingb.setIsNewAdPic("yes");
        if(getupdate==0){
            timingb.setIsNewVersion("no");
            String img=imgname.substring(3);
            timingb.setNewPicUrl(img);
            timingb.setNewVersionUrl("");
            timingb.setResult("success");
            int num1= JSON.toJSONString(timingb).length();
            response = "##@1@"+num1+"@"+ JSON.toJSONString(timingb);
        }else if(getupdate==1){
            List<Files> list2=fileService.selectByImgid(imgid);
            for(Files files : list2){
                if(files.getSort()==1){
                    aa="";
                    Md="";
                    aa=prop.getPropsAsString("IsNewVersion");
                    NewPicUrl=files.getFilePath()+File.separator+files.getFileName();
                    Md=MD5jy(NewPicUrl);
                    md5y+=Md;
                    NewVersionUrl+=(aa+files.getFileName()+",");
                }
            }
            timingb.setIsNewVersion("yes");
            timingb.setNewPicUrl(imgname);
            timingb.setNewVersionUrl(NewVersionUrl);
            timingb.setNewVersionMD5(md5y);
            timingb.setResult("success");
            int num1= JSON.toJSONString(timingb).length();
            response = "##@1@"+num1+"@"+ JSON.toJSONString(timingb);
        }else{
            timingb.setIsNewVersion("no");
            timingb.setNewPicUrl(imgname);
            timingb.setNewVersionUrl("");
            timingb.setResult("success");
            int num1= JSON.toJSONString(timingb).length();
            response = "##@1@"+num1+"@"+ JSON.toJSONString(timingb);
        }
        return response;
    }
}
