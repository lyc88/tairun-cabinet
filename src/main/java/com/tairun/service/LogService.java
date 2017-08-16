package com.tairun.service;

import com.tairun.model.Log;
import com.tairun.server.utils.EUDataGridResult;

/**
 * Created by THINK on 2017/8/1.
 */
public interface LogService {

    boolean insert(Log record);
    public EUDataGridResult getAccountPage(int pageNum, int pageSize);
    public Integer deletebyid(Integer logid);
}
