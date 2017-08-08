package com.tairun.listener;

import com.tairun.server.Server;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by lyc on 2017/7/25.
 * 容器启动时候 启动服务器
 */
public class AllResLoadListener implements ServletContextListener {

    private  WebApplicationContext ctx;
    private Server server;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //启动服务器
        ServletContext sc = servletContextEvent.getServletContext();
        /*ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
        server = (Server) ctx.getBean("server");
        server.start();*/
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        server.stop();
        System.out.println("=============================================");
    }
}
