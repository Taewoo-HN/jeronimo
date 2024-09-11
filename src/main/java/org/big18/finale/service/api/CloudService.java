package org.big18.finale.service.api;

import org.big18.finale.DTO.NewsRequest;
import org.big18.finale.entity.News;
import org.big18.finale.repository.NewsRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CloudService {

    private final NewsRepository newsRepository;
    private final RestTemplate restTemplate;

    public CloudService(NewsRepository newsRepository, RestTemplate restTemplate) {
        this.newsRepository = newsRepository;
        this.restTemplate = restTemplate;
    }

    // 키워드를 포함한 뉴스 제목을 FastAPI로 전송하는 메서드
    public void sendKeywordNewsTitles(String keyword) {

        // 1. 키워드에 맞는 뉴스 목록 가져오기
        List<News> newsList = getKeywordNews(keyword);

        // 2. 뉴스 목록에서 제목만 추출
        List<String> newsTitles = newsList.stream()
                .map(News::getNewsTitle)
                .collect(Collectors.toList());

        // 3. 추출한 제목을 FastAPI로 전송
        sendNewsTitles(newsTitles, keyword);
    }

    // 키워드를 포함한 뉴스 조회 메서드
    public List<News> getKeywordNews(String keyword) {
       return newsRepository.findByNewsTitleContaining(keyword);
    }

    public void sendNewsTitles(List<String> newsTitles, String keyword) {
        String url = "http://172.20.160.1:18000/news";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 키워드와 제목 리스트를 포함한 NewsRequest 객체 생성
        NewsRequest newsRequest = new NewsRequest(newsTitles, keyword);

        // NewsRequest 객체를 본문으로 설정
        HttpEntity<NewsRequest> request = new HttpEntity<>(newsRequest, headers);

        // POST 요청 보내기
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
    }


}

