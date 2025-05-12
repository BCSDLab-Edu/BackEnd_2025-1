package com.example.bcsd;

public class Article {
    private final Long id;
    private String title;
    private String content;

    public Article(Long id, String title, String content) { // 생성자
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getId() {
        return id;
    }
}
