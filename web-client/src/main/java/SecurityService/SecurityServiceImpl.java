package SecurityService;

import com.github.thamvannguyen.user.dao.entity.Account;
import com.github.thamvannguyen.user.exception.AccountNotFoundException;
import com.github.thamvannguyen.user.manager.AccountManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class SecurityServiceImpl implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
    @Autowired
    AccountManagerImpl accountManagerImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = new Account();
        try {
            System.out.println("====USERNAME FROM SECURITYSERVICEIMPL CLASS==== "+ username);
            account = accountManagerImpl.findByUsername(username);
            if (account == null)
                throw new UsernameNotFoundException("No such user: " + username);
            else {
                System.out.println("====ACCOUNT FROM SECURITYSERVICEIMPL CLASS==== " + account.toString());

                if(account.getPasswordHistory().getFailedLoginAttempts() >= account.getPasswordHistory().getSize() &&
                        account.getPasswordHistory().isTimeUp(account.getPasswordHistory().getLastPasswordFailed())){
                    //update nonlock account, reset attempt account, update db
                    account.resetAccountNonLocked();
                    account.getPasswordHistory().resetFailedLoginAttempts();
                    accountManagerImpl.update(account);
                }
                System.out.println("====ACCOUNT FROM SECURITYSERVICEIMPL CLASS(AFTER RESET FAILED)==== " + account.toString());
            }
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
        }
        return account;
    }
}
