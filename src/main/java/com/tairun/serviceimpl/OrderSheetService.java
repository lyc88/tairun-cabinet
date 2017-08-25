package com.tairun.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tairun.dao.OrderSheetMapper;
import com.tairun.model.OrderSheet;
import com.tairun.model.OrderSheetExample;
import com.tairun.server.utils.EUDataGridResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by THINK on 2017/8/3.
 */
@Service
public class OrderSheetService {
    @Autowired
    private OrderSheetMapper orderSheetMapper;
    private AccountService accountService;

    /**
     * 插入订单
     * @param orderSheet
     * @return
     */
    public int insertOrderSheet(OrderSheet orderSheet){
        int num=orderSheetMapper.insert(orderSheet);
        return num;
    }
    /*
    *删除订单
     */
    public int deleteOrderSheet(int id){
        int num=orderSheetMapper.deleteByPrimaryKey(id);
        return num;
    }
    /**
     * 查询相对于的柜子
     * @return
     */
    public List<OrderSheet> findByBoxIdent(String identifier, int boxNumbe1){
       OrderSheetExample orderSheetExample = new OrderSheetExample();
        //查询条件
        OrderSheetExample.Criteria criteria = orderSheetExample.createCriteria();
        criteria.andBoxNumberEqualTo(boxNumbe1);
        criteria.andIdentifierEqualTo(identifier);
        List<OrderSheet> list = orderSheetMapper.selectByExample(orderSheetExample);
        if(null != list && list.size()>0){
            return list;
        }else{
            return null;
        }
    }
    /*
    *根据运单号找到相对于的联系号码
     */
    public List<OrderSheet> findBywaybillnumber(String waybill_number){
        OrderSheetExample orderSheetExample = new OrderSheetExample();
        //查询条件
        OrderSheetExample.Criteria criteria = orderSheetExample.createCriteria();
        criteria.andWaybillNumberEqualTo(waybill_number);
        List<OrderSheet> list = orderSheetMapper.selectByExample(orderSheetExample);
        if(null != list && list.size()>0){
            return list;
        }else{
            return null;
        }
    }
    /**
     *查询所有订单带分页
     */
    public EUDataGridResult getOrderSheetPage(int pageNum, int pageSize,String account){
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        OrderSheetExample orderSheetExample = new OrderSheetExample();
        //分页查询
        PageHelper.startPage(pageNum,pageSize);

        //查询条件
        OrderSheetExample.Criteria criteria = orderSheetExample.createCriteria();
        if(StringUtils.isNotBlank(account)){
            criteria.andAccountEqualTo(account);
        }
        List list = orderSheetMapper.selectByExample(orderSheetExample);
        euDataGridResult.setRows(list);
        //取记录总条数
        PageInfo<OrderSheet> pageInfo = new PageInfo<OrderSheet>(list);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }
    /**
     *查询所有订单
     */
    public List<OrderSheet> getOrderSheetPage1(){
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        OrderSheetExample orderSheetExample = new OrderSheetExample();
        //查询条件
        OrderSheetExample.Criteria criteria = orderSheetExample.createCriteria();
        List list = orderSheetMapper.selectByExample(orderSheetExample);
        euDataGridResult.setRows(list);
        //取记录总条数
        PageInfo<OrderSheet> pageInfo = new PageInfo<OrderSheet>(list);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return list;
    }
    /**
     * 修改
     */
    public int updateorder(OrderSheet orderSheet){
        int num=orderSheetMapper.updateByPrimaryKey(orderSheet);
        return num;
    }
}
