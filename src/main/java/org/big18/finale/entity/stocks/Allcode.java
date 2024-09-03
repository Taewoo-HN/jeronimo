package org.big18.finale.entity.stocks;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "allcode")
public class Allcode {
    @Id
    @Column(name = "code", nullable = false, length = 10)
    private String code;  // 종목코드

    @Column(name = "name", nullable = false, length = 50)
    private String name;  // 종목명
}
