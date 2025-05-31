package com.example.bcsd.exception;

public class InvalidRequestBodyException extends CustomException {
    public InvalidRequestBodyException(ErrorCode errorCode) {
        super(errorCode);
    }
}
