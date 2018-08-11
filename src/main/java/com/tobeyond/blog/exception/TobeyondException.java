package com.tobeyond.blog.exception;

public class TobeyondException extends RuntimeException {

    public TobeyondException() {
    }

    public TobeyondException(String message) {
        super(message);
    }

    public TobeyondException(String message, Throwable cause) {
        super(message, cause);
    }

    public TobeyondException(Throwable cause) {
        super(cause);
    }

}
