package com.example.bcsd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    // Create
    @PostMapping
    public String addArticle(@RequestBody Article article) {
        articleService.addArticle(article);
        return "게시글이 추가되었습니다.";
    }

    // Read all
    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    // Read one
    @GetMapping("/{id}")
    public Article getArticle(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

    // Update
    @PutMapping("/{id}")
    public String updateArticle(@PathVariable Long id, @RequestBody Article updated) {
        articleService.updateArticle(id, updated);
        return "게시글이 수정되었습니다.";
    }

    // Delete
    @DeleteMapping("/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return "게시글이 삭제되었습니다.";
    }
}
