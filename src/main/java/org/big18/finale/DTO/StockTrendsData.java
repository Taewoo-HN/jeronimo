package org.big18.finale.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockTrendsData {
    private String code;
    private String stock_name;
    private Integer individual;
    private Integer foreign;
    private Integer institution;

}
