package com.business.coffeshop.service.impl;

import com.business.coffeshop.constant.RoleCodeEnum;
import com.business.coffeshop.dto.AccountDto;
import com.business.coffeshop.dto.RegisterAccountDto;
import com.business.coffeshop.entity.Account;
import com.business.coffeshop.mapper.AccountMapper;
import com.business.coffeshop.repository.AccountRepository;
import com.business.coffeshop.service.AccountService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Override
    @Transactional
    public Account registerCustomerAccount(RegisterAccountDto registerAccount) {
        // Kiểm tra xem số điện thoại đã tồn tại chưa
        if (accountRepository.findByMsisdn(registerAccount.getMsisdn()).isPresent()) {
            throw new IllegalArgumentException("Phone number already exists!");
        }

        // Tạo mới User
        Account newAccountSaved = new Account();
        newAccountSaved.setMsisdn(registerAccount.getMsisdn());
        newAccountSaved.setPassword(passwordEncoder.encode(registerAccount.getPassword()));
        newAccountSaved.setRoleCode(RoleCodeEnum.CUST.name());
        newAccountSaved.setAccountName(registerAccount.getName());
        newAccountSaved.setAddress(registerAccount.getAddress());
        accountRepository.save(newAccountSaved);

        return accountRepository.save(newAccountSaved);
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public AccountDto getAccountDtoById(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null) {
            return null;
        }
        AccountDto accountDto = accountMapper.toAccountDto(account);
        return accountDto;
    }

    @Override
    public List<AccountDto> getAllAccount() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDto> accountDtos = accounts.stream()
                .map(accountMapper::toAccountDto)
                .collect(Collectors.toList());
        return accountDtos;
    }

}
