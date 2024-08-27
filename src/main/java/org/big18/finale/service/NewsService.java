package org.big18.finale.service;

import org.big18.finale.entity.News;
import org.big18.finale.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public News getNewsById(Integer newsId) {
        Optional<News> optionalNews = newsRepository.findById(newsId);
        return optionalNews.orElseThrow(() -> new RuntimeException("News not found with id: " + newsId));
    }

    public News createNews(News news) {
        return newsRepository.save(news);
    }

    public News updateNews(Integer newsId, News newsDetails) {
        News news = getNewsById(newsId);

        news.setNewsTitle(newsDetails.getNewsTitle());
        news.setNewsContent(newsDetails.getNewsContent());
        news.setPressCo(newsDetails.getPressCo());
        news.setSummary(newsDetails.getSummary());
        news.setAddress(newsDetails.getAddress());

        return newsRepository.save(news);
    }

    public void deleteNews(Integer newsId) {
        News news = getNewsById(newsId);
        newsRepository.delete(news);
    }
}