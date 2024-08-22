package org.big18.finale.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stock;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime date;
    private Integer postOrder;
    private int count;
    private String formattedDate;

    public Post() {

    }
}