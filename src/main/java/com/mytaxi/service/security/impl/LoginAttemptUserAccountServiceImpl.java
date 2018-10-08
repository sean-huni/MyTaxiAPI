package com.mytaxi.service.security.impl;

import com.mytaxi.service.security.LoginAttemptService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.service.security.impl
 * USER      : sean
 * DATE      : 08-Mon-Oct-2018
 * TIME      : 01:22
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Service
@Qualifier("userAccount")
public class LoginAttemptUserAccountServiceImpl extends LoginAttempts implements LoginAttemptService {
    public LoginAttemptUserAccountServiceImpl() {
        super(6, 4, TimeUnit.MINUTES);
    }
}
