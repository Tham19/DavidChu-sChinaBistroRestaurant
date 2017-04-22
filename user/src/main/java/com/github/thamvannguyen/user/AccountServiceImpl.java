package com.github.thamvannguyen.user;

import com.github.thamvannguyen.user.dao.entity.Account;
import com.github.thamvannguyen.user.dao.entity.PasswordResetToken;
import com.github.thamvannguyen.user.dao.entity.Role;
import com.github.thamvannguyen.user.dao.repository.PasswordResetTokenRepository;
import com.github.thamvannguyen.user.dto.AccountDto;
import com.github.thamvannguyen.user.exception.AccountAlreadyExistException;
import com.github.thamvannguyen.user.exception.AccountNotFoundException;
import com.github.thamvannguyen.user.manager.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountManager accountManager;
    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Account createAccount(AccountDto accountDto) throws AccountAlreadyExistException {
        Role role = new Role();
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        Account account = new Account();
        account.setEmail(accountDto.getEmail());
        account.setUsername(accountDto.getUsername());
        account.setPhoneNumber(accountDto.getPhoneNumber());
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        account.getPasswordHistory().addPassword(accountDto.getPassword());
        account.setEnabled(true);
        account.setRoles(roles);
        return accountManager.create(account);
    }

    @Override
    public AccountDto findAccountDtoByEmail(String email) {
        Account account =  accountManager.findAccountByEmail(email);
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername(account.getId());
        accountDto.setEmail(account.getEmail());
        return  accountDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void createPasswordResetTokenForUser(AccountDto accountDto, String token) throws AccountNotFoundException {
        System.out.println("Tokenhere "+token);
        System.out.println("Idhere "+accountDto.getUsername());
        Account account = accountManager.findByAccountId(accountDto.getUsername());
        PasswordResetToken myToken = new PasswordResetToken(account, token);
        passwordResetTokenRepository.save(myToken);
    }

    private boolean isExitToken(PasswordResetToken passwordResetToken){
        return  passwordResetToken != null;
    }

    private boolean isMatchTokenAndAccountId(PasswordResetToken passwordResetToken,String id, String token){
        return  (passwordResetToken.getAccount().getId().equals(id) && passwordResetToken.getToken().equals(token));
    }
    private boolean isExpiryDate(PasswordResetToken passwordResetToken){
        final Calendar calendar = Calendar.getInstance();
        return ((passwordResetToken.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0)? true : false;
    }

    @Override
    public boolean validatePasswordResetToken(String id, String token) {
        final PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
        if (!isExitToken(passwordResetToken) || !isMatchTokenAndAccountId(passwordResetToken, id, token) || isExpiryDate(passwordResetToken)) {
            return false;
        }
        return true;
    }

    @Override
    public AccountDto findAccountDtoById(String accountDtoId) throws AccountNotFoundException {
        Account account = accountManager.findByAccountId(accountDtoId);
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername(account.getId());
        return accountDto;
    }

    @Override
    public void changeUserPassword(Account account, String newPassword) throws AccountNotFoundException {
        account.getPasswordHistory().addPassword(account.getPassword());
        account.getPasswordHistory().setLastPasswordChanged(new Date());
        account.setPassword(passwordEncoder.encode(newPassword));
        accountManager.update(account);
    }

    @Override
    public Account findAccountById(String id) throws AccountNotFoundException {
        return accountManager.findByAccountId(id);
    }

}

