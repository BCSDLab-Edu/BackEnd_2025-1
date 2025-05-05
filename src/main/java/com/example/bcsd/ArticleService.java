package com.example.bcsd;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleService {
    private final Map<Long, Article> articleMap = new HashMap<>();
    private long nextId = 1;

    public Article getArticle(Long id) {
        return articleMap.get(id);
    }

    public Article createArticle(Article article) {
        article.setId(nextId++);
        articleMap.put(article.getId(), article);
        return article;
    }

    public Article updateArticle(Long id, Article updated) {
        Article existing = articleMap.get(id);
        if (existing != null) {
            existing.setTitle(updated.getTitle());
            existing.setContent(updated.getContent());
        }
        return existing;
    }

    public boolean deleteArticle(Long id) {
        return articleMap.remove(id) != null;
    }
}
