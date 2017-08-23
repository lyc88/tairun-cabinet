package com.tairun.action;

import com.tairun.model.Files;
import com.tairun.model.Selfcabinet;
import com.tairun.server.utils.SpringUtil;
import com.tairun.serviceimpl.FileService;
import com.tairun.serviceimpl.SelfCabinetService;

import java.util.List;

/**
 * Created by lkh on 2017/8/20.
 * 更新返回
 */
public class RemoteupdatRestate {
    private FileService fileService = SpringUtil.getBean(FileService.class);
    private SelfCabinetService selfCabinetService=SpringUtil.getBean(SelfCabinetService.class);
    public void update(String identifier,String newVersionUpdata,String newPicUpdata){
        Files files = new Files();
        int imgid=Integer.parseInt(identifier);
        if(newVersionUpdata.equals("success")){
            List<Selfcabinet> list=selfCabinetService.findBycode(identifier);
            if(newPicUpdata.equals("success")){
                if(null!=list){
                    for(Selfcabinet selfcabinet:list){
                        selfcabinet.setUpdateisorno("固件与图片更新成功");
                        selfcabinet.setImgUpdate(0);
                        selfcabinet.setIsUpdate(new Byte("0"));
                        selfCabinetService.updateisorno(selfcabinet);
                    }
                }
            }else if(newPicUpdata.equals("false")){
                if(null!=list){
                    for(Selfcabinet selfcabinet:list){
                        selfcabinet.setUpdateisorno("固件更新成功图片更新失败");
                        selfcabinet.setImgUpdate(0);
                        selfcabinet.setIsUpdate(new Byte("0"));
                        selfCabinetService.updateisorno(selfcabinet);
                    }
                }
            }
        }else if(newVersionUpdata.equals("false")){
            List<Selfcabinet> list=selfCabinetService.findBycode(identifier);
            if(newPicUpdata.equals("success")){
                if(null!=list){
                    for(Selfcabinet selfcabinet:list){
                        selfcabinet.setUpdateisorno("固件与图片更失败");
                        selfcabinet.setImgUpdate(0);
                        selfcabinet.setIsUpdate(new Byte("0"));
                        selfCabinetService.updateisorno(selfcabinet);
                    }
                }
            }else if(newPicUpdata.equals("false")){
                if(null!=list){
                    for(Selfcabinet selfcabinet:list){
                        selfcabinet.setUpdateisorno("固件更新失败图片更新成功");
                        selfcabinet.setImgUpdate(0);
                        selfcabinet.setIsUpdate(new Byte("0"));
                        selfCabinetService.updateisorno(selfcabinet);
                    }
                }
            }
        }
        List<Files> list1=fileService.selectByImgid(imgid);
        for(Files files1:list1){
            if(files1.getSort()==1){
                int imgids=0;
                imgids=files1.getId();
                fileService.deleteFile(imgids);
            }
        }
    }
}
