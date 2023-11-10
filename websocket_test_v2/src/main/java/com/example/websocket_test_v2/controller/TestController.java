package com.example.websocket_test_v2.controller;

import com.example.websocket_test_v2.config.WebSocketClient;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final WebSocketClient webSocketClient;
    static int idx = 1;

    @Scheduled(fixedDelay = 10, initialDelay = 10, timeUnit = TimeUnit.SECONDS)
    private void sendData() {
        if (webSocketClient.getSession() != null)
            webSocketClient.sendMessage("hi server " + idx++);
    }
}
