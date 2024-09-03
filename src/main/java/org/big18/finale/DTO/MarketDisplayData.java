package org.big18.finale.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class MarketDisplayData {
    private String marketName;
    private LocalDate date;
    private BigDecimal currentPrice;
    private BigDecimal priceDifference;
    private BigDecimal percentageChange;

    public String getPercentageChange() {
        return String.format("%.2f%%", percentageChange);
    }

    public String calPriceDifference() {
        return String.format("%.2f%%", priceDifference);
    }

    public boolean isPercentageChangePositive() {
        return this.percentageChange.compareTo(BigDecimal.ZERO) > 0;
    }
}

