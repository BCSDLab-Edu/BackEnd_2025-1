package com.example.bcsd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public class ErrorDto {
    private final String message;
    private final int StatusCode;
}
