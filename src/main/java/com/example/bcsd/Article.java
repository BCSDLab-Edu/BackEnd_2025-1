package com.example.bcsd;

import java.time.LocalDateTime;

public class Article {
    int id;
    int memberId;
    int boardId;
    String title;
    String author;
    String content;
    LocalDateTime createdDate;
    LocalDateTime editedDate;

    public Article(int id, int memberId, int boardId, String title, String author, String content, LocalDateTime createdDate, LocalDateTime editedDate) {
        this.id = id;
        this.memberId = memberId;
        this.boardId = boardId;
        this.title = title;
        this.author = author;
        this.content = content;
        this.createdDate = createdDate;
        this.editedDate = editedDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }

    public int getBoardId() { return boardId; }
    public void setBoardId(int boardId) { this.boardId = boardId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }

    public LocalDateTime getEditedDate() { return editedDate; }
    public void setEditedDate(LocalDateTime editedDate) { this.editedDate = editedDate; }
}