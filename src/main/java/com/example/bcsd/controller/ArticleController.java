package com.example.bcsd.controller;

import com.example.bcsd.domain.Article;
import com.example.bcsd.dto.ArticleRequestDto;
import com.example.bcsd.dto.ArticleResponseDto;
import com.example.bcsd.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(params = "boardId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Article>> getAllArticlesRaw(@RequestParam Long boardId) {
        List<Article> filtered = articleService.getAllArticles().stream()
                .filter(a -> a.getBoardId().equals(boardId))
                .collect(Collectors.toList());
        return ResponseEntity.ok(filtered);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> getArticleJson(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> create(@RequestBody ArticleRequestDto dto) {
        Article created = articleService.addAndReturn(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleRequestDto dto) {
        articleService.updateArticle(id, dto);
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.ok("게시글이 삭제되었습니다.");
    }

    @GetMapping(params = "boardId", produces = MediaType.APPLICATION_JSON_VALUE, path = "/dto")
    public ResponseEntity<List<ArticleResponseDto>> getAllByBoardId(@RequestParam Long boardId) {
        return ResponseEntity.ok(articleService.getAllByBoardId(boardId));
    }
}
