package com.example.websocket_test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> map = new HashMap<>();

    //웹소켓이 연결될 때 생기는 연결정보를 담고 있는 객체
    //핸들러에서 웹소켓 통신을 처리하기 위해 컬렉션에 담아서 처리
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("sessionID = {}", session.getId());
        map.put(session.getId(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("payload={}", message.getPayload());
        for(WebSocketSession s: map.values()) {
            s.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //연결 해제 관련
        map.remove(session.getId());
    }
}
