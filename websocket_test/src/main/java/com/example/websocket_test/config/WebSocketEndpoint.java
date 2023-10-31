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

        //클라이언트로 메시지 보내기
        session.getBasicRemote().sendText("서버에서 받은 메시지: " + message);
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
