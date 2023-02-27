package com.logistics.auth.service;

import com.logistics.auth.dto.AccountDTO;
import com.logistics.auth.model.Account;
import com.logistics.auth.repository.AccountRepository;
import com.logistics.auth.util.CryptoUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * Crud Service for model: Account.
 *
 * @author LucianoReul
 */
@Service
@AllArgsConstructor
public class AccountService {

    /**
     * Account Repository
     */
    private final AccountRepository accountRepository;

    /**
     * Get Account CRUD Repository
     *
     * @return AccountRepository
     */
    protected AccountRepository getRepository() {
        return accountRepository;
    }

    /**
     * Save account in the table
     *
     * @param account
     */
    public void insert(Account account) {
        account.setPassword(CryptoUtil.hash(account.getPassword()));
        this.validateInsert(account);
        this.getRepository().save(account);
    }

    /**
     * Validate account information
     *
     * @param account
     */
    protected void validateInsert(Account account) {
        if (getRepository().existsByUsername(account.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT ,"Username already exists.");
        }
    }

    /**
     * Find account by username
     *
     * @param username
     * @return Optional<Account>
     */
    public Optional<Account> findByUsername(String username) {
        return getRepository().findByUsername(username);
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public Account login(String username, String password) {
        return getRepository().login(username, CryptoUtil.hash(password));
    }

    /**
     *
     * @param accountDTO
     */
    public void createAccount(AccountDTO accountDTO) {
        Account account = new Account(accountDTO.getUsername(), accountDTO.getPassword());
        this.insert(account);
    }
}
