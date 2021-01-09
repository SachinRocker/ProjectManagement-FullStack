package com.simpleUI.projectmanagementtool.security;

public class SecurityConstants {
	
	public static final String SIGN_UP_URLS = "/users/**";
	public static final String H2_URL = "h2-console/**";
	public static final String SECRET = "SecretKeyToGenJwts";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final Long EXPIRATION_TIME = 30_000l; //30 second

}
