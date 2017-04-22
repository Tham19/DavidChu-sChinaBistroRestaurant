package com.github.thamvannguyen;

import com.github.thamvannguyen.user.AccountService;
import com.github.thamvannguyen.user.dao.entity.Account;
import com.github.thamvannguyen.user.dto.AccountDto;
import com.github.thamvannguyen.user.exception.AccountAlreadyExistException;
import com.github.thamvannguyen.user.exception.AccountNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by thamnguyen on 17/04/2017.
 */
@Controller
public class HomeController {

    Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final String PROTOCOL = "http://";
    private final String TOKEN_PARAMETER_URL = "&token=";
    private final String MAIL_SUBJECT = "Reset password";
    private final String USERNAME_PARAMETER_URL = "id=";
    private final String CHANGEPASSWORD_URL = "/account/changePassword?";
    private final String CONTENT = "Please access link below to change password. Note that link active during 24 hours ! \r\n";

    @Autowired
    private AccountService accountService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "logout", required = false) String logout,
                              @RequestParam(value = "message", required = false) String message) {
        ModelAndView modelAndView = new ModelAndView();
        if(logout != null){
            modelAndView.addObject("loginMessage", "You've been logged out successfully.");
        }

        if(message != null){
            modelAndView.addObject("loginMessage",message.toString());
        }

        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    protected ModelAndView home() throws AccountNotFoundException{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.GET)
    protected String signUp(Model model){
        model.addAttribute("accountDto", new AccountDto());
        logger.info("Init accountDto form");
        logger.debug("Init accountDto form");
        return "sign_up";
    }

    @RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
    public String saveUser(@Valid AccountDto accountDto, BindingResult result) throws AccountAlreadyExistException {
        // handle error
        if (result.hasErrors()) {
            logger.info("Got Error");
            logger.debug("Error when input not correct format file data");
            return "sign_up";
        } else {
            // insert into database
            //ti bat loi dang ky da ton tai
            logger.info("Success");
            logger.debug("Register success");
            accountService.createAccount(accountDto);
            return "redirect:/login?message=Sign_up success!";
        }
    }


    @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
    public String forgotPassword() {
        return "send_email_reset_password";
    }

    // Reset password
    @RequestMapping(value = "/account/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    private ModelAndView resetPassword(HttpServletRequest request, HttpServletResponse response, @RequestParam("email") String email) throws IOException, AccountNotFoundException {
        final AccountDto accountDto = accountService.findAccountDtoByEmail(email);
        System.out.println("Check account : "+accountDto.toString());
        if (accountDto != null) {
            final String token = UUID.randomUUID().toString();
            accountService.createPasswordResetTokenForUser(accountDto, token);
            mailSender.send(constructResetTokenEmail(getAppUrl(request), token, accountDto));
//            /*OutputStream outputStream = response.getOutputStream();
//            outputStream.close();*/
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        return modelAndView;
    }



    /*try*/
    @RequestMapping(value = "/account/changePassword", method = RequestMethod.GET)
    public ModelAndView showChangePasswordPage(@RequestParam("id") String id, @RequestParam("token") final String token) throws AccountNotFoundException {
        boolean checkPasswordResetToken = accountService.validatePasswordResetToken(id, token);
        ModelAndView modelAndView = new ModelAndView();
        if (!checkPasswordResetToken) {
            modelAndView.setViewName("redirect:/login?error=Don't exit token or expiry date");
            return modelAndView;
        }
        final Account account = accountService.findAccountById(id);
        final Authentication authentication = new UsernamePasswordAuthenticationToken(account, null,userDetailsService.loadUserByUsername(account.getUsername()).getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        modelAndView.setViewName("update_password");
        return modelAndView;
    }

    @RequestMapping(value = "/account/savePassword", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView savePassword(@RequestParam("newPassword") String newPassword) throws AccountNotFoundException {
        final Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        accountService.changeUserPassword(account, newPassword);
        ModelAndView modelAndView = new ModelAndView("redirect:/login?message=Change password success");
        return modelAndView;
    }



//    NON API
    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final String token, final AccountDto accountDto) {
        StringBuilder url = new StringBuilder(CONTENT);
        url.append(contextPath);
        url.append(CHANGEPASSWORD_URL);
        url.append(USERNAME_PARAMETER_URL);
        url.append(accountDto.getUsername());
        url.append(TOKEN_PARAMETER_URL);
        url.append(token).toString();
        return constructEmail(MAIL_SUBJECT, url.toString(), accountDto);
    }

    private SimpleMailMessage constructEmail(String subject, String body, AccountDto accountDto) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(accountDto.getEmail());
        return email;
    }

    private String getAppUrl(HttpServletRequest request) {
        final StringBuilder url = new StringBuilder(PROTOCOL);
        url.append(request.getServerName());
        url.append(":");
        url.append(request.getServerPort());
        url.append(request.getContextPath());
        return url.toString();
    }

}
