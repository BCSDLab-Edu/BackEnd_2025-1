package com.example.bcsd.member.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateMemberRequest(
    @NotNull String name,
    @NotNull String email
) {
    
}
