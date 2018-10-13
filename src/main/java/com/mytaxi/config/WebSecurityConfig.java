package com.mytaxi.config;

import com.mytaxi.handler.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import static com.mytaxi.constant.Constants.*;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.config
 * USER      : sean
 * DATE      : 03-Wed-Oct-2018
 * TIME      : 22:46
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private PersistentTokenRepository persistenceTokenRepository;
    private UserDetailsService userDetailsService;
    private CustomAccessDeniedHandler accessDeniedHandler;


    @Autowired
    public WebSecurityConfig(PersistentTokenRepository persistenceTokenRepository, @Qualifier("userdetails") UserDetailsService userDetailsService,
                             CustomAccessDeniedHandler accessDeniedHandler) {
        super();
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
        this.persistenceTokenRepository = persistenceTokenRepository;
        this.userDetailsService = userDetailsService;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler myFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler();
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .headers().frameOptions().disable() //h2 DB won't work with frameOptions & CSRF enabled.
                .and()
                .authorizeRequests()
                .antMatchers("/swagger-ui.html", "/").hasRole(AUTHORITY_ADMIN)
                .antMatchers(SECURITY_PERMIT_ALL_URLS).permitAll()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .defaultSuccessUrl(URL_LOGIN_SUCCESSFUL, true)
                .passwordParameter(PARAM_PASS_FIELD)
                .usernameParameter(PARAM_USER_FIELD)
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and()
                .logout()
                .logoutUrl(URL_LOGOUT)
                .logoutSuccessUrl("/")
                .deleteCookies(COOKIES_SESSION).clearAuthentication(true)
                .and()
                .rememberMe().rememberMeCookieName(COOKIE_REMEMBER_ME).tokenValiditySeconds(1200)
                .tokenRepository(persistenceTokenRepository)
                .alwaysRemember(true)
                .useSecureCookie(true).userDetailsService(userDetailsService);
    }
}