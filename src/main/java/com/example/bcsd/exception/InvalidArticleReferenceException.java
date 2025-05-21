package com.example.bcsd.exception;

public class InvalidArticleReferenceException extends CustomException {
    public InvalidArticleReferenceException(ErrorCode errorCode) {
        super(errorCode);
    }
}
