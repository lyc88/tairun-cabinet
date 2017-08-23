package com.tairun.dao;

import com.tairun.model.Prepaid;
import com.tairun.model.PrepaidExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PrepaidMapper {
    int countByExample(PrepaidExample example);

    int deleteByExample(PrepaidExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Prepaid record);

    int insertSelective(Prepaid record);

    List<Prepaid> selectByExample(PrepaidExample example);

    Prepaid selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Prepaid record, @Param("example") PrepaidExample example);

    int updateByExample(@Param("record") Prepaid record, @Param("example") PrepaidExample example);

    int updateByPrimaryKeySelective(Prepaid record);

    int updateByPrimaryKey(Prepaid record);
}