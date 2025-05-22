package com.example.bcsd.exception;

import com.example.bcsd.dto.RsData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<RsData<Object>> handleCustomException(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();

        log.error("에러 코드 : {}\n내용 : {}", errorCode.getCode(), errorCode.getMessage());

        return ResponseEntity
                .status(errorCode.getCode())
                .body(RsData.error(errorCode.getMessage()));
    }
}
