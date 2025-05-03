package com.example.bcsd;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

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

    // PUT /articles/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable int id, @RequestBody Article updatedArticle) {
        for (Article article : articleList) {
            if (article.getId() == id) {
                article.setTitle(updatedArticle.getTitle());
                article.setContent(updatedArticle.getContent());
                return ResponseEntity.ok(article);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE /articles/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable int id) {
        Iterator<Article> iterator = articleList.iterator();
        while (iterator.hasNext()) {
            Article article = iterator.next();
            if (article.getId() == id) {
                iterator.remove();
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}