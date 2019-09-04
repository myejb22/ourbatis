package com.xiaad.ourbatis.core.exception;

public class ParameterHandlerException extends RuntimeException {

    public ParameterHandlerException() {
        super();
    }

    public ParameterHandlerException(String message) {
        super(message);
    }

    public ParameterHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParameterHandlerException(Throwable cause) {
        super(cause);
    }
}
