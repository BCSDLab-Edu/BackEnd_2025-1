package com.example.bcsd.repository;


import com.example.bcsd.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    List<Article> findAll();
    List<Article> findByBoardId(Long boardId);
    Optional<Article> findById(Long id);
    boolean existsByWriterId(Long id);
    boolean existsByBoardId(Long id);
    Article save(Article article);
    void update(Article article);
    boolean deleteById(Long id);
}