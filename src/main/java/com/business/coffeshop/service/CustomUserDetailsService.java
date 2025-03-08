package com.business.coffeshop.service;

import com.business.coffeshop.entity.Account;
import com.business.coffeshop.repository.AccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByMsisdn(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(account.getMsisdn(), account.getPassword(), Collections.emptyList());
    }
}
