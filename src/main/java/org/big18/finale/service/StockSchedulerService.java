package org.big18.finale.service;

import org.big18.finale.DTO.StockDisplayData;
import org.big18.finale.service.market.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StockSchedulerService {

    private final StockService stockService;
    private final SimpMessagingTemplate messagingTemplate;
    private Map<String, Integer> lastClosePrices = new HashMap<>();  // 마지막 종가 저장


    @Autowired
    public StockSchedulerService(SimpMessagingTemplate messagingTemplate, StockService stockService) {
        this.messagingTemplate = messagingTemplate;
        this.stockService = stockService;
    }

    @Scheduled(fixedRate = 5000)
    public void sendStockUpdates() {
        List<StockDisplayData> stockDataList = stockService.getAllStockData();

        stockDataList.forEach(stock -> {
            if (!lastClosePrices.containsKey(stock.getCode()) || !lastClosePrices.get(stock.getCode()).equals(stock.getCurrentPrice().intValue())) {
                messagingTemplate.convertAndSend("/topic/stocks", stock);  // 실시간 데이터 전송
                lastClosePrices.put(stock.getCode(), stock.getCurrentPrice().intValue());
            }
        });
    }
}
