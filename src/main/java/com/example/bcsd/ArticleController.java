package com.example.bcsd;

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
    public List<Article> getAll() {
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public Article getOne(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

    @PostMapping
    public void create(@RequestBody Article article) {
        articleService.addArticle(article);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Article article) {
        articleService.updateArticle(id, article);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }
}