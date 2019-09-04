package com.xiaad.ourbatis.core.exception;

public class StatementHandlerException extends RuntimeException {

    public StatementHandlerException() {
        super();
    }

    public StatementHandlerException(String message) {
        super(message);
    }

    public StatementHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatementHandlerException(Throwable cause) {
        super(cause);
    }
}
