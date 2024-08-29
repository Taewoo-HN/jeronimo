package org.big18.finale.controller;

import org.big18.finale.entity.News;
import org.big18.finale.entity.NewsItem;
import org.big18.finale.service.NewsService;
import org.big18.finale.service.RssService;
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

    @Autowired
    private RssService rssService;
    @Autowired
    private NewsService newsService;

    @GetMapping("/")
    public String redirectToMain() {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String home(Model model) {
        List<News> newsList = newsService.getAllNews();
        Collections.shuffle(newsList);

        if (newsList.size() > 10) {
            newsList = newsList.subList(0, 9);
        }
        model.addAttribute("newsList", newsList);
        return "index";
    }

    @GetMapping("/detail/{code}")
    public String getStockDetail(@PathVariable String code, Model model) {
        model.addAttribute("stockCode", code);
        return "detail";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/news")
    public String showNews(@RequestParam(name = "query", defaultValue = "코스피" ) String query,
                           @RequestParam(name = "page", defaultValue = "1") int page,
                           @RequestParam(name = "display", defaultValue = "20") int display,
                           Model model) {
        int start = (page - 1) * display + 1;
        List<NewsItem> newsItems = rssService.fetchAllNewsItems(query, display, start);
        model.addAttribute("newsItems", newsItems);
        model.addAttribute("searchQuery", query);
        model.addAttribute("currentPage", page);
        return "newspage";
    }

    @GetMapping("/bbs")
    public String board() {
        return "bbs";
    }

    @GetMapping("/chatting")
    public String chatWindow(){
        return "chatbot_window";
    }

}


