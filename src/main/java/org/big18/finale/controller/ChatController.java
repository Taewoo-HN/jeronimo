package org.big18.finale.controller;

import org.big18.finale.DTO.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
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

}