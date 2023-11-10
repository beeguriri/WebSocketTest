package com.example.websocket_test_v2.handler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageHandler implements javax.websocket.MessageHandler.Whole<String> {
    @Override
    public void onMessage(String message) {
        log.info("Message from server >> {}", message);
    }
}
