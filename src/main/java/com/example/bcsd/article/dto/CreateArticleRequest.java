package com.example.bcsd.article.dto;

import com.example.bcsd.article.model.Article;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreateArticleRequest(
        @NotNull(message = "제목은 필수로 입력해야 합니다.")
        String title,

        @NotNull
        Long authorId,

        @NotNull
        Long boardId,

        @NotNull
        @Size(max = 1000, message = "본문은 최대 1000자 까지 입력 가능합니다.")
        String content
) {
    public Article toArticle() {
        Article article = new Article();

        article.updateTitle(title);
        article.updateContent(content);
        article.updateAuthorId(authorId);
        article.updateBoardId(boardId);
        article.updateCreatedDate(LocalDateTime.now());

        return article;
    }
}
