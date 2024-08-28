package org.big18.finale.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "news")
public class News {
    @Id
    @Column(name = "news_id", nullable = false)
    private Integer id;

    @Column(name = "news_title", length = 100)
    private String newsTitle;

    @Column(name = "news_content", length = 10000)
    private String newsContent;

    @Column(name = "press_co", length = 10)
    private String pressCo;

    @Column(name = "stock_id", length = 12)
    private String stockId;

    @Column(name = "summary", nullable = false, length = 500)
    private String summary;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

}