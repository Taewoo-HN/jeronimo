package org.big18.finale.controller;

import jakarta.servlet.http.HttpSession;
import org.big18.finale.DTO.ChatMessage;
import org.big18.finale.DTO.MarketDisplayData;
import org.big18.finale.DTO.StockDisplayData;
import org.big18.finale.entity.News;
import org.big18.finale.repository.marketStat.*;
import org.big18.finale.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@Controller
public class MainController {

    private final NewsService newsService;
    private final StockService stockService;
    private final UserNameProvider userNameProvider;
    private final MarketDataService marketDataService;
    private final KosdaqRepository kosdaqRepository;
    private final KospiRepository kospiRepository;
    private final OilRepository oilRepository;
    private final GoldRepository goldRepository;
    private final NasdaqRepository nasdaqRepository;
    private final DollarRepository dollarRepository;
    private final SnpRepository snp500Repository;


    @Autowired
    public MainController(NewsService newsService, StockService stockService, HttpSession session, UserNameProvider userNameProvider,
                          MarketDataService marketDataService, KosdaqRepository kosdaqRepository, KospiRepository kospiRepository,
                          OilRepository oilRepository, GoldRepository goldRepository, NasdaqRepository nasdaqRepository, SnpRepository snpRepository,
                          DollarRepository dollarRepository, SnpRepository snp500Repository) {
        this.newsService = newsService;
        this.stockService = stockService;
        this.userNameProvider = userNameProvider;
        this.marketDataService = marketDataService;
        this.kosdaqRepository = kosdaqRepository;
        this.kospiRepository = kospiRepository;
        this.oilRepository = oilRepository;
        this.goldRepository = goldRepository;
        this.nasdaqRepository = nasdaqRepository;
        this.dollarRepository = dollarRepository;
        this.snp500Repository = snp500Repository;
    }

    @GetMapping("/")
    public String redirectToMain() {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String home(Model model, HttpSession session) {
        List<News> newsList = newsService.getAllNews();
        Collections.shuffle(newsList);
        newsList = newsList.subList(0, 9);

        List<StockDisplayData> stockDataList = stockService.getAllStockData();
        model.addAttribute("stockDataList", stockDataList);
        model.addAttribute("newsList", newsList);

        MarketDisplayData kosdaqData = marketDataService.getMarketData("Kosdaq", kosdaqRepository);
        MarketDisplayData kospiData = marketDataService.getMarketData("Kospi", kospiRepository);
        MarketDisplayData goldData = marketDataService.getMarketData("Gold", goldRepository);
        MarketDisplayData dollarData = marketDataService.getMarketData("Dollar", dollarRepository);
        MarketDisplayData snp500Data = marketDataService.getMarketData("S&P 500", snp500Repository);
        MarketDisplayData oilData = marketDataService.getMarketData("Oil", oilRepository);
        MarketDisplayData nasdaqData = marketDataService.getMarketData("Nasdaq", nasdaqRepository);

        // 각각의 데이터를 Model에 추가
        model.addAttribute("kosdaqData", kosdaqData);
        model.addAttribute("kospiData", kospiData);
        model.addAttribute("goldData", goldData);
        model.addAttribute("dollarData", dollarData);
        model.addAttribute("snp500Data", snp500Data);
        model.addAttribute("oilData", oilData);
        model.addAttribute("nasdaqData", nasdaqData);

        userNameProvider.setUserAttributes(session, model);

        return "index";
    }

    @GetMapping("/detail/{code}")
    public String getStockDetail(@PathVariable String code, Model model, HttpSession session) {
        userNameProvider.setUserAttributes(session, model);
        return "detail";
    }

    @GetMapping("/chatting")
    public String chatWindow() {
        return "chatbot_window";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


}


