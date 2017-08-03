package com.tairun.service;

import com.tairun.model.Account;
import com.tairun.serviceimpl.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by lycon 2017/7/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void testSave(){

         for (int i=0;i<50;i++) {
             Account account  = new Account();
             account.setName("张三"+1);
             account.setAccount(50.0+i);
             account.setTelephone("13297946558");
             account.setCreateDate(new Date());
             accountService.save(account);
         }
    }

    @Test
    public void testUpdate(){
        //accountService.
    }
}
