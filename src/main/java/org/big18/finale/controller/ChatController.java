package org.big18.finale.controller;

import org.big18.finale.DTO.ChatMessage;
import org.big18.finale.service.api.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatbotService chatbotService;

    @Autowired
    public ChatController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/chatBot")
    public ResponseEntity<String> sendMessage(@RequestBody ChatMessage chatMessage) {

        // ChatMessage 객체를 FastAPI 서버로 전송하고 응답을 반환
        System.out.println(chatMessage.getContent());
        String chatbotResponse = chatbotService.sendMessageToChatbot(chatMessage);
        System.out.println(chatbotResponse);
        return ResponseEntity.ok("{\"response\": \"" +chatbotResponse + "\"}");
    }
}
