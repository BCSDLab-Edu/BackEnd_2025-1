package com.example.bcsd.domain;

public record Member(
        Long id,
        String name,
        String email,
        String password
) {
}
