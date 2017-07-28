package com.tairun.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tairun.dao.AccountMapper;
import com.tairun.model.Account;
import com.tairun.model.AccountExample;
import com.tairun.server.utils.EUDataGridResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        PageInfo<Account> pageInfo = new PageInfo<>(list);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }

    /**
     * 保存快递员账号
     * @param account
     * @return
     */
    public int save(Account account){
       return accountMapper.insert(account);
    }
}
