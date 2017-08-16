package com.tairun.service;

import com.tairun.model.Account;
import com.tairun.server.utils.EUDataGridResult;
import com.tairun.serviceimpl.AccountService;
import com.tairun.serviceimpl.SelfCabinetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lycon 2017/7/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private SelfCabinetService selfCabinetService;

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
        EUDataGridResult euDataGridResult = accountService.getAccountPage(1,10,"","李");
        System.out.println(euDataGridResult.getTotal());
    }

    @Test
    public void testSaveCa(){
       /* for (int i=0;i<50;i++){
            Selfcabinet selfcabinet = new Selfcabinet();

            selfcabinet.setCode("abc"+i);
            selfcabinet.setName("字体奎"+i);
            selfcabinet.setCreateDate(new Date());
            selfcabinet.setImgId(i);
            selfcabinet.setUpdateDate(new Date());
            selfCabinetService.save(selfcabinet);
        }*/

        EUDataGridResult euDataGridResult = selfCabinetService.selectSelfcabinetAll(1,10,"","");
        System.out.println(euDataGridResult.getTotal());
    }

    /**
     * 测试客户端
     */
    @Test
    public void testClient() throws IOException {
        List<String> list = new ArrayList<>();
        for (int i=0;i<10;i++) {
            Socket socket = new Socket("112.74.54.67", 10102);
            System.out.println("客户端启动成功");
            // 2、获取输出流，向服务器端发送信息
            String msg = "##@1@105@{\n" +
                    "\t\"identifier\":\t\"123456\",\n" +
                    "\t\"action\":\t\"admin_open_box\",\n" +
                    "\t\"account\":\t\"1\",\n" +
                    "\t\"password\":\t\"\",\n" +
                    "\t\"boxNum\":\t12\n" +
                    "}$_";
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(msg.getBytes());
            socket.shutdownOutput();
            // 由Socket对象得到输入流，并构造相应的BufferedReader对象
            InputStream inputStream = socket.getInputStream();
            byte[] b = new byte[1024];
            String resp = "";
            while (inputStream.read(b) > -1) {
                resp += new String(b);
            }

            System.out.println("response:===="+i+"======" + resp);
            list.add(resp);
            socket.close();
        }
        System.out.println("================================="+list.size());
    }
}
