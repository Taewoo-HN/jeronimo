package org.big18.finale.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class NewsItem {
    private String title;
    private Date date;
    private String excerpt;
    private String content;
    private String imageUrl;
    private String source;
    private String summary;
    private List<String> keywords;
    private List<StockTheme> relatedThemes;

    public NewsItem(String title, Date date, String excerpt, String content, String imageUrl, String source,
                    String summary, List<String> keywords, List<StockTheme> relatedThemes) {
        this.title = title;
        this.date = date;
        this.excerpt = excerpt;
        this.content = content;
        this.imageUrl = imageUrl;
        this.source = source;
        this.summary = summary;
        this.keywords = keywords;
        this.relatedThemes = relatedThemes;
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", excerpt='" + excerpt + '\'' +
                ", content='" + content + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", source='" + source + '\'' +
                ", summary='" + summary + '\'' +
                ", keywords=" + keywords +
                ", relatedThemes=" + relatedThemes +
                '}';
    }
}