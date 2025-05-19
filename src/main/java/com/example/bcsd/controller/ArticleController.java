package com.example.bcsd.controller;

import com.example.bcsd.model.Article;
import com.example.bcsd.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // 게시글 생성
    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleService.createArticle(article);
    }

    // 전체 또는 boardId별 게시글 조회
    @GetMapping
    public List<Article> getArticles(@RequestParam(value = "boardId", required = false) Long boardId) {
        if (boardId != null) {
            return articleService.getArticlesByBoardId(boardId);
        }
        return articleService.getAllArticles();
    }

    // 게시글 단건 조회
    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable Long id, @RequestBody Article updatedArticle) {
        return articleService.updateArticle(id, updatedArticle);
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return "삭제 완료!";
    }
}
