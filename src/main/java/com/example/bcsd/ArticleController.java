package com.example.bcsd;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    List<Article> articleList = new ArrayList<>();
    int currentId = 1;

    // GET /articles/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable int id) {
        for (Article article : articleList) {
            if (article.getId() == id) {
                return ResponseEntity.ok(article);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // POST /articles
    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        article.setId(currentId++);
        articleList.add(article);
        return ResponseEntity.ok(article);
    }
}