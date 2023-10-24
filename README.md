# WebSocketTest
SpringBoot, WebSocket, STOMP 테스트

### STOMP
- pub에서 메시지를 발행하면
- sub에서 channel ID가 같은 경우 메시지 수신

### Test site
- https://apic.app/online/#/tester
- pub에서 메시지 발신
![](images/pub.png)
- channel ID가 같을 경우 메시지 수신
![](images/sub_ok.png)
- channel ID가 다를 경우 메시지 수신 안 함
![](images/sub_no.png)
