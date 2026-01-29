package com.jjld.global.exception.admin;

import com.jjld.global.exception.BusinessException;
import com.jjld.global.exception.ErrorCode;

public class InvalidCurrentPassword extends BusinessException {
    public InvalidCurrentPassword() {
        super(ErrorCode.INVALID_CURRENT_PASSWORD);
    }

    public InvalidCurrentPassword(String message) {
        super(ErrorCode.INVALID_CURRENT_PASSWORD, message);
    }
}
