package com.tairun.serviceimpl;

import com.tairun.dao.UserMapper;
import com.tairun.model.Cabinet;
import com.tairun.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    public List<Cabinet> selectByAccount(String tele) {
        return userMapper.selectByAccount(tele);
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
