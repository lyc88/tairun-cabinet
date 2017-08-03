package com.tairun.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tairun.dao.SelfcabinetMapper;
import com.tairun.model.Cabinet;
import com.tairun.model.CabinetExample;
import com.tairun.model.Selfcabinet;
import com.tairun.model.SelfcabinetExample;
import com.tairun.server.utils.EUDataGridResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cyt on 2017/7/27.
 */
@Service
public class SelfCabinetService {
    @Resource
    private SelfcabinetMapper selfcabinetMapper;

    public EUDataGridResult selectSelfcabinetAll(int pageNum, int pageSize) {
        Selfcabinet selfcabinet=new Selfcabinet();
        SelfcabinetExample selfcabinetExample = new SelfcabinetExample();
        SelfcabinetExample.Criteria criteria = selfcabinetExample.createCriteria();
        PageHelper.startPage(pageNum, pageSize);
        List<Selfcabinet> list = selfcabinetMapper.selectByExample(selfcabinetExample);
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(list);
        PageInfo pageInfo = new PageInfo(list);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }
}
