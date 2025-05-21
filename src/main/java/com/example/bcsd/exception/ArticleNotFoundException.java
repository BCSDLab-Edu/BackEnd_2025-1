package com.example.bcsd.exception;

public class ArticleNotFoundException extends CustomException {
    public ArticleNotFoundException(ErrorCode errorcode) {
        super(errorcode);
    }
}
