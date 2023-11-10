package com.example.websocket_test_v2.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.HandshakeResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
public class WebSocketHeaderConfig extends ClientEndpointConfig.Configurator {

    private String key;

    @Override
    public void beforeRequest(Map<String, List<String>> headers) {
        log.info("beforeRequest>>");
        headers.put("key", Collections.singletonList(key));
    }

    @Override
    public void afterResponse(HandshakeResponse handshakeResponse) {
        log.info("afterResponse>> {}", handshakeResponse);
    }
}
