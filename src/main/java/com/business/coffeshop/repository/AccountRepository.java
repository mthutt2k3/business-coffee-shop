package com.business.coffeshop.repository;

import com.business.coffeshop.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByMsisdn(String msisdn);

    boolean existsByMsisdn(String msisdn);
}
