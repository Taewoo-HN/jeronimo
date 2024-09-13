package org.big18.finale.entity.stocks;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.big18.finale.entity.TrendsData;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "A000660_trends")
public class A000660Trends extends TrendsData {
    @Column(name = "code", length = 10)
    private String code;

    @Column(name = "individual")
    private Integer individual;

    @Column(name = "foreign_")
    private Integer foreign;

    @Column(name = "institution")
    private Integer institution;

    @Id
    @ColumnDefault("date_format(sysdate(), '%Y-%m-%d')")
    @Column(name = "Jdate")
    private LocalDate jdate;

}