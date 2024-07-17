package org.big18.finale.controller;

import org.big18.finale.service.chatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public chatMessage handleMessage(chatMessage message) {
        return message;
    }
}
