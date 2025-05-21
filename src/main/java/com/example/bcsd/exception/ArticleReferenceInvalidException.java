package com.example.bcsd.exception;

public class ArticleReferenceInvalidException extends CustomException {
    public ArticleReferenceInvalidException(ErrorCode errorcode) {
        super(errorcode);
    }
}
