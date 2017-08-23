package com.tairun.service;

import com.tairun.model.Account;
import com.tairun.server.utils.EUDataGridResult;
import com.tairun.server.utils.Md5Util;
import com.tairun.serviceimpl.AccountService;
import com.tairun.serviceimpl.PrepaidService;
import com.tairun.serviceimpl.SelfCabinetService;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lycon 2017/7/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class AccountServiceTest {

    @Autowired
    PrepaidService prepaidService;

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
    public static String sendGet(String url, Map<String, String> parameters) {
        String result="";
        BufferedReader in = null;// 读取响应输入流
        StringBuffer sb = new StringBuffer();// 存储参数
        String params = "";// 编码之后的参数
        try {
            // 编码请求参数
            if(parameters.size()==1){
                for(String name:parameters.keySet()){
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),
                                    "UTF-8"));
                }
                params=sb.toString();
            }else{
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),
                                    "UTF-8")).append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }
            String full_url = url + "?" + params;
            System.out.println(full_url);
            // 创建URL对象
            java.net.URL connURL = new java.net.URL(full_url);
            // 打开URL连接
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
                    .openConnection();
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 建立实际的连接
            httpConn.connect();
            // 响应头部获取
            Map<String, List<String>> headers = httpConn.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : headers.keySet()) {
                System.out.println(key + "\t：\t" + headers.get(key));
            }
            // 定义BufferedReader输入流来读取URL的响应,并设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn
                    .getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result ;
    }
    public static void main(String[] args) {
      /* Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("name", "sarin");
        String result =sendGet("http://192.168.6.4:8080/legendshop/themeModule/selectBySubnumber/17080211223549739065", parameters);

        System.out.println(result+"===============");*/
      //定时执行

    }
    /**
     * 测试客户端
     */
    @Test
    public void testClient() throws IOException {
       /* List<String> list = new ArrayList<>();
        for (int i=0;i<50;i++) {
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
        }*/
        /*System.out.println("================================="+list.size());*/
        System.out.println("=============================");
        System.out.println(DigestUtils.md5Hex(new FileInputStream(new File("D:\\gitlocal\\tairun-cabinet\\target\\cabinet\\upfile\\com.rar"))));
        System.out.println(Md5Util.getMd5ByFile(new File("D:\\gitlocal\\tairun-cabinet\\target\\cabinet\\upfile\\com.rar")).toString());
        System.out.println("=============================");
    }
}
