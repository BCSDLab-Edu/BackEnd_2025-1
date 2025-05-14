package com.example.bcsd.dto.resopnse;

import com.example.bcsd.model.Article;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ArticleViewResponse(
        String title,
        String author,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ArticleViewResponse fromEntity(Article article, String authorName) {
        return ArticleViewResponse.builder()
                .title(article.getTitle())
                .author(authorName)
                .content(article.getContent())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
    }
}
