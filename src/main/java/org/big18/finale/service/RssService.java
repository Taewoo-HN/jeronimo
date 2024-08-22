package org.big18.finale.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.big18.finale.entity.NewsItem;
import org.big18.finale.entity.StockTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RssService {

    @Value("${naver.api.client.id}")
    private String clientId;

    @Value("${naver.api.client.secret}")
    private String clientSecret;

    @Autowired
    private StockThemeService stockThemeService;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<NewsItem> fetchAllNewsItems(String query) {
        String apiUrl = "https://openapi.naver.com/v1/search/news.json?query=" + query;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                JsonNode root = objectMapper.readTree(response.getBody());
                JsonNode items = root.path("items");
                List<NewsItem> newsItems = new ArrayList<>();

                for (JsonNode item : items) {
                    NewsItem newsItem = convertToNewsItem(item);
                    newsItems.add(newsItem);
                }

                return newsItems;
            } catch (Exception e) {
                System.err.println("Error parsing JSON response: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return Collections.emptyList();
    }

    private NewsItem convertToNewsItem(JsonNode item) {
        String title = item.path("title").asText();
        String description = item.path("description").asText();
        String link = item.path("link").asText();
        Date pubDate = parseDate(item.path("pubDate").asText());

        String content = fetchFullContent(link); // 전체 내용을 가져오는 메소드 (구현 필요)
        String summary = summarizeContent(content);
        List<String> keywords = extractKeywords(content);
        List<StockTheme> relatedThemes = stockThemeService.findThemesByKeywords(keywords);

        return new NewsItem(
                title,
                pubDate,
                description,
                content,
                null, // imageUrl 대신 null 사용
                link,
                summary,
                keywords,
                relatedThemes
        );
    }

    private Date parseDate(String dateString) {
        try {
            return new Date(dateString);
        } catch (Exception e) {
            System.err.println("Error parsing date: " + dateString);
            return new Date();
        }
    }

    /*private String fetchFullContent(String url) {
        // 여기에 전체 내용을 가져오는 로직을 구현해야 합니다.
        // 예를 들어, JSoup 라이브러리를 사용하여 웹 스크래핑을 할 수 있습니다.
        // 지금은 간단히 URL만 반환하도록 하겠습니다.
        return "Full content from: " + url;
    }*/

    private String summarizeContent(String content) {
        // 실제 요약 로직을 여기에 구현합니다.
        // 이 예제에서는 간단히 첫 100자를 반환합니다.
        return content.length() > 100 ? content.substring(0, 100) + "..." : content;
    }

    private List<String> extractKeywords(String content) {
        // 실제 키워드 추출 로직을 여기에 구현합니다.
        // 이 예제에서는 간단히 가장 빈번한 단어 5개를 반환합니다.
        String[] words = content.split("\\s+");
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }
        return wordFrequency.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private String fetchFullContent(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements contentElements = doc.select("div#articleBodyContents, div#articleBody, div.article_body, div.news_content");
            if (!contentElements.isEmpty()) {
                return contentElements.first().text();
            } else {
                return "컨텐츠를 찾을 수 없습니다.";
            }
        } catch (IOException e) {
            System.err.println("Error fetching content from URL: " + url);
            e.printStackTrace();
            return "컨텐츠를 가져오는 중 오류가 발생했습니다.";
        }
    }
}