package org.big18.finale.controller;

import jakarta.servlet.http.HttpSession;
import org.big18.finale.DTO.StockDisplayData;
import org.big18.finale.entity.News;
import org.big18.finale.entity.NewsItem;
import org.big18.finale.service.NewsService;
import org.big18.finale.service.RssService;
import org.big18.finale.service.StockService;
import org.big18.finale.service.UserNameProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class MainController {

    private final RssService rssService;
    private final NewsService newsService;
    private final StockService stockService;
    private final UserNameProvider userNameProvider;

    @Autowired
    public MainController(RssService rssService, NewsService newsService, StockService stockService, HttpSession session, UserNameProvider userNameProvider) {
        this.rssService = rssService;
        this.newsService = newsService;
        this.stockService = stockService;
        this.userNameProvider = userNameProvider;
    }

    @GetMapping("/")
    public String redirectToMain() {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String home(Model model, HttpSession session) {
        List<News> newsList = newsService.getAllNews();
        Collections.shuffle(newsList);
        newsList = newsList.subList(0, 9);

        List<StockDisplayData> stockDataList = stockService.getAllStockData();
        model.addAttribute("stockDataList", stockDataList);
        model.addAttribute("newsList", newsList);

        userNameProvider.setUserAttributes(session, model);

        return "index";
    }

    @GetMapping("/detail/{code}")
    public String getStockDetail(@PathVariable String code, Model model, HttpSession session) {

        userNameProvider.setUserAttributes(session, model);

        return "detail";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/news")
    public String showNews(@RequestParam(name = "query", defaultValue = "코스피") String query,
                           @RequestParam(name = "page", defaultValue = "1") int page,
                           @RequestParam(name = "display", defaultValue = "20") int display,
                           Model model, HttpSession session) {
        int start = (page - 1) * display + 1;
        List<NewsItem> newsItems = rssService.fetchAllNewsItems(query, display, start);
        model.addAttribute("newsItems", newsItems);
        model.addAttribute("searchQuery", query);
        model.addAttribute("currentPage", page);

        userNameProvider.setUserAttributes(session, model);


        return "newspage";
    }


    @GetMapping("/chatting")
    public String chatWindow() {

        return "chatbot_window";
    }

}


