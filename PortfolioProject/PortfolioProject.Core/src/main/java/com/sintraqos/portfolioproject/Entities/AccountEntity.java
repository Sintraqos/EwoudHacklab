package com.sintraqos.portfolioproject.Entities;

import com.sintraqos.portfolioproject.DTO.AccountDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Account Entity Object, use for creating new Database Tables, and for storing the data from the database
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountID;

    @Column(nullable = false, length = 100)
    private String username;

    @Column(name = "eMail")
    private String eMail;

    @Column(name = "passwordHash")
    private String passwordHash;

    public AccountEntity(String username, String password) {
        this.username = username;
        this.eMail = eMail;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.passwordHash = passwordEncoder.encode(password);
    }

    public AccountEntity(String username, String eMail, String password) {
        this.username = username;
        this.eMail = eMail;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.passwordHash = passwordEncoder.encode(password);
    }

    public AccountEntity(AccountDTO accountDTO) {
        this.username = accountDTO.getUsername();
        this.eMail = accountDTO.getEMail();
        this.passwordHash = accountDTO.getPassword();
    }
}