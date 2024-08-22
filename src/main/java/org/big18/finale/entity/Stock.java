package org.big18.finale.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Stock {
    @Id
    private String code;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id")
    private StockTheme theme;

    // Constructors
    public Stock() {}

    public Stock(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", theme=" + (theme != null ? theme.getName() : "null") +
                '}';
    }
}