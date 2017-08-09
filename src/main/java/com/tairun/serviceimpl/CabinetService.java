package com.tairun.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tairun.dao.CabinetMapper;
import com.tairun.model.Cabinet;
import com.tairun.model.CabinetExample;
import com.tairun.server.utils.EUDataGridResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cyt on 2017/7/18.
 */
@Service
public class CabinetService  {
    @Resource
    private CabinetMapper cabinetMapper;

    /**
     * 查询自提柜信息
     * @return
     */
    public EUDataGridResult selectAll(int pageNum,int pageSize,int status,int id) {
        PageHelper.startPage(pageNum, pageSize);
        List<Cabinet> list = cabinetMapper.selectById(id);
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(list);
        PageInfo pageInfo = new PageInfo(list);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }
    /**
     * 根据状态查询自提柜信息
     * @return
     */
    public EUDataGridResult selectByStatus(int pageNum, int pageSize, Byte status) {
        PageHelper.startPage(pageNum, pageSize);
        List<Cabinet> list = cabinetMapper.selectByStatus(status);
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(list);
        PageInfo pageInfo = new PageInfo(list);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }

    /*public int updateCabinet(Cabinet cabinet) {
        *//*CabinetExample cabinetExample = new CabinetExample();
        CabinetExample.Criteria criteria=cabinetExample.createCriteria();
        int num=cabinetMapper.updateByPrimaryKey(cabinet);
        return num;*//*
        return 0;
    }*/

   /* public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        CabinetService cabinetService = (CabinetService) applicationContext.getBean("cabinetServiceImpl");
        System.out.println(cabinetService.selectAll().get(0).getBeginDate());
    }*/
    /**
     * 根据自提柜号跟柜子号找到相对应的柜子
     * @return
     */
    public List<Cabinet> findBycodeandnumber(String identifier, int box_number){
        CabinetExample cabinetExample = new CabinetExample();
        //查询条件
        CabinetExample.Criteria criteria = cabinetExample.createCriteria();
        criteria.andCodeEqualTo(identifier);
        criteria.andNumberEqualTo(box_number);
        List<Cabinet> list = cabinetMapper.selectByExample(cabinetExample);
        if(null != list && list.size()>0){
            System.out.println(JSONObject.toJSONString(list));
            return list;
        }else{
            return null;
        }
    }
    public List<Cabinet> findBycode(String identifier){
        CabinetExample cabinetExample = new CabinetExample();
        //查询条件
        CabinetExample.Criteria criteria = cabinetExample.createCriteria();
        criteria.andCodeEqualTo(identifier);
        List<Cabinet> list = cabinetMapper.selectByExample(cabinetExample);
        if(null != list && list.size()>0){
            System.out.println(JSONObject.toJSONString(list));
            return list;
        }else{
            return null;
        }
    }
    public int updatecabinet(Cabinet cabinet){
        int num=cabinetMapper.updateByPrimaryKey(cabinet);
        return num;
    }
}
