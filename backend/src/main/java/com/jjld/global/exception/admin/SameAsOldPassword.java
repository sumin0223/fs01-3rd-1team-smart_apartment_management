package com.jjld.global.exception.admin;

import com.jjld.global.exception.BusinessException;
import com.jjld.global.exception.ErrorCode;

public class SameAsOldPassword extends BusinessException {
    public SameAsOldPassword() {
        super(ErrorCode.SAME_AS_OLD_PASSWORD);
    }

    public SameAsOldPassword(String message) {
        super(ErrorCode.SAME_AS_OLD_PASSWORD, message);
    }
}
