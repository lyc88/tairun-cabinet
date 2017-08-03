package com.tairun.dao;

import com.tairun.model.Cabinet;
import com.tairun.model.CabinetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CabinetMapper {
    /**
     * 根据id查询自提柜信息
     * @param id
     * @return
     */
    List<Cabinet> selectById(Integer id);

    List<Cabinet> selectByStatus(Byte status);

    int countByExample(CabinetExample example);

    int deleteByExample(CabinetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Cabinet record);

    int insertSelective(Cabinet record);

    List<Cabinet> selectByExample(CabinetExample example);

    Cabinet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Cabinet record, @Param("example") CabinetExample example);

    int updateByExample(@Param("record") Cabinet record, @Param("example") CabinetExample example);

    int updateByPrimaryKeySelective(Cabinet record);

    int updateByPrimaryKey(Cabinet record);
}