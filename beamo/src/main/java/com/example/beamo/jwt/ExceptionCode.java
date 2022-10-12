package com.example.beamo.jwt;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    UNKNOWN_ERROR("0110", "UNKNOWN_ERROR"),
    ACCESS_DENIED("1001", "access type"),
    NOT_FOUNT_USER("1000", "Not Found User"),
    EXPIRED_TOKEN("2000", "Expired type"),
    UNSUPPORTED_TOKEN("2001", "Unsupported Type"),
    WRONG_TOKEN("3000", "Wrong Type"),
    NOT_ALLOW_ACCESS("4000", "Access is Denied"),
    IllegalArgumentException("4040", "IllegalArgumentException Grant"),

    PERMISSION_DENIED("5000", "Permission Denied "),
    CUSTOM_EXCEPTION_SAMPLE("CODE", "Custom Exception Sample"),
    ;

    private final String code;
    private final String message;

    ExceptionCode(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}