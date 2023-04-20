package com.standard.Common.Exception;

import org.springframework.security.core.AuthenticationException;

public class UserCountLockException extends AuthenticationException {
    public UserCountLockException(String msg) {
        super(msg);
    }


}
