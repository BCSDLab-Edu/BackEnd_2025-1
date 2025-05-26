package com.example.bcsd.article.dto;

public record UpdateArticleRequest(
        String title,
        Long boardId,
        String content
) {
}
