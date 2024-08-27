package org.big18.finale.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.big18.finale.entity.NewsItem;
import org.big18.finale.service.RssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("/")
public class MainController {

    @Autowired
    private RssService rssService;


    @GetMapping("/main")
    public String home() {
        return "index";
    }

    @GetMapping("/detail")
    public String detail() {
        return "detail";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrf != null) {
            model.addAttribute("_csrf", csrf);
        }
        return "login";
    }

    @GetMapping("/news")
    public String showNews(@RequestParam(name = "query", defaultValue = "삼성전자") String query, Model model) {
        List<NewsItem> newsItems = rssService.fetchAllNewsItems(query);
        System.out.println("Fetched " + newsItems.size() + " news items for query: " + query);
        for (NewsItem item : newsItems) {
            System.out.println("Title: " + item.getTitle() + ", Excerpt: " + item.getExcerpt());
            System.out.println("Summary: " + item.getSummary());
            System.out.println("Keywords: " + String.join(", ", item.getKeywords()));
            System.out.println("Related Themes: " + item.getRelatedThemes().stream()
                    .map(theme -> theme.getName())
                    .collect(java.util.stream.Collectors.joining(", ")));
        }
        model.addAttribute("newsItems", newsItems);
        model.addAttribute("searchQuery", query);
        return "newspage";
    }

    @GetMapping("/bbs")
    public String board() {
        return "bbs";
    }

}
