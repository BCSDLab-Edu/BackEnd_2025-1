package com.example.bcsd.member.service;

import org.springframework.stereotype.Component;

@Component
public class MemberValidator {

    private static final Integer PASSWORD_MAX_LENGTH = 255;
    private static final Integer NAME_MAX_LENGTH = 100;
    private static final Integer EMAIL_MAX_LENGTH = 100;

    public void validatePassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요");
        }
        if (password.length() > PASSWORD_MAX_LENGTH) {
            throw new IllegalArgumentException(String.format("비밀번호은 최대 %d자리 입니다.", PASSWORD_MAX_LENGTH));
        }
    }

    public void validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("이름을 입력해주세요");
        }
        if (name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(String.format("이름은 최대 %d자리 입니다.", NAME_MAX_LENGTH));
        }
    }

    public void validateEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("이메일을 입력해주세요");
        }
        if (email.length() > EMAIL_MAX_LENGTH) {
            throw new IllegalArgumentException(String.format("이메일은 최대 %d자리 입니다.", EMAIL_MAX_LENGTH));
        }
    }
}
