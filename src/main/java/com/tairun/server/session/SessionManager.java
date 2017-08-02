package com.tairun.server.session;

import io.netty.buffer.Unpooled;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lyc on 2017/7/25.
 */
public class SessionManager {

    /*,
     * 在线会话
     */
    private static final ConcurrentHashMap<String, Session> onlineSessions = new ConcurrentHashMap<String, Session>();

    /**
     * 加入
     * @param codeId 编码
     * @param session
     * @return
     */
    public static boolean putSession(String codeId, Session session){
        if(!onlineSessions.containsKey(codeId)){
            boolean success = onlineSessions.putIfAbsent(codeId, session)== null? true : false;
            return success;
        }
        return false;
    }

    /**
     * 移除
     * @param codeId
     */
    public static Session removeSession(String codeId){
        return onlineSessions.remove(codeId);
    }

    /**
     * 发送消息
     * @param codeId
     * @param message
     */
    public static  void sendMessage(String codeId, String message){
        Session session = onlineSessions.get(codeId);
        if (session != null && session.isConnected()) {
            session.write(Unpooled.copiedBuffer(message.getBytes()));
        }
    }

    /**
     * 发送消息[protoBuf协议]
     * @param <T>
     * @param playerId
     * @param message
     */
  /*  public static <T extends GeneratedMessage> void sendMessage(long playerId, short module, short cmd, T message){
        Session session = onlineSessions.get(playerId);
        if (session != null && session.isConnected()) {
            Response response = new Response(module, cmd, message.toByteArray());
            session.write(response);
        }
    }*/

    /**
     * 是否在线
     * @param code
     * @return
     */
    public static boolean isOnlinePlayer(String code){
        return onlineSessions.containsKey(code);
    }

    /**
     * 获取所有在线玩家
     * @return
     */
    public static Set<String> getOnlinePlayers() {
        return Collections.unmodifiableSet(onlineSessions.keySet());
    }
}
