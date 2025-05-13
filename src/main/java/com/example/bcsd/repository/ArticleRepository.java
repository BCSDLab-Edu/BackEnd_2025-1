package com.example.bcsd.repository;

import com.example.bcsd.domain.Article;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleRepository {
    private final List<Article> articles = new ArrayList<>();

    public List<Article> findAll() {
        return articles;
    }

    public Optional<Article> findById(Long id) {
        return articles.stream().filter(a -> a.getId().equals(id)).findFirst();
    }

    public Article save(Article article) {
        article.setCreatedDate(LocalDateTime.now());
        article.setModifiedDate(LocalDateTime.now());
        articles.add(article);
        return article;
    }

    public boolean deleteById(Long id) {
        return articles.removeIf(a -> a.getId().equals(id));
    }
}