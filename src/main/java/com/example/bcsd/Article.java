package com.example.bcsd;

public class Article {
    private String description;
    private Long id;

    public Article(){

    }

    public Article(String description, Long id){
        this.description = description;
        this.id = id;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
