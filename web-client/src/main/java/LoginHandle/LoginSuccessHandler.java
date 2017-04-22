package LoginHandle;

import com.github.thamvannguyen.user.dao.entity.Account;
import com.github.thamvannguyen.user.exception.AccountNotFoundException;
import com.github.thamvannguyen.user.manager.AccountManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

    @Autowired
    AccountManagerImpl accountManagerImpl;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        logger.info("====LOGIN SUCCESS FROM LOGINSUCCESSHANDLER CLASS==== ", authentication.getPrincipal());

        String username = request.getParameter("username");

        try {
            Account account = accountManagerImpl.findByUsername(username);
//            if(accountManagerImpl.isExists(account)){
                account.setLastAccessTimestamp(new Date());
                accountManagerImpl.update(account);
            System.out.println("LOGIN SUCCESS "+ account.toString());
//            }
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }

}

