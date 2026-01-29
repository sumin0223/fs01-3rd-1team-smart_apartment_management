package com.jjld.global.exception.complex;

import com.jjld.global.exception.BusinessException;
import com.jjld.global.exception.ErrorCode;

public class ApartmentComplexNotFoundException extends BusinessException {
    public ApartmentComplexNotFoundException() {
        super(ErrorCode.APARTMENT_COMPLEX_NOT_FOUND);
    }

    public ApartmentComplexNotFoundException(String message) {
        super(ErrorCode.APARTMENT_COMPLEX_NOT_FOUND, message);
    }
}