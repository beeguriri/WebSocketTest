package com.example.websocket_test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Component
@ServerEndpoint(value = "/test", configurator = ServerEndpointConfigurator.class)
@Slf4j
public class WebSocketEndpoint {

    @OnOpen
    public void onOpen(Session session){
        log.info("클라이언트 연결 완료 : {}", session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("받은 메시지: {}", message);

        for(Session s : session.getOpenSessions()){
            s.getBasicRemote().sendText("message from " + session.getId() + " >>>" + message);
        }

        //클라이언트로 메시지 보내기
        //클라이언트로부터 받은 ping 메시지에 대한 응답으로 pong 메시지 보냄
//        if(message.equals("ping"))
//            session.getBasicRemote().sendText("pong");
//        else
//            session.getBasicRemote().sendText("서버에서 받은 메시지 재전송: " + message);
    }

    @OnClose
    public void onClose(Session session) {
        log.info("클라이언트 연결 종료 : {}", session);
    }

    @OnError
    public void onError(Throwable throwable) {
        log.info("error : {}", throwable.getMessage());
    }
}
