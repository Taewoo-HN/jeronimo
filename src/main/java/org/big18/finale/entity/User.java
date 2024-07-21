package org.big18.finale.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    private String userId;

    private String userPw;

    private String phoneNumber;

    private String email;

    @Column(name = "user_name", nullable = false, length = 10)
    private String userName;

}