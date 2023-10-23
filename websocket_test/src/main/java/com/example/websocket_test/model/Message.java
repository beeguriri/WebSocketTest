package com.example.websocket_test.model;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String type;
    private String sender;
    private String channelId;
    private String data;

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void newContent() {
        this.type = "new";
    }

    public void closeConnect() {
        this.type = "close";
    }
}
