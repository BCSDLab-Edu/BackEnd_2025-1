package com.example.bcsd.exception;

public class MemberNotFoundException extends CustomException {
    public MemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
