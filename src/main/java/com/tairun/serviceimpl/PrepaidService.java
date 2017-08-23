package com.tairun.serviceimpl;

import com.tairun.dao.PrepaidMapper;
import com.tairun.model.Prepaid;
import com.tairun.model.PrepaidExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by THINK on 2017/8/21.
 */
@Service
public class PrepaidService {
    @Autowired
    private PrepaidMapper prepaidMapper;
    public int save(Prepaid prepaid){
         int num=prepaidMapper.insert(prepaid);
        return num;
    }
    public List<Prepaid> findByorderid(String orderid){
        PrepaidExample prepaidExample= new PrepaidExample();
        //查询条件
        PrepaidExample.Criteria criteria = prepaidExample.createCriteria();
        criteria.andOrderidEqualTo(orderid);
        List<Prepaid> list = prepaidMapper.selectByExample(prepaidExample);
        if(null != list && list.size()>0){
            return list;
        }else{
            return null;
        }
    }
   public int updateorderid(Prepaid prepaid){
        int num=prepaidMapper.updateByPrimaryKey(prepaid);
        return num;
   }

}
