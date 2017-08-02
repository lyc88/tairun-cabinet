package com.tairun.serviceimpl;

import com.tairun.dao.LogMapper;
import com.tairun.model.Log;
import com.tairun.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by THINK on 2017/8/1.
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    public boolean insert(Log record) {
        logMapper.insert(record);
        return true;
    }
}
