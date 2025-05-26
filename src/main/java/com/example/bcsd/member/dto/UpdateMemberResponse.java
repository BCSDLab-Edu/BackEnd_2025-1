package com.example.bcsd.member.dto;

import com.example.bcsd.member.model.*;;;

public record UpdateMemberResponse(
        String name,
        String email
) {
    public static UpdateMemberResponse from(Member member) {
        return new UpdateMemberResponse(member.getName(), member.getEmail());
    }
}
