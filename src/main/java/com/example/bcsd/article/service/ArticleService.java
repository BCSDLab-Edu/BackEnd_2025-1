package com.example.bcsd.article.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.bcsd.article.model.Article;
import com.example.bcsd.article.dto.CreateArticleRequest;
import com.example.bcsd.article.repository.ArticleRepository;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void CreateArticle(CreateArticleRequest request) {
        articleRepository.save(request.toArticle());
    }

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Article getArticle(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found"));
    }

    public void deleteArticle(Long id) {
        articleRepository.delete(id);
    }
}
