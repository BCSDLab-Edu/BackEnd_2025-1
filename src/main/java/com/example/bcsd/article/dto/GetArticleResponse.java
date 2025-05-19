package com.example.bcsd.article.dto;

import com.example.bcsd.article.model.Article;

import java.time.LocalDateTime;

public record GetArticleResponse(Long id, String title, String author, String date, String content) {
    public static GetArticleResponse from(Article article, String author) {
        LocalDateTime date;
        if (article.getEditedDate() == null) {
            date = article.getCreatedDate();
        } else {
            date = article.getEditedDate();
        }

        return new GetArticleResponse(article.getId(), article.getTitle(), author, date.toString(), article.getContent());
    }
}