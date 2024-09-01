package org.big18.finale.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockDisplayData {
    private String code;
    private String name;  // 종목명
    private Double currentPrice;  // 현재가
    private Double percentageChange;  // 등락률
    private Double priceDifference;  // 차이량

    public String getFormattedCurrentPrice() {
        return String.format("%.0f", currentPrice);  // 소수점 이하 없는 가격 표시
    }

    public String getFormattedPercentageChange() {
        return String.format("%.2f", percentageChange);  // 소수점 이하 두 자리까지 표시
    }

    public String getFormattedDifference() {
        return String.format("%.0f", priceDifference);  // 소수점 이하 없는 차이량 표시
    }

    public boolean isPercentageChangePositive() {
        return this.percentageChange > 0;
    }
}

