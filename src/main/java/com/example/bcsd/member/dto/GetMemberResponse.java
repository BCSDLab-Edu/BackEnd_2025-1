package com.example.bcsd.member.dto;

import com.example.bcsd.member.model.Member;

public record GetMemberResponse(
    Long id,
    String name,
    String email
) {
    public static GetMemberResponse from(Member member) {
        return new GetMemberResponse(member.getId(), member.getName(), member.getEmail());
    }
}