package com.example.bcsd.exception;

public class BoardDeletionNotAllowedException extends CustomException {
    public BoardDeletionNotAllowedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
