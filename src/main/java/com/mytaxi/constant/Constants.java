package com.mytaxi.constant;

import java.util.Arrays;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.constant
 * USER      : sean
 * DATE      : 04-Thu-Oct-2018
 * TIME      : 00:05
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public class Constants {
    public static final String AUTHORITY_USER = "USER";
    public static final String AUTHORITY_ADMIN = "ADMIN";

    public static final String USER_ROLE_PREFIX = "ROLE_";
    public static final String URL_LOGOUT = "/logout";
    public static final String URL_LOGIN_SUCCESSFUL = "/swagger-ui.html#/";
    public static final String COOKIES_SESSION = "JSESSIONID";
    public static final String PARAM_PASS_FIELD = "password";
    public static final String PARAM_USER_FIELD = "username";
    public static final String COOKIE_REMEMBER_ME = "vhiT0WjOaEVBeoXxvrTuTxQEWYHZVn0eafjYl3atd8dhp9T";
    public static final String URL_LOGIN_ERROR_TRUE = "/error?error=true";
    private static final String URL_INVALID_SESSION = "/error";
    private static final String URL_RESOURCES_CSS = "css/**";
    private static final String URL_RESOURCES_PUB = "public/**";
    private static final String URL_RESOURCES_STATIC = "static/**";
    private static final String URL_RESOURCES_WEBJAR = "/webjars/**";
    private static final String URL_RESOURCES_SWAGGER = "/swagger-resources/**";
    private static final String URL_RESOURCES_H2 = "/h2";
    public static final String[] EXTERNAL_URL_RESOURCES = {URL_RESOURCES_CSS, URL_RESOURCES_PUB,
            URL_RESOURCES_STATIC, URL_RESOURCES_WEBJAR, URL_RESOURCES_H2}; //URL_ROOT
    private static final String URL_INTERNAL_RESOURCES_PUB = "classpath:/public/";
    public static final String SECURITY_PERMIT_ALL_URLS = Arrays.toString(
            new String[]{URL_RESOURCES_H2, URL_INTERNAL_RESOURCES_PUB, URL_INVALID_SESSION, URL_RESOURCES_SWAGGER, "/", "/error", "/templates/404.html", "/static/**"})
            .replace("[", "").replace("]", "");
    private static final String URL_INTERNAL_RESOURCES_STATIC = "classpath:/static/";
    private static final String URL_INTERNAL_RESOURCES_WEBJAR = "classpath:/webjars/";
    public static final String[] INTERNAL_URL_RESOURCES = {URL_INTERNAL_RESOURCES_PUB,
            URL_INTERNAL_RESOURCES_STATIC, URL_INTERNAL_RESOURCES_WEBJAR};
}
