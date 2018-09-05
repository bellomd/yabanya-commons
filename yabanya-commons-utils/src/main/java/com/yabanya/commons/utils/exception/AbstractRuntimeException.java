package com.yabanya.commons.utils.exception;

public abstract class AbstractRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 6755983711857560606L;

    public AbstractRuntimeException(final String message) { super(message); }
    public AbstractRuntimeException(final Throwable cause) { super(cause); }
    public AbstractRuntimeException(final String message, final Throwable cause) { super(message, cause); }
}
