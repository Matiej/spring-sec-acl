package com.matiej.springsec_acl.global.exceptions;

public class EmailExistsException extends Throwable {
    public EmailExistsException(final String message) {
        super(message);
    }
}
