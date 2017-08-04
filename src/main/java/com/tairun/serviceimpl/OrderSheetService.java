package com.tairun.serviceimpl;

import com.tairun.dao.OrderSheetMapper;
import com.tairun.model.OrderSheet;
import com.tairun.model.OrderSheetExample;
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
    /*
    *插入订单
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
}
