package com.jjld.global.exception.admin;

import com.jjld.global.exception.BusinessException;
import com.jjld.global.exception.ErrorCode;

public class PasswordMismatchException extends BusinessException {
    public PasswordMismatchException() {
        super(ErrorCode.PASSWORD_MISMATCH);
    }

    public PasswordMismatchException(String message) {
        super(ErrorCode.PASSWORD_MISMATCH, message);
    }
}
