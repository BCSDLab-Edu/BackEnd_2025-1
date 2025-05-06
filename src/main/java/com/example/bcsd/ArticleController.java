package com.example.bcsd;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private Map<Long, Article> articleStore = new HashMap<>();
    private Long currentId = 1L;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Article createArticle(@RequestBody Article article) {
        article.setId(currentId++);
        articleStore.put(article.getId(), article);
        return article;
    }

    @GetMapping
    public Collection<Article> getAllArticles() {
        return articleStore.values();
    }

    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable Long id, @RequestBody Article updatedArticle) {
        Article existing = articleStore.get(id);
        if (existing == null) {
            throw new NoSuchElementException("Article not found: " + id);
        }
        existing.setDescription(updatedArticle.getDescription());
        return existing;
    }

    @DeleteMapping("/{id}")
    public String deleteArticle(@PathVariable Long id) {
        Article removed = articleStore.remove(id);
        if (removed == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 게시글이 없습니다: " + id);
        }
        return "삭제 완료!";
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable Long id) {
        Article article = articleStore.get(id);
        if (article == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 게시글이 없습니다: " + id);
        }
        return article;
    }


}
