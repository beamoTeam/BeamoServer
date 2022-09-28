package com.example.beamo.jwt;

public interface JwtProperties {
    String SECRET = "{}"; //(2)
    int EXPIRATION_TIME =  60 * 60 * 24 * 10 * 1000; //(3)
    String TOKEN_PREFIX = "Bearer "; //(4)
    String HEADER_STRING = "Authorization"; //(5)
}
