package com.example.bcsd.article.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Object GetArticles(
        @RequestParam(value = "boardId", required = false) Long boardId
    ) {
        if (boardId == null) {
            return ResponseEntity.ok().body(articleService.GetArticles());
        } else {
            GetArticlesResponse articles = articleService.GetArticlesByBoardId(boardId);
            return ResponseEntity.ok().body(articles.articles());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetArticleResponse> GetArticle(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok().body(articleService.GetArticle(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateArticleResponse> UpdateArticle(
            @PathVariable("id") Long id,
            @RequestBody UpdateArticleRequest request
    ) {
        UpdateArticleResponse res = articleService.UpdateArticle(id, request);

        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteArticle(
            @PathVariable("id") Long id
    ) {
        articleService.DeleteArticle(id);

        return ResponseEntity.noContent().build();
    }
}
