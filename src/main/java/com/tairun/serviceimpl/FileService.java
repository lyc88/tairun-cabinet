package com.tairun.serviceimpl;

import com.tairun.dao.FilesMapper;
import com.tairun.model.Files;
import com.tairun.model.FilesExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cyt on 2017/7/31.
 */
    @Service
    public class FileService {
    @Resource
    private FilesMapper filesMapper;
    public List<Files> selectByImgid(int imgid){
           FilesExample filesExample=new FilesExample();
           return filesMapper.selectByImgid(imgid);
    }
}
