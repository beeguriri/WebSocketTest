package com.example.websocket_test.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

//    private final SimpMessageSendingOperations simpMessageSendingOperations;

    /*
    /sub/test/1234          - 구독(siteId:1234)
    /pub/test               - 메시지 발행
    */

//    @MessageMapping("/test")
//    public void message(Message message) {
//        simpMessageSendingOperations.convertAndSend("/sub/test/"+message.getChannelId(), message);
//    }

    @PostMapping("/api/MockPMS")
    public ResponseEntity<PMSResourceData> sendData(@RequestBody TestParam testParam) {
        log.info("testParam={}", testParam);
        PMSResourceData pmsResourceData = new PMSResourceData();
        pmsResourceData.setSiteId("12345678");
        pmsResourceData.setResourceId(testParam.resourceId);
        pmsResourceData.setEquipment(testParam.equipment);
        pmsResourceData.setPower("1");
        pmsResourceData.setVoltage("2");
        pmsResourceData.setCurrent("3");
        log.info("pmsResourceData={}", pmsResourceData);
        return ResponseEntity.ok().body(pmsResourceData);
    }

    @Data
    static class PMSResourceData {
        private String siteId;
        private String resourceId;
        private String equipment;
        private String power;
        private String voltage;
        private String current;
    }

    @Data
    static class TestParam {
        private String resourceId;
        private String equipment;
    }
}
