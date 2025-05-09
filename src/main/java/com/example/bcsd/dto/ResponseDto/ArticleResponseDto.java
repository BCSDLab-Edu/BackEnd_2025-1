package com.example.bcsd.dto.ResponseDto;

import com.example.bcsd.model.Article;

public class ArticleResponseDto {
    private String title;
    private String author;
    private String date;
    private String content;

    public ArticleResponseDto(String title, String author, String date, String content) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public static ArticleResponseDto from(Article article) {
        return new ArticleResponseDto(
                article.getTitle(),
                article.getMember().getName(),
                article.getCreatedAt().toString(),
                article.getContent());
    }
}
