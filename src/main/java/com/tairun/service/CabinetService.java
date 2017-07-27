package com.tairun.service;

import com.tairun.model.Cabinet;

import java.util.List;
/**
 * Created by cyt on 2017/7/18.
 */
public interface CabinetService {
   //查看自提柜信息
    List<Cabinet> selectAll(Byte status,Integer page,Integer rows);
    //清空柜子
    int updateCabinet(Cabinet cabinet);
}
