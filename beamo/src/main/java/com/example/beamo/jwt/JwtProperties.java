package com.example.beamo.jwt;


import org.springframework.beans.factory.annotation.Value;

public interface JwtProperties {

    int EXPIRATION_TIME = 1;
//            60 * 60 * 24 * 10 * 1000; //(3)
    String TOKEN_PREFIX = "Bearer "; //(4)
    String HEADER_STRING = "Authorization"; //(5)
}
