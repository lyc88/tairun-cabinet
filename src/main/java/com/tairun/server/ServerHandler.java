package com.tairun.server;

import com.tairun.action.Operaction;
import com.tairun.server.session.Session;
import com.tairun.server.session.SessionImpl;
import com.tairun.server.session.SessionManager;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lyc on 2017/7/25.
 */
public class ServerHandler extends SimpleChannelInboundHandler {
    Logger logger = LoggerFactory.getLogger(ServerHandler.class);
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
        String msg1 = handlerMessage(new SessionImpl(channelHandlerContext.channel()), request,channelHandlerContext);
        String response = "服务器响应：" + msg1 ;
        channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));

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
        //根据消息类型 判断是否是新的客户端
        String[] args = msg.split("@");
        int args2= Integer.valueOf(args[2]);
        String code=null;
        System.out.println(args[3].length());
        if(msg.indexOf("@")!=-1){
            if(args[0].equals("##")){
                if(args[3].length()==args2){
                    /*SessionManager.putSession("code12",session);
                   System.out.println(SessionManager.getOnlinePlayers().size());*/
                    Operaction operaction = new Operaction();
                    code =operaction.event(session,msg);
                    /*System.out.println(code);*/
                   SessionManager.sendMessage("code12",code);
                }
            }else{

            }
        }else{

        }

        return code;
    }
}
