package com.example.bcsd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.bcsd.model.Article;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ArticleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Article> findById(Long id) {
        Article article = entityManager.find(Article.class, id);
        return Optional.ofNullable(article);
    }

    public List<Article> findAll() {
        String jpql = "SELECT a FROM Article a";
        return entityManager.createQuery(jpql, Article.class).getResultList();
    }

    public List<Article> findByBoardId(Long boardId) {
        String jpql = "SELECT a FROM Article a WHERE a.board.id = :boardId";
        return entityManager.createQuery(jpql, Article.class)
                            .setParameter("boardId", boardId)
                            .getResultList();
    }

    public List<Article> findByAuthorId(Long authorId) {
        String jpql = "SELECT a FROM Article a WHERE a.member.id = :authorId";
        return entityManager.createQuery(jpql, Article.class)
                            .setParameter("authorId", authorId)
                            .getResultList();
    }

    public void deleteById(Long id) {
        Article article = entityManager.find(Article.class, id);
        if (article != null) {
            entityManager.remove(article);
        }
    }

    public void save(Article article) {
       if (article.getId() == null) { // 생성
            entityManager.persist(article);
        } else { // 수정
            entityManager.merge(article);
        }
    }
    
}
