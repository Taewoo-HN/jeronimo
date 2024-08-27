package org.big18.finale.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name="allcode")
@Getter
@Setter
public class StockCodeMap {

    @Column(name = "code", length = 10)
    private String code;

    @Column(name = "name", length = 50)
    private String name;

}
