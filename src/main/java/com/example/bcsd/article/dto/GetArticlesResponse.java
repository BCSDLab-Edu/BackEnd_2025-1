package com.example.bcsd.article.dto;

import java.util.List;

import com.example.bcsd.article.model.Article;

public record GetArticlesResponse(List<GetArticleResponse> articles, Integer articleCount) {
    public static GetArticlesResponse from(List<Article> articles) {
        List<GetArticleResponse> articlesForResponse = articles.stream().map(article -> GetArticleResponse.from(article)).toList();
        Integer articleCount = articles.size();
        return new GetArticlesResponse(articlesForResponse, articleCount);
    }
}