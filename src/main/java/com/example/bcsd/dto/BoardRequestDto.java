package com.example.bcsd.dto;

import jakarta.validation.constraints.NotBlank;

public class BoardRequestDto {
    private Long id;

    @NotBlank(message = "이름이 비어있습니다.")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}