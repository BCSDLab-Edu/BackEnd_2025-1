package com.example.bcsd;

public class Article {
    private String title;
    private String content;
    private Long id;

    public Article(String title, String content, Long id) { // 생성자
        this.title = title;
        this.content = content;
        this.id = id;
    }

    // getter,setter 설정
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
