package com.xiaad.ourbatis.core.exception;

public class OurBatisConnectionException extends RuntimeException {
    public OurBatisConnectionException() {
        super();
    }

    public OurBatisConnectionException(String message) {
        super(message);
    }

    public OurBatisConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public OurBatisConnectionException(Throwable cause) {
        super(cause);
    }
}
