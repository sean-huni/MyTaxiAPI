package com.mytaxi.service.security.impl;

import com.mytaxi.service.security.LoginAttemptService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.service.auth
 * USER      : sean
 * DATE      : 03-Wed-Oct-2018
 * TIME      : 23:15
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Service
@Qualifier("clientIPAddress")
public class LoginAttemptIPAddressServiceImpl extends LoginAttempts implements LoginAttemptService {

    public LoginAttemptIPAddressServiceImpl() {
        super(3, 1, TimeUnit.MINUTES);
    }
}
