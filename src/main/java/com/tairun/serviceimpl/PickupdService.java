package com.tairun.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tairun.dao.PickupdMapper;
import com.tairun.model.Pickupd;
import com.tairun.model.PickupdExample;
import com.tairun.server.utils.EUDataGridResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by THINK on 2017/8/17.
 */
@Service
public class PickupdService {
    @Autowired
    private PickupdMapper pickupdMapper;
    public int insertPickupd(Pickupd pickupd){
        int num=pickupdMapper.insert(pickupd);
        return num;
    }
    /**
     * 返回分页取件信息
     * @return
     */
    public EUDataGridResult getPickupdPage(int pageNum, int pageSize, String acount){
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        PickupdExample pickupdExample = new PickupdExample();
        //分页查询
        PageHelper.startPage(pageNum,pageSize);

        //查询条件
        PickupdExample.Criteria criteria = pickupdExample.createCriteria();
        if(StringUtils.isNotBlank(acount)){
            criteria.andAcountLike(acount+"%");
            criteria.andStatusLike("取件"+"%");
        }
        List list = pickupdMapper.selectByExample(pickupdExample);
        euDataGridResult.setRows(list);
        //取记录总条数
        PageInfo<Pickupd> pageInfo = new PageInfo<Pickupd>(list);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }
    /**
     * 返回分页存件信息
     * @return
     */
    public EUDataGridResult getPickupdownPage(int pageNum, int pageSize, String acount){
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        PickupdExample pickupdExample = new PickupdExample();
        //分页查询
        PageHelper.startPage(pageNum,pageSize);

        //查询条件
        PickupdExample.Criteria criteria = pickupdExample.createCriteria();
        if(StringUtils.isNotBlank(acount)){
            criteria.andAcountLike(acount+"%");
            criteria.andStatusLike("存件"+"%");
        }
        List list = pickupdMapper.selectByExample(pickupdExample);
        euDataGridResult.setRows(list);
        //取记录总条数
        PageInfo<Pickupd> pageInfo = new PageInfo<Pickupd>(list);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }
}
