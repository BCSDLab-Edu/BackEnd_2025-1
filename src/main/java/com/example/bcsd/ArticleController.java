package com.example.bcsd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    // Create
    @PostMapping
    public ResponseEntity<String> createArticle(@RequestBody ArticleRequest request) {
        Article article = new Article(null, request.title(), request.content());
        articleService.createArticle(article);
        return ResponseEntity.ok ("게시글이 추가되었습니다.");
    }

    // Read all
    @GetMapping
    public List<ArticleResponse> getAllArticles() {
        return articleService.getAllArticles().stream()
                .map(article -> new ArticleResponse(article.getId(), article.getTitle(), article.getContent()))
                .collect(Collectors.toList());
    }

    // Read one
    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable Long id) {
        Article article = articleService.getArticleById(id);
        if(article == null) {
            return ResponseEntity.notFound().build();
        }
        ArticleResponse response = new ArticleResponse(article.getId(), article.getTitle(), article.getContent());
        return ResponseEntity.ok(response);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<String> updateArticle(@PathVariable Long id, @RequestBody ArticleRequest updated) {
        Article article = new Article(id, updated.title(), updated.content());
        articleService.updateArticle(id, article);
        return ResponseEntity.ok("게시글이 수정되었습니다.");
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long id) {
        Article existingArticle = articleService.getArticleById(id);
        if(existingArticle == null) {
            return ResponseEntity.notFound().build();
        }
        articleService.deleteArticle(id);
        return ResponseEntity.ok ("게시글이 삭제되었습니다.");
    }
}
