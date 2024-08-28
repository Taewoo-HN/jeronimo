package org.big18.finale.controller;

import org.big18.finale.service.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @Autowired
    private final SimpMessagingTemplate template;

    @Autowired
    public ChatController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage handleMessage(@Payload ChatMessage message) {
        // 들어오는 메시지 처리
        System.out.println("받은 메시지: " + message.getContent());

        // 응답 메시지 생성
        return message;
    }

}