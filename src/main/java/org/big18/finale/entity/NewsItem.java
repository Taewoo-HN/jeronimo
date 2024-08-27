package org.big18.finale.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsItem {
    private Long news_id;
    private String news_title;
    private String news_content;
    private String press_co;
    private Long stock_id;
    private String summary;
    private String address;

    @Override
    public String toString() {
        return "NewsItem{" +
                "news_id=" + news_id +
                ", news_title='" + news_title + '\'' +
                ", news_content='" + news_content + '\'' +
                ", press_co='" + press_co + '\'' +
                ", stock_id=" + stock_id +
                ", summary='" + summary + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}