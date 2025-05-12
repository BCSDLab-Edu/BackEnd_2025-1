package com.example.bcsd;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService service;

    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        return ResponseEntity.ok(service.getAllArticles());
    }

    // GET /articles/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        Article article = service.getArticle(id);
        if (article == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(article);
    }

    // POST /articles
    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        Article created = service.createArticle(article);
        return ResponseEntity.ok(created);
    }

    // PUT /articles/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        Article updated = service.updateArticle(id, article);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    // DELETE /articles/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        boolean deleted = service.deleteArticle(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
