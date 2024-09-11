package org.big18.finale.controller;


import jakarta.servlet.http.HttpSession;
import org.big18.finale.DTO.NewsItem;
import org.big18.finale.service.RssService;
import org.big18.finale.service.UserNameProvider;
import org.big18.finale.service.api.CloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class NewsController {

    private final RssService rssService;
    private final UserNameProvider userNameProvider;
    private final CloudService cloudService;


    @Autowired
    public NewsController(RssService rssService, UserNameProvider userNameProvider, CloudService cloudService) {
        this.rssService = rssService;
        this.userNameProvider = userNameProvider;
        this.cloudService = cloudService;
;
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


}

