package com.tairun.service;

import com.tairun.model.Cabinet;

import java.util.List;

/**
 * Created by cyt on 2017/7/20.
 */
public interface UserService {
    /*快递员查看滞留件*/
    List<Cabinet> selectByAccount(String tele);
}
