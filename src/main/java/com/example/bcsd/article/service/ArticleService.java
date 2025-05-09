package com.example.bcsd.article.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.bcsd.article.dto.UpdateArticleRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.bcsd.article.model.Article;
import com.example.bcsd.article.dto.CreateArticleRequest;
import com.example.bcsd.article.dto.UpdateArticleResponse;
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

    public UpdateArticleResponse updateArticle(Long id, UpdateArticleRequest request) {
        Article article = getArticle(id);
        String title = request.title();
        Long boardId = request.boardId();
        String content = request.content();

        if (title == null) {
            title = article.getTitle();
        }

        if (boardId == null) {
            boardId = article.getBoardId();
        }

        if (content == null) {
            content = article.getContent();
        }

        article.setTitle(title);
        article.setBoardId(boardId);
        article.setContent(content);
        article.setEditedDate(LocalDateTime.now());

        articleRepository.updateSave(article);
        return UpdateArticleResponse.from(article);
    }

    public void deleteArticle(Long id) {
        articleRepository.delete(id);
    }
}
