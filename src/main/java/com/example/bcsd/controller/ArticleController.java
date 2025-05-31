package com.example.bcsd.controller;

import com.example.bcsd.dto.ArticleListResponseDto;
import com.example.bcsd.dto.ArticleRequestDto;
import com.example.bcsd.dto.ArticleResponseDto;
import com.example.bcsd.service.ArticleService;
import jakarta.validation.Valid;
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

    @GetMapping(params = "boardId")
    public List<ArticleResponseDto> getArticlesByBoardId(@RequestParam Long boardId) {
        return articleService.getArticlesByBoardId(boardId);
    }

    @PostMapping
    public ArticleResponseDto createArticle(@RequestBody @Valid ArticleRequestDto dto) {
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
