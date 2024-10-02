package org.big18.finale.entity.stocks;

import jakarta.persistence.*;
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

    @Transient  // 이 필드는 DB에 저장되지 않음
    private boolean selected;
}
