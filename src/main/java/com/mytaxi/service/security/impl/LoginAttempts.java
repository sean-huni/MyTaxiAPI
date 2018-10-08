package com.mytaxi.service.security.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.service.security.impl
 * USER      : sean
 * DATE      : 08-Mon-Oct-2018
 * TIME      : 01:23
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public class LoginAttempts {
    private final int MAX_ATTEMPT;
    private LoadingCache<String, Integer> attemptsCache;

    public LoginAttempts(int MAX_ATTEMPT, long duration, TimeUnit timeUnit) {
        this.MAX_ATTEMPT = MAX_ATTEMPT;
        attemptsCache = CacheBuilder.newBuilder().
                expireAfterWrite(duration, timeUnit).build(new CacheLoader<String, Integer>() {
            public Integer load(String key) {
                return 0;
            }
        });
    }

    public void loginSucceeded(String key) {
        attemptsCache.invalidate(key);
    }

    public void loginFailed(String key) {
        int attempts = 0;
        try {
            attempts = attemptsCache.get(key);
        } catch (ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(key, attempts);
    }

    public boolean isBlocked(String key) {
        try {
            return attemptsCache.get(key) >= MAX_ATTEMPT;
        } catch (ExecutionException e) {
            return false;
        }
    }
}
