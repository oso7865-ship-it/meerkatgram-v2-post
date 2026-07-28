package com.meerkatgramv2post.global.error.custom;

public class ResourceAuthMismatchException extends RuntimeException {
    public ResourceAuthMismatchException(String message) {
        super(message);
    }
}
