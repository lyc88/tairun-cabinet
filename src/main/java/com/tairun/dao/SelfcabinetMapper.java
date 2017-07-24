package com.tairun.dao;

import com.tairun.model.Selfcabinet;
import com.tairun.model.SelfcabinetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SelfcabinetMapper {
    int countByExample(SelfcabinetExample example);

    int deleteByExample(SelfcabinetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Selfcabinet record);

    int insertSelective(Selfcabinet record);

    List<Selfcabinet> selectByExample(SelfcabinetExample example);

    Selfcabinet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Selfcabinet record, @Param("example") SelfcabinetExample example);

    int updateByExample(@Param("record") Selfcabinet record, @Param("example") SelfcabinetExample example);

    int updateByPrimaryKeySelective(Selfcabinet record);

    int updateByPrimaryKey(Selfcabinet record);
}