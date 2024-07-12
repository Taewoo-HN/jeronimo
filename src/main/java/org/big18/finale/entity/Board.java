package org.big18.finale.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "board", schema = "stock_test")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id", nullable = false)
    private Integer id;

    @Column(name = "nickname", length = 10)
    private String nickname;

    @Column(name = "stock_id", length = 12)
    private String stockId;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "content", length = 10000)
    private String content;

    @ColumnDefault("sysdate()")
    @Column(name = "reg_date")
    private LocalDate regDate;

    @ColumnDefault("sysdate()")
    @Column(name = "mod_date")
    private LocalDate modDate;

}