package com.example.bcsd;

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

    private final Map<Long, Article> articleMap = new HashMap<>();

    // [1] 게시글 등록 (POST)
    @PostMapping
    public String createArticle(@RequestBody Article article) {
        articleMap.put(article.getId(), article);
        return "게시글 등록 완료";
    }

    //  [2] 게시글 조회 (GET)
    @GetMapping("/{id}")
    public ResponseEntity<?> getArticle(@PathVariable Long id) {
        Article article = articleMap.get(id);
        if (article != null) {
            return ResponseEntity.ok(article);
        } else {
            return ResponseEntity.status(404).body("게시글이 존재하지 않습니다.");
        }
    }

    //  [3] 게시글 삭제 (DELETE)
    @DeleteMapping("/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleMap.remove(id);
        return "삭제 완료";
    }

    @PutMapping("/{id}")
    public String updateArticle(@PathVariable Long id, @RequestBody Article article) {
        Article existing = articleMap.get(id);
        if (existing != null) {
            existing.setTitle(article.getTitle());
            existing.setContent(article.getContent());
            existing.setAuthorId(article.getAuthorId());
            existing.setCreatedDate(article.getCreatedDate());
            return "수정 완료";
        } else {
            return "수정 실패: 게시글이 존재하지 않습니다.";
        }
    }
    // [4] 모든 게시글 조회 (GET)
    @GetMapping
    public List<Article> getAllArticles() {
        return new ArrayList<>(articleMap.values());
    }
    @PostConstruct
    public void init() {
        articleMap.put(1L,new Article(1L, 12L, 1L, "제목1","안녕하세요", "2025-05-11","-"));
        articleMap.put(2L,new Article(2L, 123L, 2L, "제목2","안녕히가세요", "2025-05-11","-"));
    }
}
