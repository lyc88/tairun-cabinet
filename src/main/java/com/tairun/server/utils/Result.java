package com.tairun.server.utils;

import com.alibaba.fastjson.JSON;
import com.tairun.server.session.SessionManager;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by lyc on 2017/7/25.
 * 消息类型
 */
public class Result {
    /**
     * 状态码
     */
    private int code;
    /**
     * 信息
     */
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    public static void main(String[] args) throws IOException {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        //System.out.println();

        Socket socket = null;
        OutputStream os = null;
        InputStream is = null;

        socket = new Socket(InetAddress.getByName("127.0.0.1"), 10102);
        String message = "hello";
        os = socket.getOutputStream();
        for (int i = 0; i < 100; i++) {
            DataOutputStream dataOutputStream = new DataOutputStream(os);
            os.write((i + message + "$_").getBytes());
            dataOutputStream.flush();
        }
    }
}
