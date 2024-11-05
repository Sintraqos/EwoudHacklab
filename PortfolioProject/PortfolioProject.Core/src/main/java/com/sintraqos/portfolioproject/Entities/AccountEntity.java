package com.sintraqos.portfolioproject.Entities;

import com.sintraqos.portfolioproject.DTO.AccountDTO;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Account Entity Object, use for creating new Database Tables, and for storing the data from the database
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "accounts")
public class AccountEntity implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountID;

    @Column(nullable = false, length = 100)
    private String username;

    @Column(name = "eMail")
    private String eMail;

    @Column(name = "passwordHash")
    private String passwordHash;

    @Column(name = "authorities")
    private String authorities;

    /**
     * Returns the authorities granted to the user.
     * @return a collection of GrantedAuthority objects
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Split the authorities string and convert to a list of SimpleGrantedAuthority objects
        return Arrays.stream(this.authorities.split("::"))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public AccountEntity(String username, String password) {
        this.username = username;
        this.eMail = eMail;
        this.passwordHash = password;
    }

    public AccountEntity(String username, String eMail, String password) {
        this.username = username;
        this.eMail = eMail;
        this.passwordHash = password;
    }

    public AccountEntity(AccountDTO accountDTO) {
        this.username = accountDTO.getUsername();
        this.eMail = accountDTO.getEMail();
        this.passwordHash = accountDTO.getPassword();
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}