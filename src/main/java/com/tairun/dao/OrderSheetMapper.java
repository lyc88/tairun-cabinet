package com.tairun.dao;

import com.tairun.model.ordersheet;
import com.tairun.model.ordersheetExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderSheetMapper {
    int countByExample(ordersheetExample example);

    int deleteByExample(ordersheetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ordersheet record);

    int insertSelective(ordersheet record);

    List<ordersheet> selectByExample(ordersheetExample example);

    ordersheet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ordersheet record, @Param("example") ordersheetExample example);

    int updateByExample(@Param("record") ordersheet record, @Param("example") ordersheetExample example);

    int updateByPrimaryKeySelective(ordersheet record);

    int updateByPrimaryKey(ordersheet record);
}