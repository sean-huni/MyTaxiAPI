package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.exception
 * USER      : sean
 * DATE      : 07-Sun-Oct-2018
 * TIME      : 12:12
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Security Violation. Too many login attempts.")
public class AccountSecurityException extends AuthenticationException {
    static final long serialVersionUID = -3389416993124228948L;

    /**
     * Constructs a {@code SecurityException} with the specified
     * detail message.
     *
     * @param s the detail message.
     */
    public AccountSecurityException(String s) {
        super(s);
    }
}
