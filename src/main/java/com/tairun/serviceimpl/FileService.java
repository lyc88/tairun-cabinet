package com.tairun.serviceimpl;

import com.tairun.dao.FilesMapper;
import com.tairun.model.Files;
import com.tairun.model.FilesExample;
import com.tairun.model.Selfcabinet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cyt on 2017/7/31.
 */
@Service
public class FileService {

    @Autowired
    SelfCabinetService selfCabinetService;

    @Resource
    private FilesMapper filesMapper;

    public List<Files> selectByImgid(int imgid) {
        FilesExample filesExample = new FilesExample();
        return filesMapper.selectByImgid(imgid);
    }

    public void insterFile(Files files) {
        try {
            filesMapper.insert(files);


            // 修改柜子图片信息
            Selfcabinet selfcabinet = new Selfcabinet();
            selfcabinet.setIsUpdate(new Byte("1"));
            selfcabinet.setCode(files.getImgId().toString());

            selfCabinetService.updateSel(selfcabinet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteFile(int imageid){
        try{
            filesMapper.deleteByPrimaryKey(imageid);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
