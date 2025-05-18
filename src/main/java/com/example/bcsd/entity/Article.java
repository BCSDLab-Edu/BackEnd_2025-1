package com.example.bcsd.entity;

import java.time.LocalDateTime;

public class Article {

    private Integer id;
    private Integer authorId;
    private Integer boardId;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Article(
        Integer id,
        Integer authorId,
        Integer boardId,
        String title,
        String content,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate
    ) {
        this.id = id;
        this.authorId = authorId;
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
