package com.example.bcsd.dto.ResponseDto;

import com.example.bcsd.model.Member;

public record MemberResponseDto(
        Long id,
        String name,
        String email) {

    public static MemberResponseDto from(Member member) {
        return new MemberResponseDto(
                member.getId(),
                member.getName(),
                member.getEmail()
        );
    }
}
