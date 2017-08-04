package com.tairun.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tairun.dao.AccountMapper;
import com.tairun.model.Account;
import com.tairun.model.AccountExample;
import com.tairun.server.utils.EUDataGridResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by lyc on 2017/7/26.
 */
@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 返回分页快递员信息
     * @return
     */
    public EUDataGridResult getAccountPage(int pageNum,int pageSize,String telephone,String name){
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        AccountExample accountExample = new AccountExample();
        //分页查询
        PageHelper.startPage(pageNum,pageSize);

        //查询条件
        AccountExample.Criteria criteria = accountExample.createCriteria();
        if(StringUtils.isNotBlank(telephone)){
            criteria.andTelephoneLike(telephone);
        }
        if(StringUtils.isNotBlank(name)){
            criteria.andNameLike(name);
        }
        List list = accountMapper.selectByExample(accountExample);
        euDataGridResult.setRows(list);
        //取记录总条数
        PageInfo<Account> pageInfo = new PageInfo<Account>(list);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }

    /**
     * 保存快递员账号
     * @param account
     * @return
     */
    public int save(Account account){
        account.setCreateDate(new Date());
       return accountMapper.insert(account);
    }

    /**
     * 根据电话 号码查询
     * @param telephone
     * @return
     */
    public Account findByTelephone(String telephone){
        AccountExample accountExample = new AccountExample();
        //查询条件
        AccountExample.Criteria criteria = accountExample.createCriteria();
        criteria.andTelephoneLike(telephone);
        List<Account> list = accountMapper.selectByExample(accountExample);
        if(null != list && list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }
    //根据账户查询余额
    public List<Account> findByTelephonetwo(String telephone){
        AccountExample accountExample = new AccountExample();
        //查询条件
        AccountExample.Criteria criteria = accountExample.createCriteria();
        criteria.andTelephoneLike(telephone);
        List<Account> list = accountMapper.selectByExample(accountExample);
        if(null != list && list.size()>0){
            return list;
        }else{
            return null;
        }
    }
    /**
     * 查询快递员账户密码是否存在
     * @return
     */
    public List<Account> findByTelephoneandpassword(String account,String password){
        AccountExample accountExample = new AccountExample();
        //查询条件
        AccountExample.Criteria criteria = accountExample.createCriteria();
        criteria.andTelephoneEqualTo(account);
        criteria.andPasswordEqualTo(password);
        List<Account> list = accountMapper.selectByExample(accountExample);
        if(null != list && list.size()>0){
            System.out.println(JSONObject.toJSONString(list));
            return list;
        }else{
            return null;
        }
    }
    public int updateaccount(Account account1){
        int num=accountMapper.updateByPrimaryKey(account1);

        return num;
    }
}
