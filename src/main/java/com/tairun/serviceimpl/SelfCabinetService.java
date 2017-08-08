package com.tairun.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tairun.dao.SelfcabinetMapper;
import com.tairun.model.Cabinet;
import com.tairun.model.CabinetExample;
import com.tairun.model.Selfcabinet;
import com.tairun.model.SelfcabinetExample;
import com.tairun.server.utils.EUDataGridResult;
import org.apache.commons.lang3.StringUtils;
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

    public EUDataGridResult selectSelfcabinetAll(int pageNum, int pageSize,String name,String code) {

        SelfcabinetExample selfcabinetExample = new SelfcabinetExample();
        SelfcabinetExample.Criteria criteria = selfcabinetExample.createCriteria();
        //条件
        if(StringUtils.isNotBlank(name)){
            criteria.andNameLike("%"+name+"%");
        }
        if (StringUtils.isNotBlank(code)){
            criteria.andCodeLike("%"+code+"%");
        }
        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<Selfcabinet> list = selfcabinetMapper.selectByExample(selfcabinetExample);

        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(list);
        //取记录总条数
        PageInfo<Selfcabinet> pageInfo = new PageInfo<>(list);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;

    }

    /**
     * 保存自提柜信息
     * @param selfcabinet
     * @return
     */
    public int save(Selfcabinet selfcabinet){
        int i = selfcabinetMapper.insertSelective(selfcabinet);
        return i;
    }
}
