package com.example.bcsd.article.repository;

import java.util.Map;
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
}
