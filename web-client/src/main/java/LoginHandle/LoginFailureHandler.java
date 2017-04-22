package LoginHandle;

import com.github.thamvannguyen.user.dao.entity.Account;
import com.github.thamvannguyen.user.exception.AccountNotFoundException;
import com.github.thamvannguyen.user.manager.AccountManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private Logger logger = LoggerFactory.getLogger(LoginFailureHandler.class);

    @Autowired
    AccountManagerImpl accountManagerImpl;
    /*@Autowired
    PasswordHistoryManager passwordHistoryManager;*/

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        logger.info("====LOGIN FAILED FROM LOGINFAILUREHANDLER CLASS====");

        String username = request.getParameter("username");
        if (username.equals("")) {
//            exception = new BadCredentialsException("Invalid username and password!");
            exception = new BadCredentialsException("username or password is empty!");
        } else {
            try {
                Account account = accountManagerImpl.findByUsername(username);
                if(account.getPasswordHistory().getFailedLoginAttempts() == 0){
                    account.getPasswordHistory().setLastPasswordFailed(new Date());
                }
                /*if(accountManagerImpl.isExists(account)  == false){
//                    exception = new BadCredentialsException("Invalid username and password!");
                    exception = new BadCredentialsException("Khong ton tai");
                }else */if(account.isAccountNonLocked() == false || account.isAccountNonExpired() == false || account.isEnabled() == false){
                    exception = new BadCredentialsException("Acount is Locked or Expired or Disabled!");
                }else if(account.getPasswordHistory().getFailedLoginAttempts() >= account.getPasswordHistory().getSize()){
                    account.setAccountNonLocked(false);
                    accountManagerImpl.update(account);
                    exception = new BadCredentialsException("Acount is Locked or Expired or Disabled!");
                }else {
                    account.getPasswordHistory().increaseFailedLoginAttempts();
                    accountManagerImpl.update(account);
                    System.out.println("==== LOGINFAILEUREHANDLER CLASS: CHECK UPDATE LOGIN FAILURE==== "+account.toString());
                    exception = new BadCredentialsException("Invalid username and password!");
                }

            } catch (AccountNotFoundException e) {
                e.printStackTrace();
            }
        }

        exception.printStackTrace();
        super.onAuthenticationFailure(request, response, exception);
    }

}