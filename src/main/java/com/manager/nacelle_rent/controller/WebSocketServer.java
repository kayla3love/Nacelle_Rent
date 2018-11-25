package com.manager.nacelle_rent.controller;

import com.alibaba.fastjson.JSONObject;
import com.manager.nacelle_rent.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket/{sid}")
@Component
public class WebSocketServer {

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //此处是解决无法注入的关键
    private static ApplicationContext applicationContext;
    private static Logger logger=Logger.getLogger(LoginController.class); // 获取logger实例

    //你要注入的service或者dao
    private UserService userService;
    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketServer.applicationContext = applicationContext;
    }

    //接收sid
    private String sid="";
    /**连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("sid") String sid) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        this.sid = sid;
        addOnlineCount();           //在线数加1
    }

    /**连接关闭调用的方法*/
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("收到来自窗口"+sid+"的信息:"+ message);
        //群发消息
        switch (this.sid){
            case "superWebAdmin":
                JSONObject jsonObject = JSONObject.parseObject(message);
                //此处是解决无法注入的关键
                userService = applicationContext.getBean(UserService.class);
                userService.handleRegister(jsonObject.getString("userId"),
                        jsonObject.getString("handleResult"));
        }
        for (WebSocketServer item : webSocketSet) {
//            try {
//                //item.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        //log.error("发生错误");
        error.printStackTrace();
    }

    /**实现服务器主动推送*/
    public void sendMessage(String jsonMessage) throws IOException {
        this.session.getBasicRemote().sendText(jsonMessage);
    }

    /**群发自定义消息*/
    public static void sendInfo(String message,@PathParam("sid") String sid) throws IOException {
        logger.info("推送消息到窗口"+sid+"，推送内容:"+message);
        for (WebSocketServer item : webSocketSet) {
            try{
                //这里可以设定只推送给这个sid的，为null则全部推送
                if(sid==null) {
                    item.sendMessage(message);
                }else if(item.sid.equals(sid)){
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}

