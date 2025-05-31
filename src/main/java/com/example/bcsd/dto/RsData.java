package com.example.bcsd.dto;

public class RsData {
    private final int code;
    private final String message;

    public RsData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static RsData of(int code, String message) {
        return new RsData(code, message);
    }
}

