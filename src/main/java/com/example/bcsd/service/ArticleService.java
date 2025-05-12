package com.example.bcsd.service;

import com.example.bcsd.repository.ArticleRepository;
import com.example.bcsd.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    public Collection<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article updateArticle(Long id, Article updatedArticle) {
        return articleRepository.update(id, updatedArticle);
    }

    public String deleteArticle(Long id) {
        return articleRepository.delete(id);
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id);
    }
}
