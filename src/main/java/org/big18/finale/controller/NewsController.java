//package org.big18.finale.controller;
//
//import org.big18.finale.entity.News;
//import org.big18.finale.service.NewsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/newspage")
//public class NewsController {
//
//    private final NewsService newsService;
//
//    @Autowired
//    public NewsController(NewsService newsService) {
//        this.newsService = newsService;
//    }
//
//    @GetMapping
//    public String getAllNews(Model model) {
//        List<News> newsList = newsService.getAllNews();
//        model.addAttribute("newsList", newsList);
//        return "news"; // news.mustache 또는 news.html을 반환
//    }
//
//    @GetMapping("/{id}")
//    public String getNewsById(@PathVariable Integer id, Model model) {
//        News news = newsService.getNewsById(id);
//        model.addAttribute("news", news);
//        return "newsDetail"; // newsDetail.mustache 또는 newsDetail.html을 반환
//    }
//}
