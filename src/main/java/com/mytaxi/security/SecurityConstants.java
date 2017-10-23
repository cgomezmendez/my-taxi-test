package com.mytaxi.security;

abstract public class SecurityConstants
{
    public static final String SECRET = "LGuZjp87yakqhJ4tRMAb5rKDrVbq8NoybhDc5zAVUkLbo87UwzWYSAMRoL7ko9Aa\n";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
