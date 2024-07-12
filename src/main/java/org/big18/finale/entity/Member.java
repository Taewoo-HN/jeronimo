package org.big18.finale.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "member", schema = "stock_test")
public class Member {
    @Id
    @Column(name = "u_phonenum", nullable = false, length = 11)
    private String uPhonenum;

    @Column(name = "username", nullable = false, length = 10)
    private String username;

    @Column(name = "nickname", length = 10)
    private String nickname;

    @Column(name = "stock_account", length = 12)
    private String stockAccount;

    @Column(name = "secret_key", length = 200)
    private String secretKey;

}