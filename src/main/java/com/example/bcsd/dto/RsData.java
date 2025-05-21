package com.example.bcsd.dto;

public class RsData<T> {

    private boolean success;
    private String message;
    private T data;

    private RsData(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> RsData<T> success(String message, T data) {
        return new RsData<>(true, message, data);
    }

    public static <T> RsData<T> error(String message) {
        return new RsData<>(false, message, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}

