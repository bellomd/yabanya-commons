package com.yabanya.commons.utils.exception;

public class NullIdException extends AbstractRuntimeException {

    private static final long serialVersionUID = 2436671679995874378L;

    public NullIdException(final String message) { super(message); }
    public NullIdException(final Throwable cause) { super(cause); }
    public NullIdException(final String message, final Throwable cause) { super(message, cause); }
}