package com.example.bcsd;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private final Map<Long, Article> articleData = new HashMap<>();
    private Long nextId = 1L;

    // Create
    public void createArticle(Article article) {
        Long id = article.getId();
        if (id == null) {
            id = nextId++;
        }
        Article newArticle = new Article(id, article.getTitle(), article.getContent());
        articleData.put(id, newArticle);
    }

    // Read all
    public List<Article> getAllArticles() {
        return new ArrayList<>(articleData.values());
    }

    // Read one
    public Article getArticleById(Long id) {
        return articleData.get(id);
    }

    // Update
    public void updateArticle(Long id, Article updated) {
        Article existing = articleData.get(id);
        if (existing != null) {
            existing.update(updated.getTitle(), updated.getContent());
        }
    }

    // Delete
    public void deleteArticle(Long id) {
        articleData.remove(id);
    }
}
