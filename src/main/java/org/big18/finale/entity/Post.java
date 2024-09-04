package org.big18.finale.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Transient
    private String formattedDate;

    public Post() {
        this.date = LocalDateTime.now();
    }

    public String getFormattedDate() {
        if (formattedDate == null && date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            formattedDate = date.format(formatter);
        }
        return formattedDate;
    }
}