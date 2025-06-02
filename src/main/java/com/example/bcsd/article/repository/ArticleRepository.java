package com.example.bcsd.article.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.bcsd.article.model.Article;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class ArticleRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Article save(Article article) {
        em.persist(article);

        return article;
    }

    public Article findById(Long id) {
        Article article = em.find(Article.class, id);

        return article;
    }

    public List<Article> findAll() {

        String jpql = "SELECT * FROM article";
        TypedQuery<Article> typedQuery = em.createQuery(jpql, Article.class);
        return typedQuery.getResultList();
    }

    @Transactional
    public Article updateSave(Article article) {
        return em.merge(article);
    }

    @Transactional
    public void delete(Long id) {
        Article article = em.find(Article.class, id);

        if (article != null) {
            em.remove(article);
        }
    }
}
