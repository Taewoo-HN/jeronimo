package org.big18.finale.service;

import org.big18.finale.DTO.MarketDisplayData;
import org.big18.finale.entity.MarketData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class MarketDataService {

    public <T extends MarketData> MarketDisplayData getMarketData(String marketName, JpaRepository<T, LocalDate> repository) {
        List<T> marketDataList = repository.findAll(Sort.by(Sort.Direction.DESC, "date"));

        if (marketDataList.size() < 2) {
            return null; // 데이터가 충분하지 않을 경우 처리
        }

        T latestData = marketDataList.get(0);
        T previousData = marketDataList.get(1);

        BigDecimal priceDifference = calculatePriceDifference(latestData.getPrice(), previousData.getPrice());
        BigDecimal percentageChange = calculatePercentageChange(latestData.getPrice(), previousData.getPrice());

        return new MarketDisplayData(
                marketName,
                latestData.getDate(),
                latestData.getPrice(),
                priceDifference,
                percentageChange
        );
    }

    private BigDecimal calculatePriceDifference(BigDecimal currentPrice, BigDecimal previousPrice) {
        return currentPrice.subtract(previousPrice);
    }

    private BigDecimal calculatePercentageChange(BigDecimal currentPrice, BigDecimal previousPrice) {
        if (previousPrice.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return (currentPrice.subtract(previousPrice))
                .divide(previousPrice, BigDecimal.ROUND_HALF_UP)
                .multiply(BigDecimal.valueOf(100));
    }
}
