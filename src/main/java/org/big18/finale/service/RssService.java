package org.big18.finale.service;

import org.big18.finale.DTO.NewsItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RssService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<NewsItem> fetchAllNewsItems(String query, int display, int start) {
        String sql = "SELECT * FROM news WHERE news_title LIKE ? LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + query + "%", display, start - 1},
                (rs, rowNum) -> new NewsItem(
                        rs.getLong("news_id"),
                        rs.getString("news_title"),
                        rs.getString("news_content"),
                        rs.getString("press_co"),
                        rs.getLong("stock_id"),
                        rs.getString("summary"),
                        rs.getString("address")
                )
        );
    }

    public NewsItem fetchNewsItemById(Long newsId) {
        String sql = "SELECT * FROM news WHERE news_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{newsId},
                (rs, rowNum) -> new NewsItem(
                        rs.getLong("news_id"),
                        rs.getString("news_title"),
                        rs.getString("news_content"),
                        rs.getString("press_co"),
                        rs.getLong("stock_id"),
                        rs.getString("summary"),
                        rs.getString("address")
                )
        );
    }

    public int getTotalNewsCount(String query) {
        String sql = "SELECT COUNT(*) FROM news WHERE news_title LIKE ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{"%" + query + "%"}, Integer.class);
    }
}