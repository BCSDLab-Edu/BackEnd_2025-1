package com.example.bcsd;

import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private Map<Long, Article> articleData = new HashMap<>();

    // Create
    public void addArticle(Article article) {
        articleData.put(article.getId(), article);
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
        articleData.put(id, updated);
    }

    // Delete
    public void deleteArticle(Long id) {
        articleData.remove(id);
    }
}
