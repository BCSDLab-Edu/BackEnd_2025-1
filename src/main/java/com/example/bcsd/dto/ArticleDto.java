package com.example.bcsd.dto;

public record ArticleDto(
        Long boardId,
        Long authorId,
        String title,
        String content
) {
}
