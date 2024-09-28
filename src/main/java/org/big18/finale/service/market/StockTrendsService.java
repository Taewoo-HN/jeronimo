package org.big18.finale.service.market;

import org.big18.finale.DTO.StockTrendsData;
import org.big18.finale.entity.TrendsData;
import org.big18.finale.repository.StocksRepository;
import org.big18.finale.repository.stocks.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Service
public class StockTrendsService {

    private final Map<String, JpaRepository> repositoryMap;
    private final AllcodeRepository allcodeRepository;


    @Autowired
    public StockTrendsService(AllcodeRepository allcodeRepository,
                              A000660TrendsRepository a000660TrendsRepository, A003670TrendsRepository a003670TrendsRepository,
                              A005380TrendsRepository a005380TrendsRepository, A005490TrendsRepository a005490TrendsRepository,
                              A005930TrendsRepository a005930TrendsRepository, A022100TrendsRepository a022100TrendsRepository,
                              A042700TrendsRepository a042700TrendsRepository, A086520TrendsRepository a086520TrendsRepository,
                              A196170TrendsRepository a196170TrendsRepository, A247540TrendsRepository a247540TrendsRepository,
                              A035420TrendsRepository a035420TrendsRepository, A035720TrendsRepository a035720TrendsRepository,
                              A051910TrendsRepository a051910TrendsRepository, A066570TrendRepository a066570TrendRepository,
                              A068270TrendsRepository a068270TrendsRepository, A207940TrendsRepository a207940TrendsRepository
    ) {
        this.allcodeRepository = allcodeRepository;
        this.repositoryMap = Map.ofEntries(
                Map.entry("000660", a000660TrendsRepository),
                Map.entry("003670", a003670TrendsRepository),
                Map.entry("005380", a005380TrendsRepository),
                Map.entry("005490", a005490TrendsRepository),
                Map.entry("005930", a005930TrendsRepository),
                Map.entry("022100", a022100TrendsRepository),
                Map.entry("042700", a042700TrendsRepository),
                Map.entry("086520", a086520TrendsRepository),
                Map.entry("196170", a196170TrendsRepository),
                Map.entry("247540", a247540TrendsRepository),
                Map.entry("066570", a066570TrendRepository),
                Map.entry("035420", a035420TrendsRepository),
                Map.entry("035720", a035720TrendsRepository),
                Map.entry("051910", a051910TrendsRepository),
                Map.entry("068270", a068270TrendsRepository),
                Map.entry("207940", a207940TrendsRepository)
        );
    }
    public StockTrendsData getTrendByCode(String code) {
        JpaRepository repository = repositoryMap.get(code);
        String stockname = allcodeRepository.findById(code).get().getName();
        LocalDate today = LocalDate.now();
        if (repository != null) {
            Optional data = repository.findById(today);
            if (data.isEmpty()) {
                data = repository.findById(today.minusDays(1));
            }
            TrendsData rdata = (TrendsData) data.get();
            StockTrendsData tdata = new StockTrendsData(code, stockname, rdata.getIndividual(), rdata.getForeign(), rdata.getInstitution());
            return tdata;
        } else {
            throw new IllegalArgumentException("해당 코드에 대한 Repository가 없습니다.");
        }
    }
}
