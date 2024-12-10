package com.sintraqos.portfolioproject.Entities;

import com.sintraqos.portfolioproject.DTO.UserDTO;

import com.sintraqos.portfolioproject.Statics.Enums;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Account Entity Object, use for creating new Database Tables, and for storing the data from the database
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountID;

    @Column(nullable = false, length = 100)
    private String username;
    private String eMail;
    private String passwordHash;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Enums.Role role;

    public UserEntity(String username, String eMail, String password, Enums.Role role) {
        this.username = username;
        this.eMail = eMail;
        this.passwordHash = password;
        this.role = role;
    }

    public UserEntity(UserDTO userDTO) {
        this.username = userDTO.getUsername();
        this.eMail = userDTO.getEMail();
        this.passwordHash = userDTO.getPassword();
        this.role = userDTO.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}