package com.example.bcsd;

import java.time.LocalDate;

public class Post {
    protected Integer post_num;
    protected String title;
    protected String text;
    protected LocalDate creation_date;
    protected LocalDate modification_date;

    public Post() {
        Integer post_num;
        String title;
        String text;
        LocalDate creation_date;
        LocalDate modification_date = null;
    }

    public Integer getPost_num() {
        return post_num;
    }

    public void setPost_num(Integer post_num) {
        this.post_num = post_num;
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

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public LocalDate getModification_date() {
        return modification_date;
    }

    public void setModification_date(LocalDate modification_date) {
        this.modification_date = modification_date;
    }
}
