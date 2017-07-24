package com.tairun.service;

import com.tairun.model.Cabinet;
import java.util.List;
/**
 * Created by Administrator on 2017/7/18.
 */
public interface CabinetService {
   //查看自提柜信息
    List<Cabinet> selectAll();
    //清空柜子
    int updateCabinet(int cabid);
}
