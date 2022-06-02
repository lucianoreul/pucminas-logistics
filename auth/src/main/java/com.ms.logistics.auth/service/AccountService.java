package com.ms.logistics.auth.service;

import com.ms.logistics.auth.dto.AccountDTO;
import com.ms.logistics.auth.exception.BusinessException;
import com.ms.logistics.auth.model.Account;
import com.ms.logistics.auth.repository.AccountRepository;
import com.ms.logistics.auth.util.CryptoUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    protected AccountRepository getRepository() {
        return accountRepository;
    }

    public void insert(Account account) throws BusinessException {
        account.setPassword(CryptoUtil.hash(account.getPassword()));
        this.validateInsert(account);
        this.getRepository().save(account);
    }

    protected void validateInsert(Account account) throws BusinessException {
        if (getRepository().existsByUsername(account.getUsername())) {
            throw new BusinessException("Username already exists.");
        }
    }

    public Account findByUsername(String username) {
        return getRepository().findByUsername(username);
    }

    public Account findById(Integer id) {
        return getRepository().findById(id).orElseThrow();
    }

    public Account login(String username, String password) {
        return getRepository().login(username, CryptoUtil.hash(password));
    }

    public void createAccount(AccountDTO accountDTO) throws BusinessException {
        Account account = new Account(accountDTO.getUsername(), accountDTO.getPassword());
        this.insert(account);
    }
}
