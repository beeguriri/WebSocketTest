package com.example.websocket_test_v2.config;

import com.example.websocket_test_v2.handler.MessageHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;


@Component
@Slf4j
public class WebSocketClient extends Endpoint {

    @Getter
    private Session session = null;

    public WebSocketClient() {
        try{
            String url = "ws://localhost:8080/test";

            ClientEndpointConfig config = ClientEndpointConfig.Builder
                    .create()
                    .configurator(new WebSocketHeaderConfig("key1234"))
                    .build();

            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, config, new URI(url));

        } catch (Exception e){
            log.error("sendMessage Error >> {}", e.getMessage());
        }
    }

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        log.info("onOpen webSocketClient = {}", this);
        log.info("onOpen webSocketClient Session: {}", session);
        this.session = session;

        //세션에 커스텀 메시지 핸들러 추가
        session.addMessageHandler(new MessageHandler());
    }

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        try {
            log.info("onClose webSocketClient = {}", this);
            log.info("onClose webSocketClient Session: {}", session);
            session.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onError(Session session, Throwable throwable) {
        log.info("onError : {}", throwable.getMessage());
    }

    public void sendMessage(String message){
        try{
            log.info("Message to server >> {}", message);
            session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("sendMessage Error >> {}", e.getMessage());
        }
    }
}
