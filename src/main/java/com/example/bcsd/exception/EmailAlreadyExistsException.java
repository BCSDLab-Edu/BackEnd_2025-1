package com.example.bcsd.exception;

public class EmailAlreadyExistsException extends CustomException {
    public EmailAlreadyExistsException(ErrorCode errorCode) {
        super(errorCode);
    }
}
