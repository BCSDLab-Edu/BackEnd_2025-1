package com.example.bcsd.member.dto;

import jakarta.validation.constraints.NotNull;

import com.example.bcsd.member.model.Member;
import com.example.bcsd.member.util.UserUtil;;

public record RegisterRequest(
    @NotNull String name,
    @NotNull String email,
    @NotNull String password
) {
    public Member toMember() {
        Member member = new Member();
        
        member.setName(name);
        member.setEmail(email);
        member.setPassword(UserUtil.Pass2Hash(password));
        
        return member;
    }
}
