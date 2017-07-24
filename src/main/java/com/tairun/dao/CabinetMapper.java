package com.tairun.dao;

import com.tairun.model.Cabinet;
import com.tairun.model.CabinetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component("cabinetMapper")
public interface CabinetMapper {
     //查询所有柜子信息
    List<Cabinet> selectAll();
    //清空柜子
    int updateCabinet(int cabid);

    int countByExample(CabinetExample example);

    int deleteByExample(CabinetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Cabinet record);

    int insertSelective(Cabinet record);



    Cabinet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Cabinet record, @Param("example") CabinetExample example);

    int updateByExample(@Param("record") Cabinet record, @Param("example") CabinetExample example);

    int updateByPrimaryKeySelective(Cabinet record);

    int updateByPrimaryKey(Cabinet record);
}