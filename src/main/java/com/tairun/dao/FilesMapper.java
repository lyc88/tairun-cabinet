package com.tairun.dao;

import com.tairun.model.Files;
import com.tairun.model.FilesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FilesMapper {
    int countByExample(FilesExample example);

    int deleteByExample(FilesExample example);

    int deleteByPrimaryKey(Integer id);


    int insert(Files record);

    int insertSelective(Files record);

    List<Files> selectByImgid(int imgid);

    List<Files> selectByExample(FilesExample example);
    List<Files> selectByImgidsore(int imgid);
    Files selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Files record, @Param("example") FilesExample example);

    int updateByExample(@Param("record") Files record, @Param("example") FilesExample example);

    int updateByPrimaryKeySelective(Files record);

    int updateByPrimaryKey(Files record);
}