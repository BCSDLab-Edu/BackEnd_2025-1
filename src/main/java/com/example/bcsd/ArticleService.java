package com.example.bcsd;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleDao articleDao;

    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Transactional(readOnly = true)
    public String getBoardName(Long boardId) {
        return articleDao.getBoardNameById(boardId);
    }

    @Transactional(readOnly = true)
    public List<ArticleDto> getAllByBoardId(Long boardId) {
        return articleDao.findAllByBoardId(boardId);
    }

    @Transactional(readOnly = true)
    public List<Article> getAllArticles() {
        return articleDao.findAll();
    }

    @Transactional(readOnly = true)
    public Article getArticleById(Long id) {
        return articleDao.findById(id);
    }

    @Transactional
    public void addArticle(Article article) {
        articleDao.insert(article);
    }

    @Transactional
    public void updateArticle(Long id, Article article) {
        articleDao.update(id, article);
    }

    @Transactional
    public void deleteArticle(Long id) {
        articleDao.delete(id);
    }
}
