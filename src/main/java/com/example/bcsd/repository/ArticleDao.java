package com.example.bcsd.repository;

import com.example.bcsd.domain.Article;
import com.example.bcsd.dto.ArticleResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleDao {
    @PersistenceContext
    private EntityManager em;

    public void insert(Article article) {
        em.persist(article);
    }

    public List<Article> findAll() {
        return em.createQuery("SELECT a FROM Article a", Article.class).getResultList();
    }

    public Article findById(Long id) {
        return em.find(Article.class, id);
    }

    public void update(Long id, Article article) {
        Article found = em.find(Article.class, id);
        if (found != null) {
            found.setBoardId(article.getBoardId());
            found.setTitle(article.getTitle());
            found.setContent(article.getContent());
            found.setModifiedDate(article.getModifiedDate());
        }
    }

    public void delete(Long id) {
        Article article = em.find(Article.class, id);
        if (article != null) em.remove(article);
    }

    public List<ArticleResponseDto> findAllByBoardId(Long boardId) {
        return em.createQuery("SELECT new com.example.bcsd.dto.ArticleResponseDto(" +
                        "a.id, a.title, a.content, '', '', a.createdDate, a.modifiedDate) " +
                        "FROM Article a WHERE a.boardId = :boardId", ArticleResponseDto.class)
                .setParameter("boardId", boardId)
                .getResultList();
    }

    public Article findLatest() {
        return em.createQuery("SELECT a FROM Article a ORDER BY a.id DESC", Article.class)
                .setMaxResults(1)
                .getSingleResult();
    }

    public String getBoardNameById(Long boardId) {
        return em.createQuery("SELECT b.name FROM Board b WHERE b.id = :boardId", String.class)
                .setParameter("boardId", boardId)
                .getSingleResult();
    }
}
