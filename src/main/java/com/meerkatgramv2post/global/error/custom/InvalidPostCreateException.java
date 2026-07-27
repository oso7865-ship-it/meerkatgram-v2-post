package com.meerkatgramv2post.global.error.custom;

public class InvalidPostCreateException extends RuntimeException {
    public InvalidPostCreateException(String message) {
        super(message);
    }
}
