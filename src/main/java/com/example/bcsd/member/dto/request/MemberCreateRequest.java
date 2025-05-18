package com.example.bcsd.member.dto.request;

import com.example.bcsd.member.entity.Member;

public record MemberCreateRequest(
    String name,
    String email,
    String password
) {
    public Member toMember() {
        return new Member(name, email, password);
    }
}
