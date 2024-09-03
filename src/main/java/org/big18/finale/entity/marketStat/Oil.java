package org.big18.finale.entity.marketStat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.big18.finale.entity.MarketData;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "OIL")
public class Oil extends MarketData {
    @Column(name = "name", length = 10)
    private String name;

    @Id
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "price", nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

}