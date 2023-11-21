package com.example.websocket_test_v3.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class WebsocketConnectionManager {

    private WebSocketSession webSocketSession = null;
    private final String url = "ws://localhost:8080/test";

    /**
     * 서버와 웹소켓 연결하는 메서드
     */
    public void connection() {

        //기존에 웹소켓 연결 되어 있으면 세션 종료하기
        if(isConnected()) {
            try {
                webSocketSession.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //API-Key를 header에 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-api-key", "12345678");

        //최초 연결 시 API-Key를 header에 담아 전달하면서 websocket을 연결한다.
        try {
            WebSocketSession temp  = new StandardWebSocketClient().doHandshake(new TextWebSocketHandler() {
                @Override
                public void afterConnectionEstablished(WebSocketSession session) {
                    //연결 후 할 일
                }

                @Override
                protected void handleTextMessage(WebSocketSession session, TextMessage message) {
                    //서버로 부터 메시지 수신
                    String text = message.getPayload();
                    log.info("receive from server : {}" , text);
                    //서버로부터 수신된 메시지를 별도로 처리하려면 여기에 추가하면 됨
                }

            }, new WebSocketHttpHeaders(headers), URI.create(url)).get();

            //ping/pong 메시지를 통해 서버와 연결여부 확인 하고 현재 세션으로 websocket session 갱신
            webSocketSession = temp;


        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 외부에서 서버에 메시지 보내는 메서드
     */
    public void sendMessage(String jsonStr) {
        try {
            webSocketSession.sendMessage(new TextMessage(jsonStr));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 외부에서 websocket 연결 되어있는 지 확인하는 메서드
     */
    public boolean isConnected() {
        if(webSocketSession == null)
            return false;

        return webSocketSession.isOpen();
    }
}
