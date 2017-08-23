package com.tairun.serviceimpl;

import com.tairun.dao.UserMapper;
import com.tairun.model.User;
import com.tairun.model.UserExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lyc on 2017/7/27.
 * 管理员操作
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public User login(String name,String password){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(name);
        criteria.andPasswordEqualTo(password);
        List<User> list = userMapper.selectByExample(userExample);

        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
    /**
     * 查询管理员账户密码是否正确
     * @return
     */
    public List<User> findByusernameandpassword(String account, String password){
        UserExample userExample = new UserExample();
        //查询条件
        UserExample.Criteria criteria = userExample.createCriteria();
        if(StringUtils.isNoneBlank(account)) {
            criteria.andUsernameEqualTo(account);
        }
        if(StringUtils.isNoneBlank(password)) {
            criteria.andPasswordEqualTo(password);
        }
        List<User> list = userMapper.selectByExample(userExample);
        if(null != list && list.size()>0){
            return list;
        }else{
            return null;
        }
    }
}
