package com.mytaxi.service.security.impl;

import com.mytaxi.dataaccessobject.UserRepo;
import com.mytaxi.domainobject.UserDO;
import com.mytaxi.exception.AccountSecurityException;
import com.mytaxi.service.security.LoginAttemptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mytaxi.constant.Constants.*;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.service.userdetail
 * USER      : sean
 * DATE      : 03-Wed-Oct-2018
 * TIME      : 23:25
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Qualifier("userdetails")
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private boolean isAdmin = false;
    private UserRepo userRepo;
    private LoginAttemptService loginAttemptService;

    @Autowired
    public UserDetailsServiceImpl(UserRepo userRepo, @Qualifier("userAccount") LoginAttemptService loginAttemptService) {
        this.userRepo = userRepo;
        this.loginAttemptService = loginAttemptService;
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (loginAttemptService.isBlocked(username)) {
            LOGGER.warn("Username {} is blocked...", username);
            throw new AccountSecurityException("Your account is blocked. Too many failed login attempts");
        }

        Optional<UserDO> userOptional = Optional.ofNullable(userRepo.findByUsername(username));

        userOptional.ifPresent(userDO -> isAdmin = userDO.getName().contains("admin"));

        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("Username & Password doesn't exist!");
        }

        return new User(userOptional.get().getUsername(), userOptional.get().getPassword(), authorizeNewUser());
    }

    private List<GrantedAuthority> authorizeNewUser() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String postfix = isAdmin ? AUTHORITY_ADMIN : AUTHORITY_USER;
        authorities.add(new SimpleGrantedAuthority(USER_ROLE_PREFIX + postfix));

        return authorities;
    }
}
