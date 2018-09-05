package com.yabanya.commons.utils.exception;

public class EntityNotFoundException extends AbstractRuntimeException {

    private static final long serialVersionUID = -6445529691541310317L;

    public EntityNotFoundException(final String message) { super(message); }
    public EntityNotFoundException(final Throwable cause) { super(cause); }
    public EntityNotFoundException(final String message, final Throwable cause) { super(message, cause); }
}
