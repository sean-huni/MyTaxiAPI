package com.mytaxi.aop.security;

import com.mytaxi.service.security.LoginAttemptService;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.aop
 * USER      : sean
 * DATE      : 05-Fri-Oct-2018
 * TIME      : 13:23
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */

@Aspect
@Component
public class LoginAttempts {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAttempts.class);
    private LoginAttemptService loginAttemptService;

    @Autowired
    public LoginAttempts(@Qualifier("userAccount") LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }

    @Pointcut("execution(* org.springframework.security.authentication.AuthenticationProvider.authenticate(..))")
    public void doAuthenticate() {
        LOGGER.info("Pointcut Authentication...");
    }

    @Before("com.mytaxi.aop.security.LoginAttempts.doAuthenticate() && args(authentication)")
    public void logBefore(Authentication authentication) {
        LOGGER.info("Before Authentication... Username: {}", authentication.getPrincipal());
    }

    @AfterReturning(value = "com.mytaxi.aop.security.LoginAttempts.doAuthenticate()", returning = "authentication")
    public void logAfter(Authentication authentication) {
        String username = String.valueOf(authentication.getPrincipal());

        if (authentication.isAuthenticated()) {
            loginAttemptService.loginSucceeded(username);
        }

        LOGGER.info("After Authentication... \t{} Auth-Successful: {}", username, authentication.isAuthenticated());
    }

    @AfterThrowing("com.mytaxi.aop.security.LoginAttempts.doAuthenticate() && args(authentication)")
    public void logAuthenticationException(Authentication authentication) {
        String userDetails = (String) authentication.getPrincipal();

        if (!authentication.isAuthenticated()) {
            loginAttemptService.loginFailed(userDetails);
        }

        LOGGER.warn("Login Failed for user: {}", userDetails);
    }
}
