package com.mytaxi.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.util
 * USER      : sean
 * DATE      : 12-Fri-Oct-2018
 * TIME      : 21:21
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public class AuthenticationUtil {

    //Ensures that this class cannot be instantiated
    private AuthenticationUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Cannot Instantiate AuthenticationUtil Constructor.");
    }

    public static void clearAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    public static void configureAuthentication(String role) {
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(role);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "SPRING_TEST_USER",
                role,
                authorities
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
