package com.example.bcsd;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final Map<Long, Article> articleStore = new HashMap<>();
    private long nextId = 1;

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        article.setPostId(nextId++);
        articleStore.put(article.getPostId(), article);
        return ResponseEntity.ok(article);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        Article article = articleStore.get(id);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(article);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article updatedData) {
        Article article = articleStore.get(id);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        article.setTitle(updatedData.getTitle());
        article.setContent(updatedData.getContent());
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        if (!articleStore.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        articleStore.remove(id);
        return ResponseEntity.noContent().build();
    }
}