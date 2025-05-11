package com.example.bcsd;

import java.time.LocalDateTime;

public class Article {
    int id;
    String title;
    String author;
    LocalDateTime date;
    String content;

    public Article(int id, String title, String author, LocalDateTime date, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
        this.content = content;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}