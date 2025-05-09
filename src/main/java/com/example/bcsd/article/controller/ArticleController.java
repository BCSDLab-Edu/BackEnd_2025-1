package com.example.bcsd.article.controller;

import java.util.List;

import com.example.bcsd.article.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bcsd.article.model.Article;
import com.example.bcsd.article.service.ArticleService;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseEntity<Void> CreateArticle(
            @RequestBody @Valid CreateArticleRequest request
    ) {
        articleService.CreateArticle(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<GetArticlesResponse> GetArticles() {
        List<Article> articles = articleService.getArticles();
        GetArticlesResponse res = GetArticlesResponse.from(articles);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetArticleResponse> GetArticle(
            @PathVariable("id") Long id
    ) {
        Article article = articleService.getArticle(id);

        GetArticleResponse res = GetArticleResponse.from(article);
        return ResponseEntity.ok().body(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateArticleResponse> UpdateArticle(
            @PathVariable("id") Long id,
            @RequestBody UpdateArticleRequest request
    ) {
        UpdateArticleResponse res = articleService.updateArticle(id, request);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteArticle(
            @PathVariable("id") Long id
    ) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}
