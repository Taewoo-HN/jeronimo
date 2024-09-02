package org.big18.finale.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public abstract class MarketData {
    private LocalDate date;
    private BigDecimal price;

}
