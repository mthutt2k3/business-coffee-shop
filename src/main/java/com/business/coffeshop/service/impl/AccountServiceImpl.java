package com.business.coffeshop.service.impl;

import com.business.coffeshop.entity.Account;
import com.business.coffeshop.repository.AccountRepository;
import com.business.coffeshop.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByMsisdn(String msisdn) {
        Account account = accountRepository.findByMsisdn(msisdn)
                .orElse(null);
        log.debug("account: {}", account);
        if (account == null) {
            throw new UsernameNotFoundException("Account not found");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(account.getMsisdn())
                .password(account.getPassword()) // đã được mã hóa bằng BCrypt
                .authorities("ROLE_USER") // hoặc roles của user
                .build();
    }

    @Override
    public boolean existsByMsisdn(String msisdn) {
        return accountRepository.existsByMsisdn(msisdn);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }
}
