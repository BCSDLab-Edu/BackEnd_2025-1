package com.example.bcsd.article.dto;

import java.util.List;
import java.util.stream.IntStream;

import com.example.bcsd.article.model.Article;

public record GetArticlesResponse(List<GetArticleResponse> articles, Integer articleCount) {
    public static GetArticlesResponse from(List<Article> articles, List<String> names) {
        Integer articleCount = articles.size();
        List<GetArticleResponse> articlesForResponse = IntStream.range(0, articleCount).mapToObj(i -> GetArticleResponse.from(articles.get(i), names.get(i))).toList();
        return new GetArticlesResponse(articlesForResponse, articleCount);
    }
}