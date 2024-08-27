package org.big18.finale.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id", nullable = false)
    private Integer id;

    @Column(name = "news_title", length = 100)
    private String newsTitle;

    @Column(name = "news_content", length = 10000)
    private String newsContent;

    @Column(name = "press_co", length = 10)
    private String pressCo;

    @Column(name= "summary", length = 500)
    private String summary;

    @Column(name = "stock_id", length = 12)
    private String stockId;

    @Column(name = "address", length = 100)
    private String address;
}