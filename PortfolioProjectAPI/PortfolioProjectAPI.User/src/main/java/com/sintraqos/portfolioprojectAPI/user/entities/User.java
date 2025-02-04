package com.sintraqos.portfolioprojectAPI.user.entities;

import com.sintraqos.portfolioprojectAPI.user.DAL.UserEntity;
import com.sintraqos.portfolioprojectAPI.user.DTO.UserDTO;
import com.sintraqos.portfolioprojectAPI.user.statics.Enums;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Use for storing account data locally
 */
@Getter
public class User implements UserDetails {
    private int accountID = -1;
    private final String username;
    private final String password;
    private Enums.Role role;
    private final boolean isAccountNonExpired= true;
    private final boolean isAccountNonLocked= true;
    private final boolean isCredentialsNonExpired= true;
    private final boolean isEnabled= true;

    /**
     * Create a new account object from a DTO object
     *
     * @param userEntity the incoming Entity object
     */
    public User(UserEntity userEntity){
        this.accountID = userEntity.getAccountID();
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.role = userEntity.getRole();
    }

    /**
     * Create a new account object from a DTO object
     *
     * @param userDTO the incoming DTO object
     */
    public User(UserDTO userDTO){
        this.accountID = userDTO.getAccountID();
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
        this.role = userDTO.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return password;
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

    @Override
    public String toString()
    {
        return "%s".formatted(username);
    }
}
