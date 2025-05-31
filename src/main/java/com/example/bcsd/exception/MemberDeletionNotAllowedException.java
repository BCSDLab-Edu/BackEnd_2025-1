package com.example.bcsd.exception;

public class MemberDeletionNotAllowedException extends CustomException {
    public MemberDeletionNotAllowedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
