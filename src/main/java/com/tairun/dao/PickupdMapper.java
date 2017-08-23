package com.tairun.dao;

import com.tairun.model.Pickupd;
import com.tairun.model.PickupdExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PickupdMapper {
    int countByExample(PickupdExample example);

    int deleteByExample(PickupdExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Pickupd record);

    int insertSelective(Pickupd record);

    List<Pickupd> selectByExample(PickupdExample example);

    Pickupd selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Pickupd record, @Param("example") PickupdExample example);

    int updateByExample(@Param("record") Pickupd record, @Param("example") PickupdExample example);

    int updateByPrimaryKeySelective(Pickupd record);

    int updateByPrimaryKey(Pickupd record);
}