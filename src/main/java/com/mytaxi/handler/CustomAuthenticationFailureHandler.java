package com.mytaxi.handler;

import com.mytaxi.constant.Constants;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.auth
 * USER      : sean
 * DATE      : 04-Thu-Oct-2018
 * TIME      : 03:56
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {


    /**
     * Performs the redirect or forward to the {@code defaultFailureUrl} if set, otherwise
     * returns a 401 error code.
     * <p>
     * If redirecting or forwarding, {@code saveException} will be called to cache the
     * exception for use in the target view.
     *
     * @param request
     * @param response
     * @param exception
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        setDefaultFailureUrl(Constants.URL_LOGIN_ERROR_TRUE);
        super.onAuthenticationFailure(request, response, exception);

        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, "Authentication Failure...");
    }
}
