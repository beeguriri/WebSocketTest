package com.example.websocket_test_v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebsocketTestV2Application {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketTestV2Application.class, args);
    }

}
