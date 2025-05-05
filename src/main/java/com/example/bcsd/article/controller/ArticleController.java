package com.example.bcsd.article.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bcsd.article.dto.CreateArticleRequest;
import com.example.bcsd.article.service.ArticleService;

@RestController
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/articles")
    public ResponseEntity<Void> CreateArticle(
        @RequestBody CreateArticleRequest request
    ) {
        articleService.CreateArticle(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
