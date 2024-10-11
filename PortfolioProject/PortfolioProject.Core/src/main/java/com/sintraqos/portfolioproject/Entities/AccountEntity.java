package com.sintraqos.portfolioproject.Entities;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "TESTsaccounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountID;

    @Column(nullable = false, length = 100)
    private String username;

    @Column(name = "eMail")
    private String eMail;

    @Column(name = "passwordHash")
    private String passwordHash;

    @Column(name = "passwordSalt")
    private String passwordSalt;

    public AccountEntity(String username, String eMail, String password) {
        this.username = username;
        this.eMail = eMail;
        this.passwordHash = password;
        this.passwordSalt = password;
    }

    public AccountEntity() {
    }
}