package org.big18.finale.controller;

import org.big18.finale.DTO.NewsItem;
import org.big18.finale.service.RssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

import static org.big18.finale.config.ServerConfiguration.APIURL;

@RestController
public class ApiController {

    private final RestTemplate restTemplate;
    private final RssService rssService;
    private final WebClient webClient;


    @Autowired
    public ApiController(RestTemplate restTemplate, RssService rssService, WebClient.Builder webClientBuilder) {
        this.restTemplate = restTemplate;
        this.rssService = rssService;
        this.webClient = webClientBuilder.baseUrl(APIURL).build();
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
    public Mono<ResponseEntity<String>> summerizer(@RequestBody Map<String, Object> params) {
        NewsItem newsis = rssService.fetchNewsItemById(Long.valueOf((String) params.get("news_id")));
        String news_content = newsis.getNews_content();

        // 정규식으로 특수문자 제거
        String regexContents = news_content.replaceAll("[^a-zA-Z0-9가-힣 ]", "");
        System.out.println(regexContents);

        Map<String, String> requestBody = Collections.singletonMap("news", regexContents);
        Mono<String> fastApiResponse = webClient.post().uri("/keyword")
                .body(BodyInserters.fromValue(requestBody))  // news_content 전송
                .retrieve()
                .bodyToMono(String.class);  // FastAPI로부터 응답을 받음


        // FastAPI로부터 받은 응답을 클라이언트에 반환
        return fastApiResponse.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .onErrorResume(e -> {
                    // 에러 발생 시 응답 처리
                    return Mono.just(new ResponseEntity<>("FastAPI 요청 중 오류 발생: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
                }).defaultIfEmpty(new ResponseEntity<>("FastAPI에서 유효한 응답이 없습니다.", HttpStatus.NO_CONTENT));
    }

    @PostMapping("/extract")
    public Mono<ResponseEntity<String>> extract(@RequestBody Map<String, Object> params) {
        NewsItem newsis = rssService.fetchNewsItemById(Long.valueOf((String) params.get("news_id")));
        String news_content = newsis.getNews_content();
        String regexContents = news_content.replaceAll("[^a-zA-Z0-9가-힣 ]", "");
        if (regexContents.length() > 800) {
            news_content = regexContents.substring(0, 799);
        }

        Map<String, String> requestBody = Collections.singletonMap("content", news_content);
        Mono<String> fastApiResponse = webClient.post().uri("/summarizer")
                .body(BodyInserters.fromValue(requestBody))  // news_content 전송
                .retrieve()
                .bodyToMono(String.class);

        return fastApiResponse.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .onErrorResume(e -> {
                    // 에러 발생 시 응답 처리
                    return Mono.just(new ResponseEntity<>("FastAPI 요청 중 오류 발생: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
                }).defaultIfEmpty(new ResponseEntity<>("FastAPI에서 유효한 응답이 없습니다.", HttpStatus.NO_CONTENT));

    }
}
