package com.business.coffeshop.service;

import com.business.coffeshop.entity.Account;
import org.springframework.security.core.userdetails.UserDetails;

public interface AccountService {
    UserDetails loadUserByMsisdn(String msisdn);

    boolean existsByMsisdn(String msisdn);

    void save(Account newAccount);
}
