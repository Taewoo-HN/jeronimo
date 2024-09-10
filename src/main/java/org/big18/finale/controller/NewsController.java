package org.big18.finale.controller;


import jakarta.servlet.http.HttpSession;
import org.big18.finale.DTO.NewsItem;
import org.big18.finale.service.RssService;
import org.big18.finale.service.UserNameProvider;
import org.big18.finale.service.api.CloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

@Controller
public class NewsController {

    private final RssService rssService;
    private final UserNameProvider userNameProvider;
    private final CloudService cloudService;
    private final RestTemplate restTemplate;

    @Autowired
    public NewsController(RssService rssService, UserNameProvider userNameProvider, CloudService cloudService, RestTemplate restTemplate) {
        this.rssService = rssService;
        this.userNameProvider = userNameProvider;
        this.cloudService = cloudService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/news")
    public String showNews(@RequestParam(name = "query", defaultValue = "AI") String query,
                           @RequestParam(name = "page", defaultValue = "1") int page,
                           @RequestParam(name = "display", defaultValue = "20") int display,
                           Model model, HttpSession session) {
        int start = (page - 1) * display + 1;
        List<NewsItem> newsItems = rssService.fetchAllNewsItems(query, display, start);
        model.addAttribute("newsItems", newsItems);
        model.addAttribute("searchQuery", query);
        model.addAttribute("currentPage", page);
        cloudService.sendKeywordNewsTitles(query);
        userNameProvider.setUserAttributes(session, model);

        return "newspage";
    }

    @GetMapping("/download-wordcloud")
    @ResponseBody
    public ResponseEntity<InputStreamResource> downloadWordCloudImage() {
        String url = "http://localhost:8000/download";  // FastAPI 엔드포인트 URL

        // FastAPI로 GET 요청 보내기 (이미지 다운로드)
        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, null, byte[].class);

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
    @ResponseBody
    public ResponseEntity<String> summerizer(@RequestBody Map<String, String> params ) {
        String URL = "http://localhost:8000/summarizer";
        NewsItem news = rssService.fetchNewsItemById(Long.valueOf(params.get("news_id")));

        String content = news.getNews_content();
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> fastApiRequest = new HashMap<>();
        fastApiRequest.put("content", content);

        // FastAPI로 요청 보내고 응답 받기
        ResponseEntity<String> response = restTemplate.postForEntity(URL, fastApiRequest, String.class);

        // FastAPI로부터 받은 요약 결과 반환
        return ResponseEntity.ok(response.getBody());
    }

}

