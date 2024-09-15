package org.big18.finale.service.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.big18.finale.DTO.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;


@Service
public class ChatbotService {

    private final RestTemplate restTemplate;

    @Autowired
    public ChatbotService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String sendMessageToChatbot(ChatMessage message) {
        String URL = "http://172.29.240.1:8000"+"/chatbot";  // FastAPI 서버 주소
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ChatMessage> entity = new HttpEntity<>(message, headers);

        try {
            // FastAPI 서버로 POST 요청
            ResponseEntity<String> response = restTemplate.postForEntity(URL, entity, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response.getBody());

            return jsonNode.toString();

        } catch (HttpClientErrorException e) {
            // 클라이언트 에러 (4xx 응답) 처리
            System.err.println("Client error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return "Error: Unable to process the request. Client error occurred.";

        } catch (HttpServerErrorException e) {
            // 서버 에러 (5xx 응답) 처리
            System.err.println("Server error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return "Error: Server error occurred. Please try again later.";

        } catch (ResourceAccessException e) {
            // 네트워크 또는 서버 연결 문제 처리
            System.err.println("Network error: " + e.getMessage());
            return "Error: Unable to connect to the chatbot server. Please check your network connection.";

        } catch (Exception e) {
            // 일반적인 예외 처리
            System.err.println("An unexpected error occurred: " + e.getMessage());
            return "Error: An unexpected error occurred. Please try again.";
        }
    }
}
