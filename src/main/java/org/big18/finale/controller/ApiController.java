package org.big18.finale.controller;

import org.big18.finale.DTO.NewsItem;
import org.big18.finale.service.RssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

import static org.big18.finale.config.ServerConfiguration.APIURL;

@RestController
public class ApiController {

    private final RestTemplate restTemplate;
    private final RssService rssService;


    @Autowired
    public ApiController(RestTemplate restTemplate, RssService rssService) {
        this.restTemplate = restTemplate;
        this.rssService = rssService;
    }

    @GetMapping("/download-wordcloud")
    public ResponseEntity<InputStreamResource> downloadWordCloudImage() {
        String URL = APIURL+"/download";  // FastAPI 엔드포인트 URL

        // FastAPI로 GET 요청 보내기 (이미지 다운로드)
        ResponseEntity<byte[]> response = restTemplate.exchange(URL, HttpMethod.GET, null, byte[].class);

        // 응답이 성공적이면 이미지 데이터를 클라이언트로 전송
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            byte[] imageBytes = response.getBody();
            InputStream inputStream = new ByteArrayInputStream(imageBytes);
            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

            // 이미지 데이터를 클라이언트에게 반환
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);  // 이미지 타입 설정
            headers.setContentLength(imageBytes.length);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(inputStreamResource);
        }
        // 에러 발생 시 에러 메시지 반환
        return ResponseEntity.status(500).build();
    }


    @PostMapping("/summarize")
    public ResponseEntity<String> summarizer(@RequestBody Map<String, Object> params) {
        NewsItem newsis = rssService.fetchNewsItemById(Long.valueOf((String) params.get("news_id")));
        String news_content = newsis.getNews_content();

        String URL = APIURL + "/keyword";
        // 정규식으로 특수문자 제거
        String regexContents = news_content.replaceAll("[^a-zA-Z0-9가-힣 ]", "");

        if (regexContents.length() > 1000) {
            news_content = regexContents.substring(0, 999);
        }

        Map<String, String> requestBody = Collections.singletonMap("news", news_content);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        try {
            // FastAPI 서버로 POST 요청
            ResponseEntity<String> response = restTemplate.postForEntity(URL, entity, String.class);
            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        } catch (Exception e) {
            // 에러 발생 시 응답 처리
            return new ResponseEntity<>("FastAPI 요청 중 오류 발생: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/extract")
    public ResponseEntity<String> extract(@RequestBody Map<String, Object> params) {
        NewsItem newsis = rssService.fetchNewsItemById(Long.valueOf((String) params.get("news_id")));
        String news_content = newsis.getNews_content();
        String URL = APIURL + "/summarizer";
        if (news_content.length() > 800) {
            news_content = news_content.substring(0, 799);
        }

        Map<String, String> requestBody = Collections.singletonMap("content", news_content);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        try {
            // FastAPI 서버로 POST 요청
            ResponseEntity<String> response = restTemplate.postForEntity(URL, entity, String.class);
            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        } catch (Exception e) {
            // 에러 발생 시 응답 처리
            return new ResponseEntity<>("FastAPI 요청 중 오류 발생: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
