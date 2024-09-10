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
        if (term == null || term.trim().isEmpty()) {
            return allcodeRepository.findTop5OrderByName(PageRequest.of(0, 5));
        }
        return allcodeRepository.searchByNameOrCode(term);
    }
}