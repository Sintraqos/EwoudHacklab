package com.sintraqos.portfolioproject.Webservice;

import com.sintraqos.portfolioproject.Account.AccountManager;
import com.sintraqos.portfolioproject.DTO.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsService implements UserDetailsService {
    @Autowired
    AccountManager accountManager;  // Service to manage account operations

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDTO account = accountManager.getAccount(username).getAccount();

        if (account == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return new AccountDetails(account);
    }
}