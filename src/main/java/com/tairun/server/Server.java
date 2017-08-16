package com.tairun.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.stereotype.Component;

/**
 * Created by lyc on 2017/7/25.
 * 服务类
 */
@Component
public class Server {
    /** 创建boss和worker */
    private   EventLoopGroup bossGroup = new NioEventLoopGroup();
    private   EventLoopGroup workerGroup = new NioEventLoopGroup();
    /***  服务类 */
    ServerBootstrap b = new ServerBootstrap();
    ChannelFuture channelFuture;
    /**
     * 启动
     */
    public void start() {
        try {
            // 设置循环线程组事例
            b.group(bossGroup, workerGroup);

            // 设置channel工厂
            b.channel(NioServerSocketChannel.class);

            // 设置管道
            b.childHandler(new ChannelInitializer<SocketChannel>()   {
                @Override
                public void initChannel(SocketChannel sc) throws Exception {
                   // ch.pipeline().addLast(new RequestDecoder());
                  //  ch.pipeline().addLast(new ResponseEncoder());
                    ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
                    sc.pipeline().addLast(new IdleStateHandler(5, 5, 16));
                    sc.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
                    //sc.pipeline().addLast()
                    sc.pipeline().addLast(new StringDecoder());

                    sc.pipeline().addLast(new ServerHandler());
                }
            });

            b.option(ChannelOption.SO_BACKLOG, 2048);// 链接缓冲池队列大小

            // 绑定端口
            channelFuture =  b.bind(10102).sync();

            System.out.println("start!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        try {
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
