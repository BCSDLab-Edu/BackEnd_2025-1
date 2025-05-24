package com.example.bcsd.domain;

import java.time.LocalDateTime;

public class Article {
    private final Long id;
    private final Long authorId;
    private Long boardId;
    private String title;
    private String content;
    private final LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Article(Long id, Long authorId, Long boardId,
                   String title, String content,
                   LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.authorId = authorId;
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Long getId() {
        return id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void update(String newTitle, String newContent, Long newBoardId, LocalDateTime newModifiedDate) {
        this.title = newTitle;
        this.content = newContent;
        this.boardId = newBoardId;
        this.modifiedDate = newModifiedDate;
    }
}