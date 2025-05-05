package com.example.bcsd.model;

import com.example.bcsd.controller.dto.ArticleRequest;

import java.time.LocalDateTime;

public class Article {
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Article update(ArticleRequest request) {
        title = request.getTitle();
        content = request.getContent();
        updatedAt = LocalDateTime.now();

        return this;
    }
}
