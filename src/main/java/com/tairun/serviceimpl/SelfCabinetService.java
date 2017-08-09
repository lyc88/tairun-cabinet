package com.tairun.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tairun.dao.FilesMapper;
import com.tairun.dao.SelfcabinetMapper;
import com.tairun.model.Files;
import com.tairun.model.Selfcabinet;
import com.tairun.model.SelfcabinetExample;
import com.tairun.server.utils.EUDataGridResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private FilesMapper filesMapper;

    public EUDataGridResult selectSelfcabinetAll(int pageNum, int pageSize,String name,String code) {

        SelfcabinetExample selfcabinetExample = new SelfcabinetExample();
        SelfcabinetExample.Criteria criteria = selfcabinetExample.createCriteria();
        //
        if(StringUtils.isNotBlank(name)){
            criteria.andNameLike("%"+name+"%");
        }
        if (StringUtils.isNotBlank(code)){
            criteria.andCodeLike("%"+code+"%");
        }
        //
        PageHelper.startPage(pageNum, pageSize);
        List<Selfcabinet> list = selfcabinetMapper.selectByExample(selfcabinetExample);

        for(Selfcabinet selfcabinet : list){
            List<Files> fileList = filesMapper.selectByImgid(Integer.parseInt(selfcabinet.getCode()));
            selfcabinet.setFiles(fileList);
        }
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(list);
        //ȡ��¼������
        PageInfo<Selfcabinet> pageInfo = new PageInfo<>(list);
        euDataGridResult.setTotal(pageInfo.getTotal());

        System.out.println(JSON.toJSONString(euDataGridResult));
        return euDataGridResult;

    }

    /**
     * �����������Ϣ
     * @param selfcabinet
     * @return
     */
    public int save(Selfcabinet selfcabinet){
        int i = selfcabinetMapper.insertSelective(selfcabinet);
        return i;
    }
    /**
     * 根据自提柜编号查询
     * @return
     */
    public List<Selfcabinet> findBycode(String identifier){
        SelfcabinetExample selfCabinetExample = new SelfcabinetExample();
        //查询条件
        SelfcabinetExample.Criteria criteria = selfCabinetExample.createCriteria();
        criteria.andCodeEqualTo(identifier);
        List<Selfcabinet> list = selfcabinetMapper.selectByExample(selfCabinetExample);
        if(null != list && list.size()>0){
            System.out.println(JSONObject.toJSONString(list));
            return list;
        }else{
            return null;
        }
    }
    public int updateself(Selfcabinet selfcabinet){
        int num=selfcabinetMapper.updateByPrimaryKey(selfcabinet);
        return num;
    }

    public void updateSel(Selfcabinet selfcabinet){
        selfcabinetMapper.updateByPrimaryKeySelective(selfcabinet);
    }

}
