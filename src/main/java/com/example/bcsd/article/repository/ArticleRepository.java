package com.example.bcsd.article.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.example.bcsd.article.model.Article;

@Repository
public class ArticleRepository {
    private final Map<Long, Article> articleDB = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public Article save(Article article) {
        Long id = idCounter.getAndIncrement();
        article.setId(id);
        articleDB.put(id, article);
        return article;
    }

    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(articleDB.get(id));
    }

    public List<Article> findAll() {
        return new ArrayList<>(articleDB.values());
    }

    public Article updateSave(Article article) {
        articleDB.put(article.getId(), article);
        return article;
    }

    public void delete(Long id) {
        articleDB.remove(id);
    }
}
