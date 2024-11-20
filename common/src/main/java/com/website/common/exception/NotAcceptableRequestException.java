package com.website.common.exception;

public class NotAcceptableRequestException extends RuntimeException{
    public NotAcceptableRequestException(String message) {
        super(message);
    }
}
