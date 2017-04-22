package com.github.thamvannguyen.user;


import com.github.thamvannguyen.user.dao.entity.Account;
import com.github.thamvannguyen.user.dto.AccountDto;
import com.github.thamvannguyen.user.exception.AccountAlreadyExistException;
import com.github.thamvannguyen.user.exception.AccountNotFoundException;

public interface AccountService {

    Account createAccount(AccountDto accountDto) throws AccountAlreadyExistException;

    AccountDto findAccountDtoByEmail(String email);

    void createPasswordResetTokenForUser(AccountDto accountDto, String token) throws AccountNotFoundException;

    boolean validatePasswordResetToken(String id, String token);

    AccountDto findAccountDtoById(String token) throws AccountNotFoundException;

    void changeUserPassword(Account accountDto, String newPassword) throws AccountNotFoundException;

    Account findAccountById(String id) throws AccountNotFoundException;
}
