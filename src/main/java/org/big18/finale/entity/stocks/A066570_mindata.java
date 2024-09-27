package org.big18.finale.entity.stocks;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "a066570_mindata")
public class A066570_mindata {
    @Column(name = "code", length = 10)
    private String code;

    @Column(name = "open_price")
    private Integer openPrice;

    @Column(name = "high_price")
    private Integer highPrice;

    @Column(name = "low_price")
    private Integer lowPrice;

    @Column(name = "close_price")
    private Integer closePrice;

    @Column(name = "volume")
    private Integer volume;

    @Column(name = "sell_vol")
    private Integer sellVol;

    @Column(name = "buy_vol")
    private Integer buyVol;

    @Id
    @ColumnDefault("date_format(sysdate(), '%Y-%m-%d')")
    @Column(name = "Jdate")
    private Instant jdate;

}