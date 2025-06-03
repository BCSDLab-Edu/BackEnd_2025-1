package com.example.bcsd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bcsd.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findById(Long id);

    List<Article> findAll();

    List<Article> findByBoardId(Long boardId);

    List<Article> findByAuthorId(Long authorId);

    void deleteById(Long id);

    Article save(Article article);
}