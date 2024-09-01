package org.big18.finale.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "A042700_mindata")
public class A042700_mindata {

    @Id
    @Column(name = "Jdate", nullable = false)
    private Timestamp jdate;  // 거래 시간

    @Column(name = "code", nullable = false, length = 10)
    private String code;  // 종목코드

    @Column(name = "open_price", nullable = false)
    private Double openPrice;  // 시작가

    @Column(name = "high_price", nullable = false)
    private Double highPrice;  // 고가

    @Column(name = "low_price", nullable = false)
    private Double lowPrice;  // 저가

    @Column(name = "close_price", nullable = false)
    private Double closePrice;  // 종가

    @Column(name = "volume", nullable = false)
    private Integer volume;  // 거래량
}