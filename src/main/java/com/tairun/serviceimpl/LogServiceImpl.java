package com.tairun.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tairun.dao.LogMapper;
import com.tairun.model.Log;
import com.tairun.model.LogExample;
import com.tairun.server.utils.EUDataGridResult;
import com.tairun.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    /**
     * 返回分页日志信息
     * @return
     */
    public EUDataGridResult getAccountPage(int pageNum, int pageSize){
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        LogExample logExample = new LogExample();
        //分页查询
        PageHelper.startPage(pageNum,pageSize);

        //查询条件
        LogExample.Criteria criteria = logExample.createCriteria();
        List list = logMapper.selectByExample(logExample);
        euDataGridResult.setRows(list);
        //取记录总条数
        PageInfo<Log> pageInfo = new PageInfo<Log>(list);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }
    public Integer deletebyid(Integer logid){
        int num=logMapper.deleteByPrimaryKey(logid);
        return num;
    }
}
