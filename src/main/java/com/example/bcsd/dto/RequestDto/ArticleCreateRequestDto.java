package com.example.bcsd.dto.RequestDto;

public class ArticleCreateRequestDto {
    private String title;
    private String content;

    public ArticleCreateRequestDto() {
    }

    public ArticleCreateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

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
}
