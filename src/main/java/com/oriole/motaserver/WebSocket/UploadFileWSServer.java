package com.oriole.motaserver.WebSocket;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author : admin</br>
 * @DESC : <p>注解{@link ServerEndpoint}声明websocket 服务端</p></br>
 */
@Component
@ServerEndpoint("/FileUpload/{ClientID}")
public class UploadFileWSServer {

    // 在线人数 线程安全
    private static int onlineCount = 0;

    // 连接集合 userId => server 键值对 线程安全
    static public final ConcurrentMap<String, UploadFileWSServer> map = new ConcurrentHashMap<>();

    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private String ClientID;


    /**
     * @param session websocket连接sesson
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("ClientID") String ClientID) {

        this.ClientID=ClientID;
        this.session = session;
        System.out.println(ClientID+"-------"+this.session.getId());

        //针对一个ClientID只能有一个链接
        if(map.get(this.ClientID)!=null){
            // 移除连接
            map.remove(this.ClientID);
            // 连接数-1
            subOnlineCount();
        }

        // 将连接session对象存入map
        map.put(this.ClientID, this);

        // 连接数+1
        addOnlineCount();
    }


    /**
     * <p>{@link OnClose} 关闭连接</p>
     */
    @OnClose
    public void onClose() {

        /**
         * 获取当前连接信息 {@code CommonConstant.USER_LOGIN_SESSION} 为Http session 名
         */

        // 移除连接
        map.remove(this.ClientID);

        // 连接数-1
        subOnlineCount();
    }

    /**
     * <p>{@link OnError} websocket系统异常处理</p>
     *
     * @param e 异常
     */
    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    /**
     * <p>系统主动推送消息 这是个静态方法在web启动后可在程序的其他合适的地方和时间调用，这就实现了系统的主动推送</p>
     *
     * @param ClientID 客户端ID
     * @param Msg 发送JSON
     */
    static public void pushBySys(String ClientID, String Msg) {

        //TODO 定点推送
        UploadFileWSServer uploadFileWSServer=map.get(ClientID);
        try {
            if(uploadFileWSServer!=null){
                uploadFileWSServer.session.getBasicRemote().sendText(Msg);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    // 获取连接数
    private static synchronized int getOnlineCount() {
        return UploadFileWSServer.onlineCount;
    }

    // 增加连接数
    private static synchronized void addOnlineCount() {
        UploadFileWSServer.onlineCount++;
    }

    // 减少连接数
    private static synchronized void subOnlineCount() {
        UploadFileWSServer.onlineCount--;
    }

}
