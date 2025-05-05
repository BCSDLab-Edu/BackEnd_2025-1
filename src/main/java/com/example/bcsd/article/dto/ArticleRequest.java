package com.example.bcsd.article.dto;

import com.example.bcsd.article.model.Article;

public record ArticleRequest(
    String name,
    String description
) {
    public Article toArticle(){
        Article article = new Article();
        article.setName(name);
        article.setDescription(description);
        return article;
    }
}
