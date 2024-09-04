package org.big18.finale.controller;

import org.big18.finale.DTO.ChatMessage;
import org.big18.finale.DTO.OutputMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@RestController
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ChatController(SimpMessagingTemplate template) {
        this.messagingTemplate = template;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage handleMessage(@Payload ChatMessage message) {
        return message;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        // 모든 클라이언트에 환영 메시지 전송
        messagingTemplate.convertAndSend("/topic/messages", new OutputMessage("Server", "환영합니다!"));
    }
}