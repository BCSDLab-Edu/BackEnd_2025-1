package com.example.bcsd.exception;

public class BoardNotFoundException extends CustomException {
    public BoardNotFoundException(ErrorCode errorcode) {
        super(errorcode);
    }
}
