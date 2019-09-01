package com.hkdemircan.todoapp.constant;

public class SecurityConstants {

    public static final String SECRET = "secret";
    public static final long EXPIRATION_TIME = 423_000_000; // 5 gun
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES = "Authorities";
    public static final String SIGN_UP_URL = "/user/sign-up";
    public static final String LOGIN_URL = "/user/login";
}
