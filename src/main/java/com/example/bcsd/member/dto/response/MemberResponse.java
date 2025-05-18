package com.example.bcsd.member.dto.response;

public record MemberResponse(
    Integer id,
    String name,
    String email
) {
    public static MemberResponse of(Integer id, String name, String email) {
        return new MemberResponse(id, name, email);
    }
}
