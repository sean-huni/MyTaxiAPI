package com.mytaxi.service.security.listener;

import com.mytaxi.exception.AccountSecurityException;
import com.mytaxi.service.security.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.auth
 * USER      : sean
 * DATE      : 03-Wed-Oct-2018
 * TIME      : 22:55
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Service
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    private final LoginAttemptService loginAttemptService;

    @Autowired
    public AuthenticationFailureListener(@Qualifier("clientIPAddress") final LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) throws AccountSecurityException {
        WebAuthenticationDetails auth = (WebAuthenticationDetails) authenticationFailureBadCredentialsEvent.getAuthentication().getDetails();

        loginAttemptService.loginFailed(auth.getRemoteAddress());

        if (loginAttemptService.isBlocked(auth.getRemoteAddress())) {
            throw new AccountSecurityException("Your IP-Address is blocked. Too many login attempts...");
        }
    }
}
