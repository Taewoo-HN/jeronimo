package org.big18.finale.controller;

import org.big18.finale.entity.stocks.Allcode;
import org.big18.finale.service.market.AllcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class AllcodeController {

    private final AllcodeService allcodeService;

    @Autowired
    public AllcodeController(AllcodeService allcodeService) {
        this.allcodeService = allcodeService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Allcode>> searchStocks(@RequestParam(required = false) String term) {
        List<Allcode> results = allcodeService.searchStocks(term);
        return ResponseEntity.ok(results);
    }
}