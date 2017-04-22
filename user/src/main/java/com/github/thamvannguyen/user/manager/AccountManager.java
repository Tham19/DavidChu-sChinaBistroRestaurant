package com.github.thamvannguyen.user.manager;


import com.github.thamvannguyen.user.dao.entity.Account;
import com.github.thamvannguyen.user.dto.AccountDto;
import com.github.thamvannguyen.user.exception.AccountAlreadyExistException;
import com.github.thamvannguyen.user.exception.AccountNotFoundException;

public interface AccountManager {
    Account findByAccountId(String accountId) throws AccountNotFoundException;

    Account findByUsername(String username) throws AccountNotFoundException;

    Account create(Account account) throws AccountAlreadyExistException;

    Account update(Account account) throws AccountNotFoundException;

    void delete(Account account);

    void changePassword(String accountId, String newPassword) throws AccountNotFoundException;

    Account findAccountByEmail(String email);
}
