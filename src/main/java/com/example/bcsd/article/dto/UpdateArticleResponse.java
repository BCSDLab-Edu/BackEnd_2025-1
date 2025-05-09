package com.example.bcsd.article.dto;

import com.example.bcsd.article.model.Article;

public record UpdateArticleResponse(
        String name,
        String description
) {
    public static UpdateArticleResponse from(Article article) {
        return new UpdateArticleResponse(article.getTitle(), article.getContent());
    }
}
