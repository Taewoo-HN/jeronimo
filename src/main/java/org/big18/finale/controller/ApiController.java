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
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class ApiController {

    private final RestTemplate restTemplate;
    private final RssService rssService;
    private final WebClient webClient;

    @Autowired
    public ApiController(RestTemplate restTemplate, RssService rssService, WebClient.Builder webClientBuilder ) {
        this.restTemplate = restTemplate;
        this.rssService = rssService;
        this.webClient = webClientBuilder.baseUrl("http://172.20.160.1:18000").build();
    }

    @GetMapping("/download-wordcloud")
    public ResponseEntity<InputStreamResource> downloadWordCloudImage() {
        String URL = "http://172.20.160.1:18000/download";  // FastAPI 엔드포인트 URL

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
        List<String> news_content_list = Arrays.asList(newsis.getNews_content());
        Map<String, Object> requestBody = Collections.singletonMap("content", news_content_list);
        Mono<String> fastApiResponse = webClient.post().uri("/summarizer")
                .body(BodyInserters.fromValue(requestBody))  // news_content 전송
                .retrieve()
                .bodyToMono(String.class);  // FastAPI로부터 응답을 받음

        fastApiResponse.doOnNext(response -> System.out.println("FastAPI 응답: " + response));

        // FastAPI로부터 받은 응답을 클라이언트에 반환
        return fastApiResponse.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

}
