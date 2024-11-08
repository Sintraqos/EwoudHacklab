package com.sintraqos.portfolioproject.Webservice;

import com.sintraqos.portfolioproject.DTO.AccountDTO;

import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
public class AccountDetails  implements UserDetails {

    private final AccountDTO account;  // Your custom Account object
    private final List<GrantedAuthority> authorities;

    public AccountDetails(AccountDTO account) {
        this.account = account;
        // Assuming `account` has a method to get roles or authorities
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_USER")); // Example: Assigning ROLE_USER
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return account.getPassword();  // Return the password from your custom account object
    }

    @Override
    public String getUsername() {
        return account.getUsername();  // Return the username from your custom account object
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Implement this based on your needs
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Implement this based on your needs
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Implement this based on your needs
    }

    @Override
    public boolean isEnabled() {
        return true;  // Implement this based on your needs
    }

}
