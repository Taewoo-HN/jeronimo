package org.big18.finale.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class StockTheme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ElementCollection
    private List<String> keywords = new ArrayList<>();

    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stock> stocks = new ArrayList<>();

    // Constructors
    public StockTheme() {}

    public StockTheme(String name) {
        this.name = name;
    }

    // Helper methods
    public void addKeyword(String keyword) {
        this.keywords.add(keyword);
    }

    public void addStock(Stock stock) {
        this.stocks.add(stock);
        stock.setTheme(this);
    }

    public void removeStock(Stock stock) {
        this.stocks.remove(stock);
        stock.setTheme(null);
    }

    @Override
    public String toString() {
        return "StockTheme{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", keywords=" + keywords +
                ", stocks=" + stocks.size() +
                '}';
    }
}