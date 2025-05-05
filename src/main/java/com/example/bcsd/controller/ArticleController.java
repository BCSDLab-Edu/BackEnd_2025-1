package com.example.bcsd.controller;

import com.example.bcsd.dto.Article;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final Map<Integer, Article> articleMap = new ConcurrentHashMap<>();

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable int id) {
        Article article = articleMap.get(id);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(article);
    }

    @PostMapping
    public ResponseEntity<String> createArticle(@RequestBody Article article) {
        if (articleMap.containsKey(article.getId())) {
            return ResponseEntity.badRequest().body("해당 ID의 게시글이 이미 존재합니다.");
        }
        articleMap.put(article.getId(), article);
        return ResponseEntity.ok("게시글 생성 완료");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateArticle(@PathVariable int id, @RequestBody Article updatedArticle) {
        if (!articleMap.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedArticle.setId(id);
        articleMap.put(id, updatedArticle);
        return ResponseEntity.ok("게시글 수정 완료");
    }

    public ResponseEntity<String> deleteArticle(@PathVariable int id) {
        if (!articleMap.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        articleMap.remove(id);
        return ResponseEntity.ok("게시글 삭제 완료");
    }
}