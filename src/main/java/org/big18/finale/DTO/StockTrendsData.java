package org.big18.finale.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockTrendsData {
    private String code;
    private String Stock_name;
    private Long individual;
    private Long foreigners;
    private Long institutions;

}
