package com.mytaxi.service.security;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.service.auth
 * USER      : sean
 * DATE      : 03-Wed-Oct-2018
 * TIME      : 23:18
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public interface LoginAttemptService {
    void loginSucceeded(String key);

    void loginFailed(String key);

    boolean isBlocked(String key);
}
