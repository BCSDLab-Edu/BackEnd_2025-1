package com.example.bcsd.dto.request;

import com.example.bcsd.dto.deserializer.PasswordBcryptDeserializer;
import com.example.bcsd.model.Member;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MemberRequest(
        @NotNull(message = "이름이 누락되었습니다.")
        @Size(max = 100, message = "이름의 최대 길이를 벗어났습니다. (최대 길이 : 100자)")
        String name,

        @NotNull(message = "이메일이 누락되었습니다.")
        @Size(max = 100, message = "이메일의 최대 길이를 벗어났습니다. (최대 길이 : 100자)")
        String email,

        @NotNull(message = "비밀번호가 누락되었습니다.")
        @Size(max = 255, message = "비밀번호의 최대 길이를 벗어났습니다. (최대 길이 : 100자)")
        @JsonDeserialize(using = PasswordBcryptDeserializer.class)
        String password
) {
    public MemberRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
