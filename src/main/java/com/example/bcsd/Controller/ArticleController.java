package com.example.bcsd.Controller;

import com.example.bcsd.Article;
import com.example.bcsd.Service.ArticleService;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // GET /articles?boardId={boardId}
    @GetMapping("/articles")
    public List<Article> getArticles(@RequestParam Long boardId) {
        return articleService.findByBoardId(boardId);
    }

    // GET /articles/{id}
    @GetMapping("/articles/{id}")
    public Article getArticle(@PathVariable Long id) {
        return articleService.findById(id);
    }

    // POST /articles
    @PostMapping("/articles")
    public int createArticle(@RequestBody Article article) {
        return articleService.create(article);
    }

    // PUT /articles/{id}
    @PutMapping("/articles/{id}")
    public int updateArticle(@PathVariable Long id, @RequestBody Article article) {
        return articleService.update(id, article);
    }

    // DELETE /articles/{id}
    @DeleteMapping("/articles/{id}")
    public int deleteArticle(@PathVariable Long id) {
        return articleService.delete(id);
    }
}
