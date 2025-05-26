package com.example.bcsd;

import java.time.LocalDate;

public class Post {
    private Integer postNum;
    private String title;
    private String text;
    private LocalDate creationDate;
    private LocalDate modificationDate;

    public Post(
            Integer postNum,
            String title,
            String text,
            LocalDate creationDate,
            LocalDate modificationDate
    ) {
        this.postNum = postNum;
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public Integer getPostNum() {
        return postNum;
    }

    public void setPost_num(Integer postNum) {
        this.postNum = postNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }
}
