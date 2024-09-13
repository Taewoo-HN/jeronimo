package org.big18.finale.service.market;

import org.big18.finale.DTO.StockTrendsData;
import org.big18.finale.entity.TrendsData;
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
                              A000660TrendsRepository a000600TrendsRepository, A003670TrendsRepository a003670TrendsRepository,
                              A005380TrendsRepository a005380TrendsRepository, A005490TrendsRepository a005490TrendsRepository,
                              A005930TrendsRepository a005930TrendsRepository, A022100TrendsRepository a022100TrendsRepository,
                              A042700TrendsRepository a042700TrendsRepository, A086520TrendsRepository a086520TrendsRepository,
                              A196170TrendsRepository a196170TrendsRepository, A247540TrendsRepository a247540TrendsRepository)
    {
        this.allcodeRepository = allcodeRepository;
        this.repositoryMap = Map.of(
                "000600", a000600TrendsRepository,
                "003670", a003670TrendsRepository,
                "005380", a005380TrendsRepository,
                "005490", a005490TrendsRepository,
                "005930", a005930TrendsRepository,
                "022100", a022100TrendsRepository,
                "042700", a042700TrendsRepository,
                "086520", a086520TrendsRepository,
                "196170", a196170TrendsRepository,
                "247540", a247540TrendsRepository
        );
    }

    public StockTrendsData getTrendByCode(String code) {
        JpaRepository repository = repositoryMap.get(code);
        String stockname = allcodeRepository.findById(code).get().getName();
        LocalDate today = LocalDate.now();
        if (repository != null) {
            Optional data = repository.findById(today);
            if(data.isEmpty()) {
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
