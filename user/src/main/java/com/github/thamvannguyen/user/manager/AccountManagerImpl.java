package com.github.thamvannguyen.user.manager;


import com.github.thamvannguyen.user.dao.entity.Account;
import com.github.thamvannguyen.user.dao.entity.PasswordHistory;
import com.github.thamvannguyen.user.dao.repository.AccountRepository;
import com.github.thamvannguyen.user.dto.AccountDto;
import com.github.thamvannguyen.user.exception.AccountAlreadyExistException;
import com.github.thamvannguyen.user.exception.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;


public class AccountManagerImpl implements AccountManager {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findByAccountId(String accountId) throws AccountNotFoundException {
        Account account = accountRepository.findOne(accountId);
        if (account == null) {
            throw new AccountNotFoundException();
        }
        return account;

    }

    @Override
    public Account findByUsername(String username) throws AccountNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new AccountNotFoundException();
        }
        return account;
    }


    @Override
    @Transactional
    public Account create(Account account) throws AccountAlreadyExistException {
        if (isExists(account)) {
            throw new AccountAlreadyExistException();
        }
        return accountRepository.save(account);
    }

    public boolean isExists(Account account) {
        return accountRepository.countByUsernameOrEmailOrPhoneAndIdNotIn(account.getUsername(),
                account.getEmail(), account.getPhoneNumber(), account.getId()) > 0;
    }

    @Override
    public Account update(Account account) throws AccountNotFoundException {
        if (!isExists(account)) {
            throw new AccountNotFoundException();
        }
        return accountRepository.save(account);
    }

    @Override
    public void delete(Account account) {
        accountRepository.delete(account);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.DEFAULT, readOnly = false)
    public void changePassword(String accountId, String newPassword) throws AccountNotFoundException {
        Account account = findByAccountId(accountId);
        PasswordHistory passwordHistory = account.getPasswordHistory();
        passwordHistory.addPassword(newPassword);
        passwordHistory.setLastPasswordChanged(Calendar.getInstance().getTime());
        account.setPassword(newPassword);
        accountRepository.save(account);
    }

    @Override
    public Account findAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }
}
