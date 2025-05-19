package com.example.bcsd.controller;

import com.example.bcsd.dto.ArticleDto;
import com.example.bcsd.dto.ArticleResponseDto;
import com.example.bcsd.service.ArticleService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ArticleResponseDto>> getArticles(@RequestParam Long boardId) {
        return ResponseEntity.ok(articleService.findByBoardId(boardId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> getArticle(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ArticleResponseDto> create(@RequestBody ArticleDto dto) {
        return ResponseEntity.ok(articleService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> update(@PathVariable Long id, @RequestBody ArticleDto dto) {
        return ResponseEntity.ok(articleService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        articleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
