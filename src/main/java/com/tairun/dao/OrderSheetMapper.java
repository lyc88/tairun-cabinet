package com.tairun.dao;

import com.tairun.model.OrderSheet;
import com.tairun.model.OrderSheetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderSheetMapper {
    int countByExample(OrderSheetExample example);

    int deleteByExample(OrderSheetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderSheet record);

    int insertSelective(OrderSheet record);

    List<OrderSheet> selectByExample(OrderSheetExample example);

    OrderSheet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderSheet record, @Param("example") OrderSheetExample example);

    int updateByExample(@Param("record") OrderSheet record, @Param("example") OrderSheetExample example);

    int updateByPrimaryKeySelective(OrderSheet record);

    int updateByPrimaryKey(OrderSheet record);
}