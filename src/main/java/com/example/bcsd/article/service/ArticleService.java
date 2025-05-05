package com.example.bcsd.article.service;

import org.springframework.stereotype.Service;

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
}
