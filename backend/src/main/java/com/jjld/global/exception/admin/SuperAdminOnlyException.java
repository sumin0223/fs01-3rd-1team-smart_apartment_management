package com.jjld.global.exception.admin;

import com.jjld.global.exception.BusinessException;
import com.jjld.global.exception.ErrorCode;

public class SuperAdminOnlyException extends BusinessException {
    public SuperAdminOnlyException() {
        super(ErrorCode.SUPER_ADMIN_ONLY);
    }

    public SuperAdminOnlyException(String message) {
        super(ErrorCode.SUPER_ADMIN_ONLY, message);
    }
}
