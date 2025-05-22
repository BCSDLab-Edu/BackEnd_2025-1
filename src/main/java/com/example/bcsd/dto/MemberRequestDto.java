package com.example.bcsd.dto;

import jakarta.validation.constraints.NotBlank;

public class MemberRequestDto {
    private Long id;

    @NotBlank(message = "이름이 비어있습니다.")
    private String name;

    @NotBlank(message = "이메일이 비어있습니다.")
    private String email;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String password;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}