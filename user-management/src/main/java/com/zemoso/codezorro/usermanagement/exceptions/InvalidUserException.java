package com.zemoso.codezorro.usermanagement.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidUserException extends Exception {
    String email;
    String reason;

    @Override
    public String getMessage() {
        return String.format("Login attempt with mail %s failed because of %s", email,reason);
    }
}
