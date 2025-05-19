package com.example.bcsd.service;

import com.example.bcsd.model.Article;
import com.example.bcsd.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository repository;

    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Article createArticle(Article article) {
        return repository.save(article);
    }

    public List<Article> getArticlesByBoardId(Long boardId) {
        return repository.findByBoardId(boardId);
    }

    public List<Article> getAllArticles() {
        return repository.findAll();
    }

    public Article getArticleById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Article updateArticle(Long id, Article article) {
        repository.update(id, article);
        return getArticleById(id);
    }

    @Transactional
    public void deleteArticle(Long id) {
        repository.delete(id);
    }
}
