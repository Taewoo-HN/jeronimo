package org.big18.finale.service.market;

import org.big18.finale.entity.stocks.Allcode;
import org.big18.finale.repository.stocks.AllcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllcodeService {
    private final AllcodeRepository allcodeRepository;

    @Autowired
    public AllcodeService(AllcodeRepository allcodeRepository) {
        this.allcodeRepository = allcodeRepository;
    }

    public List<Allcode> searchStocks(String term) {
        System.out.println("Searching for term: " + term);
        List<Allcode> results;

        if (term == null || term.trim().isEmpty()) {
            results = allcodeRepository.findTop5OrderByName(PageRequest.of(0, 5));
            System.out.println("Empty term search results: " + results.size());
        } else {
            results = allcodeRepository.searchByNameOrCode(term.trim());
            System.out.println("Search results for '" + term + "': " + results.size());
        }
        return results;
    }

    public List<Allcode> getAllStocks() {
        List<Allcode> stocks = allcodeRepository.findAll();
        System.out.println("Total stocks loaded: " + stocks.size());
        return stocks;
    }
}