package org.big18.finale.entity;

import lombok.Data;

@Data
public abstract class TrendsData {
    private String code;
    private Integer foreign;
    private Integer individual;
    private Integer institution;
}
