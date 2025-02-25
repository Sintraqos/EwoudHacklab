package com.sintraqos.portfolioproject.user.DAL;

import com.sintraqos.portfolioproject.user.DTO.UserDTO;
import com.sintraqos.portfolioproject.user.statics.Enums;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountID;

    @Column(nullable = false, length = 100)
    private String username;
    private String email;
    private String passwordHash;
    private boolean isAccountNonExpired= true;
    private boolean isAccountNonLocked= true;
    private boolean isCredentialsNonExpired= true;
    private boolean isEnabled= true;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Enums.Role role;

    public UserEntity(String username, String email, String password, Enums.Role role) {
        this.username = username;
        this.email = email;
        this.passwordHash = password;
        this.role = role;
    }

    public UserEntity(UserDTO userDTO) {
        this.accountID = userDTO.getAccountID();
        this.username = userDTO.getUsername();
        this.email = userDTO.getEMail();
        this.passwordHash = userDTO.getPassword();
        this.role = userDTO.getRole();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    public String getPassword() {
        return passwordHash;
    }
}