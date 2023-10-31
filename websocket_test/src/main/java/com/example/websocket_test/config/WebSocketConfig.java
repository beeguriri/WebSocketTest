package com.example.websocket_test.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
//@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketConfigurer {
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        // 메세지 구독 요청 url -> 메세지 받을 때
//        registry.enableSimpleBroker("/sub");
//        // 메세지 발행 요청 url -> 메세지 보낼 때
//        registry.setApplicationDestinationPrefixes("/pub");
//    }
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/ws") // stomp 설정
//                .setAllowedOrigins("*");
//    }

    private final WebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/siteId") //웹소켓 연결 주소
                .setAllowedOrigins("*"); //스프링 기본 정책 : same origin만 허용
    }
}
