package com.example.bcsd.article.dto;

import java.util.List;

import com.example.bcsd.article.model.Article;

public class GetArticlesResponse {
    private List<Article> articles;
    private Integer articleCount;

    public List<Article> getArticles() {
        return this.articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public Integer getArticleCount() {
        return this.articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }
}
