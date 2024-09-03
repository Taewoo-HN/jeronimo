package org.big18.finale.service;

import org.big18.finale.DTO.StockDisplayData;
import org.big18.finale.repository.stocks.AllcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.big18.finale.entity.stocks.Allcode;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StockService {

    private final AllcodeRepository allcodeRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StockService(AllcodeRepository allcodeRepository1, JdbcTemplate jdbcTemplate1) {
        this.allcodeRepository = allcodeRepository1;
        this.jdbcTemplate = jdbcTemplate1;
    }

    public List<StockDisplayData> getAllStockData() {
        List<String> codes = allcodeRepository.findAll()
                .stream()
                .map(Allcode::getCode)
                .collect(Collectors.toList());

        return codes.stream()
                .map(code -> {
                    Map<String, Object> latestData = getLatestStockData(code);
                    Map<String, Object> firstDataOfTheDay = getFirstDataOfTheDay(code);

                    Integer latestClosePrice = (Integer) latestData.get("close_price");
                    Integer firstOpenPrice = (Integer) firstDataOfTheDay.get("open_price");

                    // Null 값 체크 및 처리
                    if (latestClosePrice == null || firstOpenPrice == null) {
                        // 기본값을 사용하거나, 다른 로직을 적용
                        latestClosePrice = (latestClosePrice != null) ? latestClosePrice : 0;
                        firstOpenPrice = (firstOpenPrice != null) ? firstOpenPrice : 0;
                    }

                    double percentageChange = calculatePercentageChange(latestClosePrice.doubleValue(), firstOpenPrice.doubleValue());
                    double priceDifference = latestClosePrice - firstOpenPrice;

                    String name = allcodeRepository.findById(code).map(Allcode::getName).orElse("Unknown");

                    return new StockDisplayData(code, name, latestClosePrice.doubleValue(), percentageChange, priceDifference);
                })
                .collect(Collectors.toList());
    }


    // 최신 데이터를 가져오는 메서드
    public Map<String, Object> getLatestStockData(String code) {
        String tableName = "`A" + code + "_mindata`";
        String sql = "SELECT * FROM " + tableName + " ORDER BY Jdate DESC LIMIT 1";

        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
        if (results.isEmpty()) {
            return new HashMap<>(); // 결과가 없으면 빈 맵 반환
        } else {
            return results.get(0); // 결과가 있으면 첫 번째 결과 반환
        }
    }

    // 당일 첫 데이터를 가져오는 메서드
    public Map<String, Object> getFirstDataOfTheDay(String code) {
        String tableName = "`A" + code + "_mindata`";
        String todayDatePattern = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 09:01%";
        String sql = "SELECT * FROM " + tableName + " WHERE Jdate LIKE ? LIMIT 1";

        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, todayDatePattern);
        if (results.isEmpty()) {
            return new HashMap<>(); // 결과가 없으면 빈 맵 반환
        } else {
            return results.get(0); // 결과가 있으면 첫 번째 결과 반환
        }
    }

    private double calculatePercentageChange(Double latestClosePrice, Double firstOpenPrice) {
        return ((latestClosePrice - firstOpenPrice) / firstOpenPrice) * 100;
    }
}
