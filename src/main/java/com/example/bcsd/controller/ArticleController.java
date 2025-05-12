package com.example.bcsd.controller;

import com.example.bcsd.service.ArticleService;
import com.example.bcsd.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    // 의존성 주입
    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleService.createArticle(article);
    }

    @GetMapping
    public Collection<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable Long id, @RequestBody Article updatedArticle) {
        return articleService.updateArticle(id, updatedArticle);
    }

    @DeleteMapping("/{id}")
    public String deleteArticle(@PathVariable Long id) {
        return articleService.deleteArticle(id);
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

}
