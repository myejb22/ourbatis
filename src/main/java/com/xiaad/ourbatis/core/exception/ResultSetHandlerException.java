package com.xiaad.ourbatis.core.exception;

public class ResultSetHandlerException extends RuntimeException {
    public ResultSetHandlerException() {
        super();
    }

    public ResultSetHandlerException(String message) {
        super(message);
    }

    public ResultSetHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResultSetHandlerException(Throwable cause) {
        super(cause);
    }
}
