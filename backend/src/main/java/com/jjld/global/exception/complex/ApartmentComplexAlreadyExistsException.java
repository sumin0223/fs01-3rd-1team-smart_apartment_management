package com.jjld.global.exception.complex;

import com.jjld.global.exception.BusinessException;
import com.jjld.global.exception.ErrorCode;

public class ApartmentComplexAlreadyExistsException extends BusinessException {
    public ApartmentComplexAlreadyExistsException() {
        super(ErrorCode.APARTMENT_COMPLEX_ALREADY_EXISTS);
    }

    public ApartmentComplexAlreadyExistsException(String message) {
        super(ErrorCode.APARTMENT_COMPLEX_ALREADY_EXISTS, message);
    }
}
