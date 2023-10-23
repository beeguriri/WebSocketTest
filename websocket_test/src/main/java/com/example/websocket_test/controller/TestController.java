package com.example.websocket_test.controller;

import com.example.websocket_test.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;

    /*
    /sub/test/1234          - 구독(siteId:1234)
    /pub/test               - 메시지 발행
    */

    @MessageMapping("/hello")
    public void message(Message message) {
        simpMessageSendingOperations.convertAndSend("/sub/channel/"+message.getChannelId(), message);
    }

}
