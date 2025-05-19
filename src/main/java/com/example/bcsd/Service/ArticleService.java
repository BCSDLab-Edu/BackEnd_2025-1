package com.example.bcsd.Service;

import com.example.bcsd.Article;
import com.example.bcsd.Repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> findByBoardId(Long boardId) {
        return articleRepository.findByBoardId(boardId);
    }

    public Article findById(Long id) {
        return articleRepository.findById(id);
    }

    public int create(Article article) {
        return articleRepository.save(article);
    }

    public int update(Long id, Article article) {
        return articleRepository.update(id, article);
    }

    public int delete(Long id) {
        return articleRepository.delete(id);
    }
}
