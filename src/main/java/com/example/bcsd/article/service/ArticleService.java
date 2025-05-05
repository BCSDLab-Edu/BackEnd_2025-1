package com.example.bcsd.article.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.bcsd.article.model.Article;
import com.example.bcsd.article.dto.ArticleRequest;
import com.example.bcsd.article.dto.UpdateArticleResponse;
import com.example.bcsd.article.repository.ArticleRepository;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void CreateArticle(ArticleRequest request) {
        articleRepository.save(request.toArticle());
    }

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Article getArticle(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found"));
    }

    public UpdateArticleResponse updateArticle(Long id, ArticleRequest request) {
        Article article = getArticle(id);
        String name = request.name();
        String description = request.description();
        if (name == null) {
            name = article.getName();
        } else if (description == null) {
            description = article.getDescription();
        }

        article.setName(name);
        article.setDescription(description);

        articleRepository.updateSave(article);
        return UpdateArticleResponse.from(article);
    }

    public void deleteArticle(Long id) {
        articleRepository.delete(id);
    }
}
