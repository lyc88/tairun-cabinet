package com.tairun.server;

import com.alibaba.fastjson.JSON;
import com.tairun.action.Operaction;
import com.tairun.businessmodel.OpenCabinetb;
import com.tairun.server.session.Session;
import com.tairun.server.session.SessionImpl;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lyc on 2017/7/25.
 */
public class ServerHandler extends SimpleChannelInboundHandler {
    Logger logger = LoggerFactory.getLogger(ServerHandler.class);
    private static Operaction operaction = new Operaction();
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        logger.error(cause.getMessage());
        ctx.close();
        //super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info(" server channel active... ");
    }
    /**
     * 消息接收
     * @param channelHandlerContext
     * @param msg
     * @throws Exception
     */
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
            String request = (String)msg;
        System.out.println("Server :" + msg);
        //ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));

        //String msg1 = "##@1@140@{\"identifier\":123456,\"action\":\"goods_pickup\",\"boxNumber\":1,\"state\":\"normal\",\"result\":\"success\",\"customerPhone\":\"15278199288\",\"balance\":12.9}";
        String msg1 = handlerMessage(new SessionImpl(channelHandlerContext.channel()), request,channelHandlerContext);


        String response = msg1;
        channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer(response.getBytes("utf-8")));

    }


    @Override
    public void userEventTriggered(final ChannelHandlerContext ctx, Object evt) throws Exception {
        logger.info("超时了-==========================================");
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            if(event.state() == IdleState.ALL_IDLE){
                ctx.close();
               /* //清除超时会话
                ChannelFuture writeAndFlush = ctx.writeAndFlush("you will close");

                writeAndFlush.addListener(new ChannelFutureListener() {

                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        ctx.channel().close();
                    }
                });*/
            }
        }else{
            super.userEventTriggered(ctx, evt);
        }
    }


    /**
     *
     *
     * 消息处理
     *  第一步消息 数据初始化 相当于玩家登入 添加到在线SessionManage 管理 及 温度等信息数据初始化操作
     *
     * 1.b)	用户取货之后将柜子编号发送到服务器，告诉服务器该快件已经被取走
     * 2.a)	登录注册都由服务器验证，用户名必须是手机号码
     *
     * a)泰润商城的订单
     * i.自提柜读取到运单号码之后，将运单号码发送到服务器请求获取收货人手机号码
     * ii.将运单号、联系人号码、柜子的编号、柜子的开箱码发送到服务器，服务器通过短信接口发送开箱码给收货人
     * b)其他订单
     * i.  不是泰润商城的订单，使用一个柜子收费0.5元
     * ii.将运单号、联系人号码、柜子的编号、柜子的开箱码发送到服务器，服务器通过短信接口发送开箱码给收货人
     */
    private String handlerMessage(Session session,String msg,ChannelHandlerContext channelHandlerContext){

        logger.info("请求数据报文为：{}",msg);
        String  code = "";
        //根据消息类型 判断是否是新的客户端
        if(msg.indexOf("@")!=-1) {
            String[] args = msg.split("@");
            if (null != args && args.length > 3) {
                int args2 = Integer.valueOf(args[2]);
                //包头
                if (args[0].equals("##")) {
                    //长度和内容一样，不一样可能数据丢失
                    System.out.println(args[3].length()+"========================================");
                    if (args[3].length() == args2) {
                        code = operaction.event(session, args[3]);
                    } else {
                       return fan();
                    }
                }else {
                    return fan();
                }
            } else {
                return fan();
            }
        }else{
            return fan();
        }
        logger.info("请求返回数据报文为：{}", JSON.toJSON(code));
        return code;
    }
    public String fan(){
        OpenCabinetb openCabinetb= new OpenCabinetb();
        openCabinetb.setResult("false");
        int num1 = JSON.toJSONString(openCabinetb).length();
        String response = "##@1@" + num1 + "@" + JSON.toJSONString(openCabinetb);
        return response;
    }
}
