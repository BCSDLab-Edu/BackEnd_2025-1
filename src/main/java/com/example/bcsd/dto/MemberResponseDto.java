package com.example.bcsd.dto;

import com.example.bcsd.domain.Member;

public class MemberResponseDto {
    Long id;
    String name;
    String email;
    String password;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.password = member.getPassword();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
