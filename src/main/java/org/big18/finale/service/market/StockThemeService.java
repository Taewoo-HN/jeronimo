package org.big18.finale.service.market;

import org.big18.finale.entity.StockTheme;
import org.big18.finale.repository.StockThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockThemeService {

    private final StockThemeRepository stockThemeRepository;

    @Autowired
    public StockThemeService(StockThemeRepository stockThemeRepository) {
        this.stockThemeRepository = stockThemeRepository;
    }

    @Transactional(readOnly = true)
    public List<StockTheme> findThemesByKeywords(List<String> keywords) {
        List<StockTheme> allThemes = stockThemeRepository.findAll();

        return allThemes.stream()
                .filter(theme -> theme.getKeywords().stream()
                        .anyMatch(keyword -> keywords.contains(keyword.toLowerCase())))
                .collect(Collectors.toList());
    }

    @Transactional
    public StockTheme createTheme(String name, List<String> keywords) {
        StockTheme theme = new StockTheme(name);
        theme.setKeywords(keywords);
        return stockThemeRepository.save(theme);
    }

    @Transactional(readOnly = true)
    public List<StockTheme> getAllThemes() {
        return stockThemeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public StockTheme getThemeById(Long id) {
        return stockThemeRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteTheme(Long id) {
        stockThemeRepository.deleteById(id);
    }
}