package com.example.bcsd.controller;

import com.example.hellospring.dto.ArticleListResponseDto;
import com.example.hellospring.dto.ArticleRequestDto;
import com.example.hellospring.dto.ArticleResponseDto;
import com.example.hellospring.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<ArticleListResponseDto> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public ArticleResponseDto getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

    @PostMapping
    public ArticleResponseDto createArticle(@RequestBody ArticleRequestDto dto) {
        return articleService.createArticle(dto);
    }

    @PutMapping("/{id}")
    public ArticleResponseDto updateArticle(@PathVariable Long id, @RequestBody ArticleRequestDto dto) {
        return articleService.updateArticle(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }
}
